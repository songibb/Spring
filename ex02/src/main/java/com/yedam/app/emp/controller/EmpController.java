package com.yedam.app.emp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yedam.app.emp.mapper.EmpMapper;
import com.yedam.app.emp.service.EmpVO;

//bean으로 등록  -> Controller로 등록해야 그 아래에 등록가능한 어노테이션이 있음
@Controller
public class EmpController {
	
	@Autowired    //만들어놓은 mapper불러오기
	EmpMapper empMapper;
	
	
	//클라이언트에서 서버로 요청을 할때 어떤 방식으로 할지 결정(경로, 메소드는 필수)
	@RequestMapping(value = "emp", method = RequestMethod.GET)   //주소창
	public String empList(Model model, EmpVO empVO) {   //empVO가 클라이언트가 보내온 형태를 받음
		model.addAttribute("emp", empMapper.getEmp(empVO));
		return "emp";   //문자열이지만 jsp파일을 찾음
	}
}
