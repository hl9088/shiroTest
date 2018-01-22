package com.shiro.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shiro.service.IUserService;

@Controller
public class UserController {

	@Autowired
	private IUserService userService;
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request) throws Exception{
		String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
		userService.getUserInfo("11");
		if(exceptionClassName!=null){
			if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
				
			} else if (IncorrectCredentialsException.class.getName().equals(
					exceptionClassName)) {
				
			} else if("randomCodeError".equals(exceptionClassName)){
				
			}else {
				
			}
		}
		return "login";
	}

}
