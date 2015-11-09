package cn.baiing.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import cn.baiing.dao.util.SimpleCondition;
import cn.baiing.dao.util.SimpleDao;
import cn.baiing.db.model.Question;

@Repository
public class QuestionDao {
	@Autowired
	private SimpleDao simpleDao;

	private final String TABLE_NAME = "question";

	public long createQuestion(Question question) throws DataAccessException {
		return simpleDao.create(question, TABLE_NAME, "id");
	}

	@Autowired
	private String sqlQuestion_answerCounterAdded;

	public boolean answerCounterAdded(long questionId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("questionId", questionId);

		return simpleDao.getNamedParameterJdbcTemplate().update(
				sqlQuestion_answerCounterAdded, paramMap) > 0;
	}

	@Autowired
	private String sqlQuestion_viewCounterAdded;

	public boolean viewCounterAdded(long questionId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("questionId", questionId);

		return simpleDao.getNamedParameterJdbcTemplate().update(
				sqlQuestion_viewCounterAdded, paramMap) > 0;
	}

	public boolean disableQuestion(long questionId) {
		final int DISABLE_STATUS = 0;
		return changeQuestionStatus(questionId, DISABLE_STATUS);
	}

	public boolean closeQuestion(long questionId) {
		final int CLOSE_STATUS = 2;
		return changeQuestionStatus(questionId, CLOSE_STATUS);
	}

	private boolean changeQuestionStatus(long questionId, int status) {
		Question question = new Question();
		question.setId(questionId);
		question.setStatus(status);

		return simpleDao.updateByProperties(question, TABLE_NAME, "id",
				"status") > 0;
	}

	public boolean removeQuestionPermanent(long questionId) {
		Question question = new Question();
		question.setId(questionId);

		return simpleDao.deleteByProperty(question, TABLE_NAME, "id") > 0;
	}

	public Question getQuestionById(long questionId) {
		SimpleCondition condition = new SimpleCondition();
		condition.setPropertyName("id");
		condition.setPropertyCondition("=");
		condition.setPropertyValue(questionId);

		return simpleDao.queryOneDomainByProperty(Question.class, TABLE_NAME,
				condition);
	}
}
