package com.reyco.test.core.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.reyco.test.core.annotation.Select;
import com.reyco.test.core.domain.User;

public class ReycoInvocationHandler implements InvocationHandler {

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		String sql = method.getAnnotation(Select.class).value()[0];
		Object parames = args[0];
		System.out.println("sql:"+sql);
		System.out.println("parameters:"+parames);
		return doInvoke(sql, parames);
	}

	private Object doInvoke(String sql, Object args) {
		List<User> users = null;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://182.61.13.51:3306/test", "root", "Reyco123456.");
			ps = (PreparedStatement) c.prepareStatement(sql);
			// 把id 大于2的记录都取出来
			ps.setObject(1, args);
			rs = ps.executeQuery();
			User user = null;
			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				if(users==null) {
					users = new ArrayList<>();
				}
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) {
					rs.close();
				}
				if(ps!=null) {
					ps.close();
				}
				if(c!=null) {
					c.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return users;
	}
}
