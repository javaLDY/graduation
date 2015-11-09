package cn.baiing.db.model;

import java.util.Date;

/**
 * WebChat模块对话bean
 * @author <a href="mailto:xiaonanem@126.com">chenping</a>
 * @date 2015-03-06
 */
public class Dialogue {

	private String ask;
	
	private String answer;
	
	private Date askTime;
	
	private Date answerTime;

	public String getAsk() {
		return ask;
	}

	public void setAsk(String ask) {
		this.ask = ask;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Date getAskTime() {
		return askTime;
	}

	public void setAskTime(Date askTime) {
		this.askTime = askTime;
	}

	public Date getAnswerTime() {
		return answerTime;
	}

	public void setAnswerTime(Date answerTime) {
		this.answerTime = answerTime;
	}
	
}
