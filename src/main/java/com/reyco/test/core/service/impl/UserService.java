package com.reyco.test.core.service.impl;

import java.util.List;

import com.reyco.test.core.domain.User;

/**
*@author reyco
*@date  2021年1月12日---下午4:49:08
*<pre>
*
*<pre> 
*/
public interface UserService {
	public List<User> queryUser(Integer id);
	
	public User queryOne(Integer... ids);
	
	public List<User> queryRole(Integer id);
}
