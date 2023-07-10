package com.spring.annotation;

import org.springframework.stereotype.Component;

//어노테이션 () 괄호 안에 이름 붙이기 / 여러개 사용시 (value="tv")
@Component("tv")
public class SamsungTV implements TV {

	@Override
	public void on() {
		System.out.println("@ 방식 : 삼성 TV를 켭니다.");
	}

}
