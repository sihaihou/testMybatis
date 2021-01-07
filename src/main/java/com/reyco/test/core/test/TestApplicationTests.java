package com.reyco.test.core.test;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.reyco.test.core.TestApplication;
import com.reyco.test.core.domain.User;
import com.reyco.test.core.service.UserService;


@SuppressWarnings("all")
public class TestApplicationTests {
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestApplication.class);
		UserService userService = (UserService)applicationContext.getBean(UserService.class);
		User user = userService.queryOne(3);
		System.out.println(user);
		
		System.out.println("-----------------------------------------------------------------");
		user = userService.queryOne(1);
		System.out.println(user);
		
		System.out.println("-----------------------------------------------------------------");
		List<User> users= userService.queryRole(3);
		for (User u : users) {
			System.out.println(u);
		}
	}
}
