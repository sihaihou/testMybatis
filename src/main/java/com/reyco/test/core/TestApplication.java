package com.reyco.test.core;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.tomcat.util.http.LegacyCookieProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.reyco.test.core.handler.MapperScan;

@SpringBootApplication
@MapperScan("com.reyco.test.core.dao")
public class TestApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws IOException {
		InputStream inputstream = Resources.getResourceAsStream("mybatis.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputstream);
		return sqlSessionFactory;
	}
	
	@Bean
	public CorsFilter corsFilter() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.setMaxAge(18000L);
		config.addAllowedMethod("*");
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}
	/**
	 * 解决cookie域名格式:
	 * 		An invalid domain [] was specified for this cookie问题解决方案
	 * @return
	 */
	@Bean
	public WebServerFactoryCustomizer<TomcatServletWebServerFactory> cookieProcessorCustomizer() {
		return (factory) -> factory.addContextCustomizers(
				(context) -> context.setCookieProcessor(new LegacyCookieProcessor()));
	}
	/**
	 * vue404
	 * @return
	 */
	@Bean
	public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer(){
		return factory -> {
	           ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/index.html");
	           factory.addErrorPages(error404Page);
		};
	}
}
