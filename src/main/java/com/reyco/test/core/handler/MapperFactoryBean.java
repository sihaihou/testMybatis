package com.reyco.test.core.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.springframework.beans.factory.FactoryBean;

import com.reyco.test.core.dao.UserDao;

@SuppressWarnings("all")
public class MapperFactoryBean implements FactoryBean{
	
	private Class target;
	
	public MapperFactoryBean(Class target) {
		this.target = target;
	}
	
	@Override
	public Object getObject() throws Exception {
		Class[] clazz = new Class[]{target};
		Object proxyDao = Proxy.newProxyInstance(this.getClass().getClassLoader(),clazz,new ReycoInvocationHandler());
		return proxyDao;
	}

	@Override
	public Class getObjectType() {
		return target;
	}
	@Override
	public boolean isSingleton() {
		return true;
	}
}
