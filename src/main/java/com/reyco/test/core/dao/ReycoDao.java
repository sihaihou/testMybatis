package com.reyco.test.core.dao;

import org.apache.ibatis.annotations.Select;

import com.reyco.test.core.domain.User;

public interface ReycoDao {
	
	@Select("select * from user where id=#{id}")
	public User query(Integer id);
	
}
