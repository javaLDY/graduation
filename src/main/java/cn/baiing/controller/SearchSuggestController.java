package cn.baiing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.baiing.service.SearchSuggestService;

@Controller
@RequestMapping("/search")
public class SearchSuggestController {

	@Autowired
	private SearchSuggestService searchSuggestService;

	@RequestMapping("/suggest")
	public String searchSuggest(Model model) {
		String keyword = "t";
		long locationId = 31399l;
		int channel = 2;
		String result = searchSuggestService.searchSuggest(keyword, locationId, channel);
		if (result != null) {
			model.addAttribute("list", result);
			model.addAttribute("success", true);
		} else {
			model.addAttribute("success", false);
		}

		return "";
	}

}
