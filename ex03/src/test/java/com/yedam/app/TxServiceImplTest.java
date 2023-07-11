package com.yedam.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yedam.app.tx.service.AaaService;

@RunWith(SpringJUnit4ClassRunner.class)
//locations => spring밑에 있는 모든 context.xml파일이 필요함 (database-context.xml와 servlet-context.xml을 같이 불러와야함)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/**/*-context.xml")
public class TxServiceImplTest {
	
	@Autowired
	AaaService aaaService;
	
	@Test
	public void txTest() {
		aaaService.insert();
	}
}
