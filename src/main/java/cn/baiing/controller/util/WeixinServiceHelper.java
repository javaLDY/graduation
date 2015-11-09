package  cn.baiing.controller.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;






import cn.baiing.db.model.Dialogue;
import cn.baiing.util.QuartzManager;

import com.google.gson.GsonBuilder;

public class WeixinServiceHelper {
	
	private static final Log logger = LogFactory.getLog(WeixinServiceHelper.class);
	
	//问答队列
	private static List<String> waitingReplyTokenQueue = new ArrayList<String>();
	
	private static WeixinServiceHelper instance;
	
	private static final Object lock = new Object();
	
	private WeixinServiceHelper() {
		QuartzManager.addJob("weixin_baiing", "cn.baiing.utils.WeixinReplyScanJob", "0 0/2 * * * ?");
	}
	
	public static WeixinServiceHelper getInstance() {
		if (instance == null) {
			synchronized(lock) {
				if (instance == null)
					instance = new WeixinServiceHelper();
			}
		}
			
		return instance;
	}
	
	/**
	 * 添加提问人标识至待回复队列
	 * @param token 提问者标识（微信用户OPENID）
	 */
	public void addTokenToWaitingReplyQueue(String token) {
		if (StringUtils.isEmpty(token)) {
			logger.warn("when add token to waitingReplyQueue, param token is null");
			return;
		}
		waitingReplyTokenQueue.add(token);
	}
	
	/**
	 * 后台轮询扫描已回复的问题，及时推送
	 */
	public void deamonScanReply() {
		 int count = 0;
		 String accessToken = null;
		 List<String> replyTokenQueue = new ArrayList<String>();
		 for (String t : waitingReplyTokenQueue) {
			 if ((count % 10) == 0) {
				 accessToken = getAccessToken();
			 }
			 if (StringUtils.isNotEmpty(accessToken)) {
				 List<Dialogue> replyList = WebChatHelper.getInstance().scanReplyFromAskQueue(t);
				 if (CollectionUtils.isNotEmpty(replyList)) {
					 sendReplyToOpenId(t, replyList, accessToken);
					 replyTokenQueue.add(t);
				 }
			 }
			 count++;
		 }
		 waitingReplyTokenQueue.removeAll(replyTokenQueue);
	}
	
	private void sendReplyToOpenId(String token, List<Dialogue> replyList, String accessToken) {
		String toUser = token;
		String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + accessToken;
		String encoding = "utf-8";
		
		try {
			HttpPost httpPost = new HttpPost(url);
			HttpClient httpClient = new DefaultHttpClient();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("msgtype", "text");
			Map<String, String> con = new HashMap<String, String>();
			
			String content = "";
			for (Dialogue r : replyList) {
				if (content.length() > 0 )
					content += "\r\n";
				content += "你的问题“" + r.getAsk() + "”已有了回复[" + r.getAnswer() + "]";
			}
			
			con.put("content", content);
			map.put("text", con);
			map.put("touser", toUser);
			String jsonString = new GsonBuilder().serializeNulls().create().toJson(map);
			StringEntity s = new StringEntity(jsonString, encoding);
			s.setContentType("application/json");
			httpPost.setEntity(s);
			HttpResponse resPost = httpClient.execute(httpPost);
	        int statusPost =  resPost.getStatusLine().getStatusCode();
	        if (statusPost == 200) {  
	        	logger.info("send reply content to openId success.");
	        } else {
	        	logger.info("send reply content to openId error.");
	        }
		} catch (Exception e) {
			logger.error("send reply content to openId error.", e);
		}
	}
	
	private String getAccessToken() {
		String accessToken = null;
		
		String appId = "wxc64c8d8bc9deb3f7";
		String appSecret = "3822f53587431c194f6f205d4877ce39";
		String getTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret=" + appSecret;
		
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet get = new HttpGet(getTokenUrl); 
		HttpResponse resGet;
		try {
			resGet = httpClient.execute(get);
	        int statusGet =  resGet.getStatusLine().getStatusCode();
	        if (statusGet == 200) {  
	           HttpEntity entityGet = resGet.getEntity();  
	           String contentGet = EntityUtils.toString(entityGet);
	           
	           if (null != contentGet && !"".equals(contentGet.trim())) {
	        	   Map<String, Object> contentJsonGet = new GsonBuilder().create().fromJson(contentGet, Map.class);
	        	   if (contentJsonGet != null && !contentJsonGet.isEmpty()) {
	        		   accessToken = (String)contentJsonGet.get("access_token");
	        	   }
	           }
		    }		
		} catch (Exception e) {
			logger.error("get weinxin's access_token error.", e);
		} 
		
		return accessToken;
	}

}
