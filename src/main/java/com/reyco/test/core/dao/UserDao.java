package com.reyco.test.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.reyco.test.core.domain.User;

public interface UserDao {
	
	@Select("select * from user where id > ?")
	public List<User> query(Integer id);
	
}
