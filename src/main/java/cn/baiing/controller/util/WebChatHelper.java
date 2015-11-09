package cn.baiing.controller.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.shiro.util.CollectionUtils;

import cn.baiing.config.BaiingConfig;
import cn.baiing.db.model.Dialogue;
import cn.baiing.exception.ConfigurationException;

public class WebChatHelper {

	
	private static final Log logger = LogFactory.getLog(WebChatHelper.class);
	
	//人工问答存储
	private static Map<Integer, List<Dialogue>> artificialAsk = new HashMap<Integer, List<Dialogue>>();
	//问答队列
	private static Map<String, List<String>> askQueue = new HashMap<String, List<String>>();
	
	private static WebChatHelper instance;
	
	private static final Object lock = new Object();
	
	private WebChatHelper() {}
	
	public static WebChatHelper getInstance() {
		if (instance == null) {
			synchronized(lock) {
				if (instance == null)
					instance = new WebChatHelper();
			}
		}
			
		return instance;
	}
	
	/**
	 * 通过人工问答历史库获取回答
	 * @param ask
	 * @return
	 */
	public String getReplyByArtificialAskRepo(String ask) {
		if (StringUtils.isEmpty(ask)) {
			logger.warn("param ask is null");
			return null;
		}
		String reply = null;
		Dialogue dialogue = getDialogueByArtificialAskRepo(ask);
		if (dialogue != null)
			reply = dialogue.getAnswer();
		
		return reply;
	}
	
	/**
	 * 通过人工问答历史库获取回答, 并且无回答时将问题加入问题队列
	 * @param ask
	 * @param token
	 * @return
	 */
	public String getReplyByArtificialAskRepo(String ask, String token) {
		String reply = getReplyByArtificialAskRepo(ask);
		reply = null;
		if (reply == null) {
			addAskToArtificialAskQueue(token, ask);
			addAskToArtificialAskRepo(ask);
			sendRequestToArtificialAskSystem(ask);
		}
		return reply;
	}
	
	/**
	 * 添加提问至人工问答队列
	 * @param token 提问者标识（uuid）
	 * @param ask 问题
	 */
	public void addAskToArtificialAskQueue(String token, String ask) {
		if (StringUtils.isEmpty(token) || StringUtils.isEmpty(ask)) {
			logger.warn("when add ask to queue, param token or ask is null");
			return;
		}
		enterAskQueue(token, ask);
	}
	
	/**
	 * 通过用户标识扫描已有人工回复的对话
	 * @param token
	 * @return
	 */
	public List<Dialogue> scanReplyFromAskQueue(String token) {
		if (StringUtils.isEmpty(token)) {
			logger.warn("when scan reply from queue, param token null");
			return null;
		}		
		List<String> askList = askQueue.get(token);
		if (CollectionUtils.isEmpty(askList)) {
			logger.info("no ask in queue by token(" + token + ")");
			return null;
		}
		List<Dialogue> replayDialogueList = null;
		for (String ask : askList) {
			Dialogue dialogue = getDialogueByArtificialAskRepo(ask);
			if (dialogue != null && StringUtils.isNotEmpty(dialogue.getAnswer())) {
				if (replayDialogueList == null)
					replayDialogueList = new ArrayList<Dialogue>();
				replayDialogueList.add(dialogue);
			}
		}
		if (!CollectionUtils.isEmpty(replayDialogueList)) {
			for (Dialogue rd : replayDialogueList) {
				exitAskQueue(token, rd.getAsk());
			}
		}
		return replayDialogueList;
	}
	
	/**
	 * 发送问题至人工座席平台
	 * @param ask
	 */
	public void sendRequestToArtificialAskSystem(String ask) {
		if (StringUtils.isEmpty(ask)) {
			logger.warn("param ask is null, send request to artificial ask system error.");
			return;
		}
		logger.info("send ask : " + ask);
		try {
			String aiUrl = BaiingConfig.getInstance().getProperty("artificial.ask.url");
			if (aiUrl == null)
				throw new ConfigurationException("artificial.ask.url is null");
			String encoding = "utf-8";
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(aiUrl);
			NameValuePair nvPair = new BasicNameValuePair("ask", ask);
			httpPost.setEntity(new UrlEncodedFormEntity(Arrays.asList(nvPair), encoding));
			HttpResponse resPost = httpClient.execute(httpPost);
	        int statusPost =  resPost.getStatusLine().getStatusCode();
	        if (statusPost == 200) {  
	           HttpEntity entityPost = resPost.getEntity();  
	           String contentPost = EntityUtils.toString(entityPost, encoding);
	           logger.info("ai system response info is : " + contentPost);
	        }
	        logger.info("ai system response http status code : " + statusPost);
		} catch (ConfigurationException e) {
			logger.error("get artificial.ask.url config param error.", e);
		} catch (UnsupportedEncodingException e) {
			logger.error("call artificial-ask system error.", e);
		} catch (ClientProtocolException e) {
			logger.error("call artificial-ask system error.", e);
		} catch (IOException e) {
			logger.error("call artificial-ask system error.", e);
		}
	}
	
