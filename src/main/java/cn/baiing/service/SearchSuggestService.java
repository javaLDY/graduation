package cn.baiing.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.baiing.c.search.SearchClient;
import cn.baiing.c.search.SearchProxyFactory;
import cn.baiing.c.search.SearchRequest;

@Service
public class SearchSuggestService {

	@Autowired
	private SearchProxyFactory searchProxyFactory;
	
	public String searchSuggest(String keyword, long locationId, int channel){
		String host = searchProxyFactory.getSuggestServiceHost();
		String port = searchProxyFactory.getSuggestServicePort();
		SearchClient client = new SearchClient(host, port);
		
		SearchRequest req = new SearchRequest();
		req.setKeyword(keyword);
		req.setLocationId(locationId);
		req.setChannel(channel);
		
		String result = client.search(req.buildString4CSearch());
		JSONObject jsonObject = new JSONObject();
		if(result != null){
			jsonObject.put("list", JSON.parseArray(result));
			jsonObject.put("success", true);
		}else {
			jsonObject.put("success", false);
		}
		return jsonObject.toJSONString();
		
	}
	
}
