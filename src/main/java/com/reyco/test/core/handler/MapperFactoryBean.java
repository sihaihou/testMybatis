package com.reyco.test.core.handler;

import java.lang.reflect.Proxy;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.FactoryBean;

@SuppressWarnings("all")
public class MapperFactoryBean implements FactoryBean {

	private Class MapperInferface;
	
	private SqlSession sqlSession;

	public MapperFactoryBean(Class MapperInferface) {
		this.MapperInferface = MapperInferface;
	}
	
	public void setSqlSession(SqlSessionFactory sqlSessionFactory) {
		this.sqlSession = sqlSessionFactory.openSession();
	}
	
	@Override
	public Object getObject() throws Exception {
		//自定义生成的代理对象
		//Class[] clazz = new Class[] { MapperInferface };
		//return (T)Proxy.newProxyInstance(this.getClass().getClassLoader(), clazz, new MapperProxy());
		
		//mybatis生成的代理对象
		return sqlSession.getMapper(MapperInferface);
	}

	@Override
	public Class getObjectType() {
		return MapperInferface;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
}
