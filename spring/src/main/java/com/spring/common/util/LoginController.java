package com.spring.common.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
	
	private static final Logger logger = Logger.getLogger(LoginController.class);
	
	
	@RequestMapping(value = "/manager/login", method = RequestMethod.GET)
	public String managerLogin(HttpServletRequest request, HttpServletResponse response) {
		/*logger.info("/manager/login, managerLogin");
		Map<String, String> params = HttpRequestUtil.getParameterMap(request);
		String managerName = params.get("managerName");
		String password = params.get("password");
		
		String res = null;*/
		return "jsp/login";
	}

}
