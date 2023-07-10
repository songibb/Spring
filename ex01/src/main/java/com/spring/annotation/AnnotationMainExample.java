package com.spring.annotation;

import org.springframework.context.support.GenericXmlApplicationContext;

public class AnnotationMainExample {

	public static void main(String[] args) {
		GenericXmlApplicationContext ctx 
		= new GenericXmlApplicationContext("classpath:applicationContext.xml");
	
		TV tv = (TV)ctx.getBean("tv");
		tv.on();

	}

}
