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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reyco.test.core.annotation.Select;
import com.reyco.test.core.domain.User;

public class MapperProxy implements InvocationHandler {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	static List<Connection> connections = new ArrayList<>(10);
	
	static {
		try {
			for (int i = 0; i < 10; i++) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection c = DriverManager.getConnection("jdbc:mysql://47.114.74.174:3306/test", "root","******");
				connections.add(c);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		String sql = method.getAnnotation(Select.class).value()[0];
		Object parames = args[0];
		logger.debug("SQL:" + sql);
		logger.debug("parameters:" + parames);
		return doInvoke(sql, parames);
	}

	private Object doInvoke(String sql, Object args) {
		List<User> users = null;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = connections.get(0);
			connections.remove(0);
			ps = c.prepareStatement(sql);
			// 把id 大于2的记录都取出来
			ps.setObject(1, args);
			rs = ps.executeQuery();
			User user = null;
			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				if (users == null) {
					users = new ArrayList<>();
				}
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (c != null) {
					connections.add(c);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return users;
	}
}
