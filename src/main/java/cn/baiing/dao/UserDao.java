package cn.baiing.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import cn.baiing.dao.util.SimpleCondition;
import cn.baiing.dao.util.SimpleDao;
import cn.baiing.db.model.User;

@Repository
public class UserDao {

	@Autowired
	private SimpleDao simpleDao;

	@Autowired
	private String sqlUser_getRoleNameByUserId;

	private final String TABLE_NAME = "user";

	public String getRoleNameByUserId(long userId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);

		return simpleDao.getNamedParameterJdbcTemplate().queryForObject(
				sqlUser_getRoleNameByUserId, paramMap, String.class);
	}

	public User getUserByLoginName(String loginName) {
		SimpleCondition condition = new SimpleCondition();
		condition.setPropertyName("loginName");
		condition.setPropertyCondition("=");
		condition.setPropertyValue(loginName);

		return simpleDao.queryOneDomainByProperty(User.class, TABLE_NAME,
				condition);
	}

	public boolean changePassword(String loginName, String loginPassword) {
		User user = new User();
		user.setLoginName(loginName);
		user.setLoginPassword(loginPassword);

		return simpleDao.updateByProperties(user, TABLE_NAME, "login_name",
				"login_password") > 0;
	}

	public long createUser(User user) throws DataAccessException {
		return simpleDao.create(user, TABLE_NAME, "id");
	}
}
