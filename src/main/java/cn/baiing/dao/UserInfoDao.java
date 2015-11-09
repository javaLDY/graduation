package cn.baiing.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.baiing.dao.util.SimpleDao;
import cn.baiing.db.model.UserInfo;
@Repository
public class UserInfoDao {

	@Autowired
	private SimpleDao simpleDao;
	
	@Autowired
	private String sql_getUserByNameOrPass;
	
	@SuppressWarnings("deprecation")
	public int getUserByNameOrPass(UserInfo userInfo){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userName", userInfo.getUserName());
		paramMap.put("passWord", userInfo.getPassWord());
		int userCount = simpleDao.getNamedParameterJdbcTemplate().queryForInt(sql_getUserByNameOrPass, paramMap);
		return userCount;
	}
	
	@Autowired
	private String sql_insertUserInfo;
	public int insertUserInfo(UserInfo userInfo){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userName", userInfo.getUserName());
		paramMap.put("passWord", userInfo.getPassWord());
		paramMap.put("roleId", userInfo.getRoleId());
		paramMap.put("phone", userInfo.getPhone());
		paramMap.put("email", userInfo.getEmail());
		int id = simpleDao.getNamedParameterJdbcTemplate().update(sql_insertUserInfo, paramMap);
		return id;
	}
	
	@Autowired
	private String sql_updateUserInfo;
	public int updateUserInfo(UserInfo userInfo){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userName", userInfo.getUserName());
		paramMap.put("passWord", userInfo.getPassWord());
		paramMap.put("roleId", userInfo.getRoleId());
		paramMap.put("phone", userInfo.getPhone());
		paramMap.put("email", userInfo.getEmail());
		paramMap.put("id", userInfo.getId());
		int id = simpleDao.getNamedParameterJdbcTemplate().update(sql_updateUserInfo, paramMap);
		return id;
	}
	
	@Autowired
	private String sql_getUserInfoById;
	public JSONObject getUserInfoById(String id){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		final JSONObject result = new JSONObject();
		simpleDao.getNamedParameterJdbcTemplate().query(sql_getUserInfoById, paramMap, new ResultSetExtractor<JSONObject>() {

			@Override
			public JSONObject extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				while(rs.next()){
					result.put("id", rs.getString("id"));
					result.put("username", rs.getString("username"));
					result.put("password", rs.getString("password"));
					result.put("roleId", rs.getString("role_id"));
					result.put("phone", rs.getString("phone"));
					result.put("email", rs.getString("email"));
				}
				return result;
			}
		});
		return result;
	}
	
	@Autowired
	private String sql_getAllOfUser;
	public JSONArray getAllOfUserInfo(){
		final JSONArray resultArray = new JSONArray();
		simpleDao.getNamedParameterJdbcTemplate().query(sql_getAllOfUser, new ResultSetExtractor<JSONArray>() {
			@Override
			public JSONArray extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				while(rs.next()){
					JSONObject result = new JSONObject();
					result.put("id", rs.getString("id"));
					result.put("username", rs.getString("username"));
					result.put("roleName", rs.getString("role_name"));
					result.put("phone", rs.getString("phone"));
					result.put("email", rs.getString("email"));
					resultArray.add(result);
				}
				return resultArray;
			}
		});
		return resultArray;
	}
}
