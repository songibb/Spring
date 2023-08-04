package com.yedam.web;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/*-context.xml")
public class EncryptionTest {
	//properties에 대신 입력할 값 찾기
	
	@Autowired
	StandardPBEStringEncryptor encryptor;
	
	@Test
	public void encryptionTest() {
		//암호화할때 사용하고나서, 배포하거나 git에 올릴때 삭제할 것
		String[] dataList = {
								""
								,""
								,""
								,""
							};
		for(String data : dataList) {
			String encData = encryptor.encrypt(data);
			System.out.println(encData);			
		}
	}
}
