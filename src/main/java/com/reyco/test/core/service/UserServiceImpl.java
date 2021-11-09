package com.reyco.test.core.service;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reyco.test.core.dao.ReycoDao;
import com.reyco.test.core.dao.RoleDao;
import com.reyco.test.core.dao.UserDao;
import com.reyco.test.core.domain.User;
import com.reyco.test.core.service.impl.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ReycoDao reycoDao;
	
	@Autowired
	private RoleDao roleDao;
	
	public List<User> queryUser(Integer id) {
		return userDao.query(id);
	}
	
	public User queryOne(Integer[] ids) {
		return reycoDao.query(ids);
	}
	
	public List<User> queryRole(Integer id) {
		return roleDao.query(id);
	}
}
