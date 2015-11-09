package cn.baiing.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import cn.baiing.dao.util.SimpleDao;
import cn.baiing.db.model.LocationInfos;

@Repository
public class LocationSelectionDao {

	@Autowired
	private SimpleDao simpleDao;
	
	@Autowired
	private String sql_getLocationInfo;
	
	public List<LocationInfos> getLocationInfo(String locationId){
		Map<String, Object> paramMap = new HashMap<String,Object>();
		paramMap.put("locationId", locationId);
		return simpleDao.getNamedParameterJdbcTemplate()
				.query(sql_getLocationInfo, paramMap, new BeanPropertyRowMapper<LocationInfos>(
						LocationInfos.class));
	}
	
}