package cn.baiing.controller;

import javax.servlet.http.HttpServletResponse;

import org.aspectj.weaver.AjAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.baiing.controller.util.WebChatHelper;
import cn.baiing.db.model.UserInfo;
import cn.baiing.service.UserInfoService;

@Controller
@RequestMapping("graduation")
public class LoginController {

	@Autowired
	private UserInfoService userInfoService;
	
	@RequestMapping("/login")
	public String login(){
		return "login";
	}
	
	@RequestMapping(value = "/getUserByNameOrPass", method = RequestMethod.POST)
	public String getUserByNameOrPass(UserInfo user, HttpServletResponse response){
		JSONObject result = new JSONObject();
		boolean selectStatus = userInfoService.getUserByNameOrPass(user);
		if(selectStatus == true){
			result.put("success", true);
			result.put("message", "登陆成功成功");
		}else{
			result.put("success", false);
			result.put("message", "用户名或密码错误");
		}
		return WebChatHelper.ajaxJson(result.toString(), response);
	}
	
	@RequestMapping("/user")
	public String insertUserInfoShow(){
		return "user";
	}
	
	@RequestMapping("/userList")
	public String userListShow(){
		return "userlist";
	}
	
	@RequestMapping("/insertUserInfo")
	public String insertUserInfo(UserInfo userInfo, HttpServletResponse response){
		JSONObject result = new JSONObject();
		boolean insertStatus = userInfoService.insertUserInfo(userInfo);
		if(insertStatus = true){
			result.put("success", true);
			result.put("message", "保存成功");
		}else{
			result.put("success", false);
			result.put("message", "保存失败");
		}
		return WebChatHelper.ajaxJson(result.toString(), response);
	}
	
	@RequestMapping("/updateUserInfo")
	public String updateUserInfo(UserInfo userInfo, HttpServletResponse response){
		JSONObject result = new JSONObject();
		boolean updateStatus = userInfoService.updateUserInfo(userInfo);
		if(updateStatus = true){
			result.put("success", true);
			result.put("message", "修改成功");
		}else{
			result.put("success", false);
			result.put("message", "修改失败");
		}
		return WebChatHelper.ajaxJson(result.toString(), response);
	}
	
	@RequestMapping(value = "/getUserInfoById", method = RequestMethod.POST)
	public String getUserInfoById(String id, HttpServletResponse response){
		JSONObject result = new JSONObject();
		result = userInfoService.getUserInfoById(id);
		if(result != null){
			result.put("success", true);
		}else{
			result.put("success", false);
		}
		return WebChatHelper.ajaxJson(result.toString(), response);
	}
	
	@RequestMapping("/getAllOfUserInfo")
	public String getAllOfUserInfo(HttpServletResponse response){
		JSONArray result = new JSONArray();
		result = userInfoService.getAllOfUserInfo();
		JSONObject json = new JSONObject();
		if(result.size()>0){
			json.put("success", true);
			json.put("message", "查找成功");
		}else{
			json.put("success", false);
			json.put("message", "查找失败");
		}
		result.add(json);
		return WebChatHelper.ajaxJson(result.toString(), response);
	}
}
