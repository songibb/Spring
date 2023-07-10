package com.spring.annotation;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.junit.Restaurant;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class BeanTest {
	//@Test
	public void test() {
		System.out.println("단순 테스트");
	}
	
	@Autowired
	ApplicationContext ctx;
	
	//@Test
	public void createBeanTest() {
		TV tv = (TV)ctx.getBean("xmlTv");
		assertNotNull(tv);
	}
	
	@Autowired  //여러개의 빈 중에 선택해야한다면 field로 들고와서 직접 테스트
	Restaurant res;
	
	@Test
	public void createResuautrantTest() {
		res.open();
	}
	
	
	
}
