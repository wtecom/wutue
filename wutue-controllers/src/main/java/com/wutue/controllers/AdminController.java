package com.wutue.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wutue.model.PageResponse;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@RequestMapping(value = "/index.do")
	public String Index(ModelMap model) throws Exception {
		PageResponse response = new PageResponse();
		response.setPath("/admin/index.do");
		model.addAttribute("response", response);
		return "admin/index";
	}
}
