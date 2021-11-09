package com.reyco.test.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.reyco.test.core.domain.User;

public interface ReycoDao {
	
	@Select({"<script> "+
			"select * from user "+
			"where id in "+
				"<foreach collection='ids' item='id' index='index' open='(' close=')' separator=','>"+
					"#{id}"+
				"</foreach>"+
			"</script> "})
	public User query(@Param("ids") Integer... ids);
	
}
