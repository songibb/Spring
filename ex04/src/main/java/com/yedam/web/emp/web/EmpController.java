package com.yedam.web.emp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yedam.web.common.PagingVO;
import com.yedam.web.emp.service.EmpService;
import com.yedam.web.emp.service.EmpVO;

@Controller
public class EmpController {

	@Autowired
	EmpService empService;
	
	//전체조회
	@GetMapping("empList")
	//PagingVO에서 getter만 만들어 놓은 상태 -> 생성자를 기반으로 연산이 일어남 -> 커맨드객체 사용하려면 생성자에 필요한 매개변수값인 totalData가 필요한데, 넘어오는 값에 totalData가 없음 -> 커맨드 객체 사용불가	
	//검색을 하게 되면 또 달라질 수 있음
	public String empList(Model model, 
						  @RequestParam(value="nowPage", defaultValue="1") Integer nowPage,		//defaultValue=값을 아무것도 않넣을 경우 지정되는 값 //RequestParam 사용시에는 int 사용하지말고 Integer사용할 것
						  @RequestParam(value="cntPerPage", defaultValue="10") Integer cntPerPage) {  
		int total = empService.empCount();
		PagingVO pagingVO = new PagingVO(total, nowPage, cntPerPage);
		List<EmpVO> empList = empService.getEmpList(pagingVO);
		
		model.addAttribute("empList", empList);
		model.addAttribute("paging", pagingVO);
		
		return "emp/empList";
	}
}
