package com.yedam.app;

import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringMainExample {

	public static void main(String[] args) {
		GenericXmlApplicationContext ctx
		 = new GenericXmlApplicationContext("classpath:applicationContext.xml");
		
		//bean에 등록되어있는 정보를 읽어들여서 인스턴스에 강제로 주입 -> new 연산자 이용하지 않아도 됨
		TV tv = (TV)ctx.getBean("tv");     //ctx는 기본 object타입 -> 인터페이스로 타입변환
		tv.on();


	}

}
