package com.quant.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quant.business.user.service.UserService;
import com.quant.common.QIResponse;
import com.quant.common.returncode.CommonCode;
import com.quant.common.util.StringUtil;
import com.quant.dal.dataobject.MTUser;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	/**
	 * 用户登录 POST
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/login", method = RequestMethod.POST)
	@ResponseBody
	public QIResponse login(MTUser user, HttpServletRequest request){
		QIResponse res = new QIResponse();
		//判断email passwd为空
		if(StringUtil.isEmpty(user.getName()) 
				|| StringUtil.isEmpty(user.getPasswd())){
			res.getMeta().setCode(CommonCode.LACK_PARAM);
			res.getMeta().setSuccess(CommonCode.FALSE);
			res.getMeta().setMessage("用户名和密码不为空！");
			return res;
		}
		
		return userService.login(user);
	}
	
	/**
	 * 用户注册 POST
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/register", method = RequestMethod.GET)
	@ResponseBody
	public QIResponse register(MTUser user, HttpServletRequest request){
		QIResponse res = new QIResponse();
		//判断为空
		if(StringUtil.isEmpty(user.getName()) 
				|| StringUtil.isEmpty(user.getPasswd())
				|| StringUtil.isEmpty(user.getTel())){
			//|| StringUtil.isEmpty(user.getEmail()) 
			res.getMeta().setCode(CommonCode.LACK_PARAM);
			res.getMeta().setSuccess(CommonCode.FALSE);
			res.getMeta().setMessage("用户名、密码、手机号不为空！");
			return res;
		}
		
		return userService.register(user);
	}
	
	
	public static void main(String[] args) {
		System.out.println(StringUtil.isEmpty("wo"));
	}
}
