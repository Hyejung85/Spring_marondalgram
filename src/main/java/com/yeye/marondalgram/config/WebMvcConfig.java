package com.yeye.marondalgram.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**") // 내가 url로 접근하고 싶은 path /images/10_1111111/test.png
		.addResourceLocations("file:///C:\\Users\\82109\\Desktop\\spring\\spring_test\\upload\\marondalgram\\images/");
	}
	
}
