package com.yedam.app.emp.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	
	//void는 mapping("경로")와 return "경로"가 같을 때만 사용가능
	
	//전체 조회
	@GetMapping("/empList")
	public String getEmpAllList(Model model) {  //받는 건 없고, 돌려 보내줄 건 있음 -> Model
		model.addAttribute("empList", empService.getEmpAll()); 
		return "emp/empList";
		//servlet-context.xml에 view resolve가 경로 앞뒤로 붇여'줌 => 실제 경로 '/WEB-INF/views/emp/empList.jsp'
	}
	
	//단건 조회
	@GetMapping("/empInfo")
	public String getEmpInfo(EmpVO empVO, Model model) {   //커맨드 객체 사용 -> EmpVO
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
					+ "\n등록된 사원의 사번은 " + empId + "입니다.";
		}
		
		rtt.addFlashAttribute("result", result);  //redirect하더라도 그 값이 살아있음
		return "redirect:empList";   //viewResolver가 응답하지 않아 페이지를 찾지 않고 dispatcherServlet에 재요청이 들어감
	}
	
	//수정 - Process -> 단건조회에서 수정을 시도하니까 form호출이 필요 없음
	//1) Client -> (JSON) -> Server  :  @RequestBody
	//2) Server -> (JSON) -> Client  :  @ResponseBody
	//json은 그 자체가 통째로 하나의 문자열 -> key value형태지만 server에서는 그 내부값을 분류할 수 없음 -> get방식 아님 -> @requestBody와 @responseBody필요
	@PostMapping("/empUpdate")
	@ResponseBody	//페이지를 반환하지 않고, 데이터를 json으로 return시 사용
	public Map<String, String> empUpdateProcess(@RequestBody EmpVO empVO){
		return empService.updateEmp(empVO);
	}
	
	//삭제 - Process -> empList에서 개별적으로 삭제
	@PostMapping("/empDelete")   
	//@PostMapping(value = "/empDelete", produces = "text/plain;charset=UTF-8")  -> //responseBody에서 return값이 String인데, 한글을 갖고오려면 이렇게 설정해야함
	@ResponseBody   //페이지 반환이 아니고, map에서 골라 텍스트 값으로 돌려주므로 @ResponseBody 필요
	public String empDeleteProcess(@RequestParam(name= "id") int employeeId) {   //@RequestParam에 name속성의 "id"가 ajax에서 data에 key값이 됨
		Map<String, String> map = empService.deleteEmp(employeeId);
		return map.get("결과");
	}

	
}
