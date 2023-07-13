package com.yedam.app.dept.web;

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

import com.yedam.app.dept.service.DeptService;
import com.yedam.app.dept.service.DeptVO;

@Controller
public class DeptController {
	@Autowired
	DeptService deptService;
	
	//전체 조회
	@GetMapping("/deptList")
	public String getDeptAllList(Model model) {
		model.addAttribute("deptList", deptService.getDeptAll());
		return "dept/deptList";
	}
	
	//단건 조회
	@GetMapping("/deptInfo")
	public String getDeptInfo(DeptVO deptVO, Model model) {
		model.addAttribute("deptInfo", deptService.getDept(deptVO));
		return "dept/deptInfo";
	}
	
	//등록 - Form 호출
	@GetMapping("/deptInsert")
	public String deptInsertForm() {
		return "dept/deptInsert";
	}
	
	//등록 - Process
	@PostMapping("/deptInsert")
	public String deptInsertProcess(DeptVO deptVO, RedirectAttributes rtt) {
		int deptId = deptService.insertDept(deptVO);
		String uri = null;
		String result = null;
		if(deptId == -1) {
			result = "정상적으로 등록되지 않았습니다.";	
			uri = "deptList";
		} else {
			result = "정상적으로 등록되었습니다."
					+"\n등록된 부서의 번호는 " + deptId + "입니다.";
			uri = "deptInfo?departmentId=" + deptId;     //deptInfo로 리다이렉트 할려면 값이 필요하므로 경로에 get방식으로 넣으면 됨
		}
		
		rtt.addFlashAttribute("result", result);
		return "redirect:" + uri;
	}
	
	//수정 - Process
	@PostMapping("/deptUpdate")
	@ResponseBody
	public Map<String, String> deptUpdateProcess(@RequestBody DeptVO deptVO){
		return deptService.updateDept(deptVO);
	}
	
	//삭제 - Process
	@PostMapping(value = "/deptDelete", produces = "text/plain;charset=UTF-8")  //responseBody에서 return값이 String인데, 한글을 갖고오려면 이렇게 설정해야함
	@ResponseBody
	public String deptDeleteProcess(@RequestParam int departmentId) {
		Map<String, String> map = deptService.deleteDept(departmentId);
		return map.get("결과");
	}
}

