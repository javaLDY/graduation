package cn.baiing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.baiing.dao.UserDao;
import cn.baiing.db.model.User;
import cn.baiing.security.SimpleShiro;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public String getRoleNameByUserId(long userId) {
		return userDao.getRoleNameByUserId(userId);
	}

	public User getUserByLoginName(String loginName) {
		return userDao.getUserByLoginName(loginName);
	}

	@Transactional
	public boolean changePassword(String loginName, String loginPassword) {
		return userDao.changePassword(loginName,
				SimpleShiro.encryptPassword(loginPassword));
	}

}
