package cn.baiing.dao.util;

import java.util.List;

import cn.baiing.db.model.Dialogue;

public class WebChatBase {

	protected String q;
	
	protected String ask;	//提问
	
	protected String reply;	//回复
	
	protected String token;	//通过uuid生成的唯一回话标识
	
	protected Integer channel;	//1:web、2:微信
	
	protected int pageSize;
	
	protected List<Dialogue> allReplyList;

	protected int startsPos;
	
	protected float similarity;
	
	protected String locationId;
	
	//用来启动是否调用云极的自动回复
	protected String isStartYj;
	
	public String getIsStartYj() {
		return isStartYj;
	}

	public void setIsStartYj(String isStartYj) {
		this.isStartYj = isStartYj;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public int getStartsPos() {
		return startsPos;
	}

	public void setStartsPos(int startsPos) {
		this.startsPos = startsPos;
	}

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

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public float getSimilarity() {
		return similarity;
	}

	public void setSimilarity(float similarity) {
		this.similarity = similarity;
	}
}
