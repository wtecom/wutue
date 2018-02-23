package com.wutue.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
/* 
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
 */
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wutue.common.utils.SessionService;
import com.wutue.controllers.annotation.Anonymous;
import com.wutue.model.vo.LoginUserVM;
import com.wutue.model.vo.ModuleView;
import com.wutue.service.IModuleManagerService;
import org.apache.log4j.Logger;

@Controller
@RequestMapping("/home")
public class HomeController {

	private static Logger logger = Logger.getLogger(HomeController.class);
	@Autowired 
	IModuleManagerService _moduleManagerService;

	@Autowired 
	SessionService _sessionHelper;

	/* 
	 @RequestMapping(value = "/index.do")
	 public String Index(ModelMap model){	
		Subject currentUser = SecurityUtils.getSubject();
		Session  session= currentUser.getSession();
		LoginUserVM  loginUserVM = (LoginUserVM)session.getAttribute("currentUser");
		model.addAttribute("modules", loginUserVM.getModules());
		return "home/index";
     }
	 */
	@Anonymous
	@RequestMapping(value = "/index.do")
	public String Index(ModelMap model) throws Exception{		
		LoginUserVM  loginUserVM = (LoginUserVM)_sessionHelper.GetSessionUser();
		model.addAttribute("modules", loginUserVM.getModules());
		logger.info(loginUserVM);
		return "home/index";
	}

	@Anonymous
	@RequestMapping(value = "/main.do")
	public String Main(){	
		return "home/main";
	}

	@Anonymous
	@ResponseBody
	@RequestMapping(value = "/getmodules.do")
	public List<ModuleView> GetModules(int parentId){
		List<ModuleView> moduleViews= _moduleManagerService.LoadByParent(parentId);
		return moduleViews;
	}

	@Anonymous
	@RequestMapping(value = "/menuheader.do")
	public String MenuHeader() {
		return "home/menuheader";
	}
}
