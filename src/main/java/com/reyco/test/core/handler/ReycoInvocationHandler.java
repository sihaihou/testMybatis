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

public class ReycoInvocationHandler implements InvocationHandler{
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		String sql = method.getAnnotation(Select.class).value()[0];
		System.out.println(sql);
		List<User> users = doInvoke(sql,args[0]);
		return users;
	}
	
	/**
	 * 真正的执行者
	 * @param sql
	 * @return
	 */
	public List<User> doInvoke(String sql,Object args) {
		List<User> users = null;
		Connection c=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			//加载驱动类
			Class.forName("com.mysql.cj.jdbc.Driver");
			c=DriverManager.getConnection("jdbc:mysql://www.housihai.com:3306/test","root","Reyco123456.");
			ps=(PreparedStatement) c.prepareStatement(sql);
			ps.setObject(1, args);  
			rs=ps.executeQuery();
			User user = null;
			while(rs.next()){
				user = new User();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setPassword(rs.getString(3));
				if(users==null) {
					users = new ArrayList<>();
				}
				users.add(user);
			}
		} catch (SQLException e) {
				e.printStackTrace();		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null){
					rs.close();
				}
				if(ps!=null){
					ps.close();
				}
				if(c!=null){
					c.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return users;
	}
}
