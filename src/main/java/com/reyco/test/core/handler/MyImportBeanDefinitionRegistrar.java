package com.reyco.test.core.handler;

import java.io.File;
import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import com.reyco.test.core.TestApplication;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar{
	
	private static List<String> beanDefinitions =  new Vector<String>();
	
	public MyImportBeanDefinitionRegistrar() {
		initBeanDefinitions();
	}
	
	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		for (String bean : beanDefinitions) {
			BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(MapperFactoryBean.class);
			BeanDefinition beanDefinition = builder.getBeanDefinition();
			beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(bean);
			registry.registerBeanDefinition(getBeanName(bean), beanDefinition);
		}
	}
	
	/**
	 * 扫描指定包下的。class文件
	 */
	private void initBeanDefinitions(){
		String[] packagePaths = TestApplication.class.getAnnotation(MapperScan.class).value();
		for (String packagePath : packagePaths) {
			initBeanDefinitions(packagePath);
		}
	}
	/**
	 * 扫描指定包下的。class文件
	 * @param packagePath
	 */
	private void initBeanDefinitions(String packagePath){
		String rootPath = this.getClass().getResource("/").getPath();
        String targetPackagePath = rootPath + packagePath.replace(".","/");
        File file = new File(targetPackagePath);
        File[] fileList = file.listFiles();
        for (File wjFile:fileList) {
            if (wjFile.isFile()) {//判断是否为文件
                String targetClass = packagePath+"."+wjFile.getName().replace(".class","");
                if (beanDefinitions==null) {
                	beanDefinitions = new Vector<String>();
                }
                beanDefinitions.add(targetClass);
            }
        }
	}
	/**
	 * 根据类全名获取bean的名称
	 * 
	 * @param tableField
	 *            表字段
	 * @return
	 */
	private static String getBeanName(String beanClass) {
		String BeanName = beanClass.substring(beanClass.lastIndexOf("."));
		byte[] bytes = BeanName.getBytes();
		// 指定索引位置转大写
		bytes[0] = (byte) (bytes[0] + 32);
		// bytes字节数组转String
		return new String(bytes);
	}

}
