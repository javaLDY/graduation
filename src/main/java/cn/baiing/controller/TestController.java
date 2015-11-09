package cn.baiing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("test")
public class TestController {

	@RequestMapping("/aa")
	public String test(){
		System.out.println("aaa");
		return null;
	}
	
}
