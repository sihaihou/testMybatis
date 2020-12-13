package com.reyco.test.core.handler;

import java.util.Set;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;

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
		GenericBeanDefinition definition;
		for (BeanDefinitionHolder beanDefinitionHolder : beanDefinitionHolders) {
			definition = (GenericBeanDefinition) beanDefinitionHolder.getBeanDefinition();
			String beanClassName = definition.getBeanClassName();
			definition.getConstructorArgumentValues().addGenericArgumentValue(beanClassName); 
		    definition.setBeanClass(MapperFactoryBean.class);
		}
		return beanDefinitionHolders;
	}
}
