package com.reyco.test.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.reyco.test.core.domain.User;

public interface RoleDao {
	
	@Select("select * from user where id<#{id}")
	public List<User> query(Integer id);
	
}
