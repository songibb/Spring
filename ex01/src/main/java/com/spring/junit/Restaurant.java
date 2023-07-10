package com.spring.junit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Restaurant {
	
	@Autowired   //기존에 등록되어있는 빈 중에서 Chef를 적절하게 주입하겠다
	Chef chef;
	
	public void open() {
		//메소드를 호출하는 시점에 Chef가 있어야하는데 이 코드는 보장하지 않음
		chef.cooking();
	}
}
