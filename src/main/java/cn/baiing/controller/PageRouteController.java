package cn.baiing.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 统一控制主页面的跳转
 * 
 * @author xueqi
 *
 */
@Controller
@RequestMapping("/page")
public class PageRouteController {

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(HttpServletRequest request, HttpServletResponse response) {
		return "/main/index";
	}
}
