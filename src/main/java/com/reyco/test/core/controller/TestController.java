package com.reyco.test.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reyco.test.core.service.impl.UserService;

@RestController
@RequestMapping("test")
public class TestController{
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("test1")
	public Object test1(Integer id) {
		return userService.queryOne(id);
	}

}