	/**
	 * 人工座席平台回复
	 * @param ask
	 * @param reply
	 */
	public void replyFromArtificialAskSystem(String ask, String reply) {
		List<Dialogue> dialogueList = artificialAsk.get(ask.hashCode());
		Dialogue dialogue = null;
		if (!CollectionUtils.isEmpty(dialogueList)) {
			for (Dialogue d : dialogueList) {
				if (ask.equalsIgnoreCase(d.getAsk())) {
					dialogue = d;
					break;
				}
			}
		}
		if (dialogue != null) {
			dialogue.setAnswer(reply);
			dialogue.setAnswerTime(new Date());
		}
	}
	
	public Map<Integer, List<Dialogue>> getArtificialAskRepo() {
		return artificialAsk;
	}
	
	private void enterAskQueue(String token, String ask) {
		List<String> askList = askQueue.get(token);
		if (CollectionUtils.isEmpty(askList)) {
			askList = new ArrayList<String>();
			askList.add(ask);
		}
		if (askList.size() > 1) {
			boolean exist = false;
			for (String askItem : askList) {
				if (askItem.equalsIgnoreCase(ask)) {
					exist = true;
					break;
				}
			}
			if (!exist)
				askList.add(ask);
		}
		askQueue.put(token, askList);
	}
	
	private void exitAskQueue(String token, String ask) {
		List<String> askList = askQueue.get(token);
		boolean exist = false;
		for (String askItem : askList) {
			if (askItem.equalsIgnoreCase(ask)) {
				exist = true;
				break;
			}
		}
		if (exist)
			askList.remove(ask);
			
		if (CollectionUtils.isEmpty(askList))
			askQueue.remove(token);
	}
	
	private void addAskToArtificialAskRepo(String ask) {
		List<Dialogue> dialogueList = artificialAsk.get(ask.hashCode());
		boolean exist = false;
		if (!CollectionUtils.isEmpty(dialogueList)) {
			for (Dialogue d : dialogueList) {
				if (ask.equalsIgnoreCase(d.getAsk())) {
					exist = true;
					break;
				}
			}
		} else {
			dialogueList = new ArrayList<Dialogue>();
		}
		if (!exist) {
			Dialogue d = new Dialogue();
			d.setAsk(ask);
			d.setAskTime(new Date());
			dialogueList.add(d);
			artificialAsk.put(ask.hashCode(), dialogueList);
		}
	}
	
	/**
	 * 通过人工问答历史库获取对话
	 * @param ask
	 * @return
	 */
	private Dialogue getDialogueByArtificialAskRepo(String ask) {
		Dialogue dialogue = null;
		List<Dialogue> dialogueList = artificialAsk.get(ask.hashCode());
		if (!CollectionUtils.isEmpty(dialogueList)) {
			for (Dialogue d : dialogueList) {
				if (ask.equalsIgnoreCase(d.getAsk())) {
					dialogue = d;
					break;
				}
			}
		}
		return dialogue;
	}
	
	
	
	/**
	 * 去掉斜杠，只取前100个字符
	 * @param word
	 * @return
	 */
	
	public static String  cancelSlash(String word){
		if (Pattern.compile("\\\\").matcher(word).find())
			word = word.replaceAll("\\\\", "");
		
		if (word.length() > 100)
			word = word.substring(0, 100);

		return word;
	}
	public static String ajaxJson(String jsonData, HttpServletResponse response) {
		
		return ajaxWriter(jsonData, "application/json",  response);
	}

	public static String ajaxWriter(String content, String type, HttpServletResponse response) {
		PrintWriter writer = null;
		try {
			response.setContentType(type + ";charset=UTF-8");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			writer = response.getWriter();
			writer.write(content);
			writer.flush();
		} catch (IOException e) {
			logger.error("ajax data output error.", e);
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
		
		return null;
	}
}
