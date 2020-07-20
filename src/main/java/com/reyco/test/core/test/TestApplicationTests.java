package com.reyco.test.core.test;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.reyco.test.core.TestApplication;
import com.reyco.test.core.domain.User;
import com.reyco.test.core.service.UserService;


@SuppressWarnings("all")
public class TestApplicationTests {
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestApplication.class);
		UserService userService = (UserService)applicationContext.getBean(UserService.class);
		List<User> users = userService.queryUser(3);
		for (User user : users) {
			System.out.println(user);
		}
		
		System.out.println("-----------------------------------------------------------------");
		users = userService.queryOne(1);
		for (User user : users) {
			System.out.println(user);
		}
		
		System.out.println("-----------------------------------------------------------------");
		users = userService.queryRole(3);
		for (User user : users) {
			System.out.println(user);
		}
	}
}