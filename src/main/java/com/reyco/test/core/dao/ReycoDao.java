package com.reyco.test.core.dao;

import java.util.List;

import com.reyco.test.core.annotation.Select;
import com.reyco.test.core.domain.User;

public interface ReycoDao {
	
	@Select("select * from user where id = ?")
	public List<User> query(Integer id);
	
}
