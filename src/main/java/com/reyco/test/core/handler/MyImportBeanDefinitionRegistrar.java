package com.reyco.test.core.handler;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(MapperScan.class.getName());
		String[] basePackages = (String[]) annotationAttributes.get("value");
		
		ClassPathMapperScanner reycoMapperScanner = new ClassPathMapperScanner(registry);
		reycoMapperScanner.addIncludeFilter(new TypeFilter() {
			@Override
			public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory)
					throws IOException {
				return true;
			}
		});
		int scan = reycoMapperScanner.scan(basePackages);
		logger.debug("scan:"+scan);
	}

}
