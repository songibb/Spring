package com.yedam.app;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yedam.app.emp.mapper.EmpMapper;
import com.yedam.app.emp.service.EmpVO;

//bean으로 등록된 mapper를 확인할 것임
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/database-context.xml")
public class EmpMapperClient {
	
	@Autowired
	EmpMapper empMapper;
	
	@Test
	public void selectAllEmp() {
		//전체 조회
		List<EmpVO> empList = empMapper.selectEmpAllList();
		//데이터가 넘어온다면 List가 비어있지 않을 것 -> empList.isEmpty() => false 
		assertTrue(!empList.isEmpty());
	}
}
