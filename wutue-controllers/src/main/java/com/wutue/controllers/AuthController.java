package com.wutue.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wutue.common.utils.SessionService;
import com.wutue.controllers.filter.UnExceptionHandler;
import com.wutue.model.JsonResponse;
import com.wutue.model.vo.LoginUserVM;
import com.wutue.pojo.User;
import com.wutue.service.ILoginService;
import org.apache.log4j.Logger;

@Controller
@RequestMapping("/auth")
public class AuthController {

	private static Logger logger = Logger.getLogger(AuthController.class);
	
	@Autowired 
	ILoginService _loginService;

	@Autowired 
	SessionService _sessionHelper;
	
	@RequestMapping(value = "register.html")
	public String register() {
		return "auth/register";
	}

	/**
	 * 创建用户
	 * @param username
	 * 用户名
	 * @param password
	 * 密码
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "register.do")
	public JsonResponse register(String username, String password) {
		JsonResponse _jsonResponse = new JsonResponse();
		try {
			_loginService.Register(username, password);
		} catch (Exception e) {
			_jsonResponse.setStatus("300");
			_jsonResponse.setMessage(e.getMessage());
		}
		return _jsonResponse;
	}

	@RequestMapping(value = "login.html")
	public String login() {
		return "auth/login";
	}
	

	@ResponseBody
	@RequestMapping(value = "/login.do")
	public JsonResponse login(String username, String password) throws Exception {	
		JsonResponse _jsonResponse = new JsonResponse();
		try {
			String message = "";
			LoginUserVM  loginUser=_loginService.Login(username, password);
			_sessionHelper.AddSessionUser(loginUser);
			
			logger.info(loginUser);
			switch(loginUser.getUser().getType()) {
			  case 1: // 管理员
				  message = "admin/index.do";
				  break;
			  case 2: // 会员
				  message = "/";
				  break;
			  default:
				  break;
			}
			_jsonResponse.setMessage(message);
		} catch (Exception e) { 	
			_jsonResponse.setStatus("300");
			_jsonResponse.setMessage(e.getMessage());
		}
		return _jsonResponse;
	}
	
	@ResponseBody
	@RequestMapping(value = "user.do")
	public JsonResponse user() {
		JsonResponse _jsonResponse = new JsonResponse();
		try {
			User user = new User();
			LoginUserVM loginUserVM = (LoginUserVM)_sessionHelper.GetSessionUser();
			user.setId(loginUserVM.getUser().getId());
			user.setName(loginUserVM.getUser().getName());
			user.setAccount(loginUserVM.getUser().getAccount());
			user.setSex(loginUserVM.getUser().getSex());
			_jsonResponse.setUser(user);
		} catch (Exception e) {
			_jsonResponse.setStatus("300");
			_jsonResponse.setMessage(e.getMessage());
		}
		return _jsonResponse;
	}
}
