package cn.baiing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("graduation")
public class GraduationShowController {

	@RequestMapping("/show")
	public String show(){
		return "show";
	}
	
}
