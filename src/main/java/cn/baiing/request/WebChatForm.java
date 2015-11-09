package cn.baiing.request;

import java.util.List;

import cn.baiing.db.model.Dialogue;

public class WebChatForm {

protected String q;
	
	protected String ask;	//提问
	
	protected String reply;	//回复
	
	protected String token;	//通过uuid生成的唯一回话标识
	
	protected Integer channel = new Integer(1);	//1:web、2:微信
	
	protected List<Dialogue> allReplyList;

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public String getAsk() {
		return ask;
	}

	public void setAsk(String ask) {
		this.ask = ask;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getChannel() {
		return channel;
	}

	public void setChannel(Integer channel) {
		this.channel = channel;
	}

	public List<Dialogue> getAllReplyList() {
		return allReplyList;
	}

	public void setAllReplyList(List<Dialogue> allReplyList) {
		this.allReplyList = allReplyList;
	}
	
	
}
