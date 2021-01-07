package com.reyco.test.core.handler;

import java.util.Set;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;

/**
 * 自定义mapper扫描器
 * @author reyco
 *
 */
public class ClassPathMapperScanner extends ClassPathBeanDefinitionScanner {
	
	
	public ClassPathMapperScanner(BeanDefinitionRegistry registry) {
		super(registry);
	}
	@Override
	protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
		return beanDefinition.getMetadata().isInterface();
	}
	
	@Override
	protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
		Set<BeanDefinitionHolder> beanDefinitionHolders = super.doScan(basePackages);
		for (BeanDefinitionHolder beanDefinitionHolder : beanDefinitionHolders) {
			GenericBeanDefinition definition = (GenericBeanDefinition) beanDefinitionHolder.getBeanDefinition();
			String beanClassName = definition.getBeanClassName();
			//注册构造器生成bean对象
			definition.getConstructorArgumentValues().addGenericArgumentValue(beanClassName); 
			//注册beanclass类型
		    definition.setBeanClass(MapperFactoryBean.class);
		    //设置属性注入模型
		    definition.setAutowireMode(AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE);
		}
		return beanDefinitionHolders;
	}
}
