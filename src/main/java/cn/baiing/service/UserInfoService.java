package cn.baiing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.baiing.dao.UserInfoDao;
import cn.baiing.db.model.UserInfo;

/**
 * 
 * @author ldy
 *
 */
@Service
public class UserInfoService {

	@Autowired
	private UserInfoDao userInfoDao;
	
	public boolean getUserByNameOrPass(UserInfo userInfo){
		boolean selectStatus = false;
		int userCount = userInfoDao.getUserByNameOrPass(userInfo);
		if(userCount>0){
			selectStatus = true;
		}
		return selectStatus;
	}
	
	@Transactional
	public boolean insertUserInfo(UserInfo userInfo){
		boolean insertStatus = false;
		int id = userInfoDao.insertUserInfo(userInfo);
		if(id>0){
			insertStatus = true;
		}
		return insertStatus;
	}
	
	@Transactional
	public boolean updateUserInfo(UserInfo userInfo){
		boolean updateStatus = false;
		int id = userInfoDao.updateUserInfo(userInfo);
		if(id>0){
			updateStatus = true;
		}
		return updateStatus;
	}
	
	public JSONObject getUserInfoById(String id){
		JSONObject json = new JSONObject();
		json = userInfoDao.getUserInfoById(id);
		return json;
	}
	
	public JSONArray getAllOfUserInfo(){
		JSONArray jsonArray = new JSONArray();
		jsonArray = userInfoDao.getAllOfUserInfo();
		return jsonArray;
	}
}
