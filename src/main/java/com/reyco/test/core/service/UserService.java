package com.reyco.test.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reyco.test.core.dao.ReycoDao;
import com.reyco.test.core.dao.RoleDao;
import com.reyco.test.core.dao.UserDao;
import com.reyco.test.core.domain.User;

@Service
public class UserService{

	@Autowired
	private UserDao userDao;
	@Autowired
	private ReycoDao reycoDao;
	@Autowired
	private RoleDao roleDao;
	
	public List<User> queryUser(Integer id) {
		List<User> daoValue = userDao.query(id);
		return daoValue;
	}
	public List<User> queryOne(Integer id) {
		List<User> daoValue = reycoDao.query(id);
		return daoValue;
	}
	public List<User> queryRole(Integer id) {
		List<User> daoValue = roleDao.query(id);
		return daoValue;
	}
}
