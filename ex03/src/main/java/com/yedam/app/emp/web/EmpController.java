package com.yedam.app.emp.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;

@Controller
public class EmpController {
	
	@Autowired
	EmpService empService;
	
	//GET 방식 (외부에 데이터가 오픈되어도 상관없는 경우, 보안이 전혀 안됨) -> 데이터 조회, 일반페이지 
	//POST 방식 (데이터 자체를 조작하는 경우, GET보다 약간의 보안) -> 등록, 수정, 삭제
	
	//전체 조회
	@GetMapping("/empList")
	public String getEmpAllList(Model model) {  //받는 건 없고, 보내줄 건 있음 -> Model model
		model.addAttribute("empList", empService.getEmpAll()); 
		return "emp/empList";
		//servlet-context.xml에 view resolve가 경로 앞뒤로 붇여줌 => 실제 경로 '/WEB-INF/views/emp/empList.jsp'
	}
	
	//단건 조회
	@GetMapping("/empInfo")
	public String getEmpInfo(EmpVO empVO, Model model) {   //커맨드 객체 사용
		EmpVO findVO = empService.getEmp(empVO);
		model.addAttribute("empInfo", findVO);
		//model에 담으면 비동기처리 불가
		return "emp/empInfo";
	}
	
	//등록 - Form 호출
	@GetMapping("/empInsert")
	public String empInsertForm() {
		return "emp/empInsert";
	}
	
	//등록 - Process
	@PostMapping("/empInsert")
	public String empInsertProcess(EmpVO empVO, RedirectAttributes rtt) {  //Model이 의미가 없음-> RedirectAttributes 사용
		// 가지고온 데이터를 커맨드 객체에 담을 것
		int empId = empService.insertEmp(empVO);
		String result = null;
		if(empId == -1) {
			result = "정상적으로 등록되지 않았습니다.";
		} else {
			result = "정상적으로 등록되었습니다."
					+ "\n 등록된 사원의 사번은 " + empId + "입니다.";
		}
		
		rtt.addFlashAttribute("result", result);  //redirect하더라도 그 값이 살아있음
		return "redirect:empList";   //viewResolver가 응답하지 않아 페이지를 찾지 않고 dispatcherServlet에 재요청이 들어감
	}
	
	//수정 - Process
	//1) Client -> (JSON) -> Server  :  @RequestBody
	//2) Server -> (JSON) -> Client  :  @ResponseBody
	@PostMapping("/empUpdate")
	@ResponseBody	//페이지를 반환하지 않고, 데이터를 json으로 return
	public Map<String, String> empUpdateProcess(@RequestBody EmpVO empVO){
		return empService.updateEmp(empVO);
	}
	
	

	
}
