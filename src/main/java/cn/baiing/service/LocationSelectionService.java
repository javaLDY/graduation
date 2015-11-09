package cn.baiing.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.baiing.dao.LocationSelectionDao;
import cn.baiing.db.model.LocationInfos;

@Service
public class LocationSelectionService {
	
	@Autowired
	private LocationSelectionDao locationSelectionDao;
	
	public List<LocationInfos> getLocationInfo(String locationId){
		return locationSelectionDao.getLocationInfo(locationId);
	}
	
}
