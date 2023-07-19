package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication    
//spring boot를 실행하기 위한 component, configuration 등등 모두 스캔. 이 위치부터 하위클래스 전부 스캔하므로 이동이나 삭제 절대 금지!
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
