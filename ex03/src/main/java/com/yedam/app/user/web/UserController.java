package com.yedam.app.user.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yedam.app.emp.service.EmpService;
import com.yedam.app.user.service.UserListVO;
import com.yedam.app.user.service.UserVO;

@Controller
public class UserController {
	
	//서비스 기반으로 autowired
	@Autowired
	EmpService empService;
	
	
	
	@RequestMapping("/getObject")   // ("getObject")도 가능 /있어도되고 없어도됨
	public String getCommandObject(UserVO userVO) {
		//객체 -> 커맨드 객체 (@annotation 사용하지 않음)
		//커맨드 객체 이용해서 요청 정보 받기  -> 일부 필드 값이 없어도 상관없음
		System.out.printf("==============%s\n", userVO.getName());
		System.out.printf("==============%d\n", userVO.getAge());
		return "";
	}
	
	@RequestMapping("getList")
	public String getCommandArray(UserListVO listVO) {
		//객체 리스트 -> 커맨드 객체 (@annotation 사용하지 않음)
		for(UserVO userVO : listVO.getList()) {
			System.out.printf("==============%s\n", userVO.getName());
			System.out.printf("==============%d\n", userVO.getAge());
		}
		return "";
	}
	
	
	@RequestMapping("getValues")
	public String getParaValues(@RequestParam(required = false) String name, @RequestParam(defaultValue = "1") Integer age) {  
		//@RequestParam이 붙으면 그 값은 무조건 있어야함
		//필수값을 안넣어도 되는 경우에는 (required = false)를 붙여야함
		//값이 없을 때 디폴트 값으로 넣으려면 (defaultValue = 1)를 붙여야함
		System.out.printf("==============%s\n", name);
		System.out.printf("==============%d\n", age);
		return "";
	}
	
	@RequestMapping("users/{id}")
	public String getPathValues(@PathVariable String id) {
		//(@PathVariable("empid") String id) 이렇게 사용가능
		//경로에 값이 붙어있음 -> 데이터를 숨김 -> 보안때문에 사용빈도가 최근 증가
		System.out.printf("==============%s\n", id);
		return "";
	}
	
	@RequestMapping("getJsonVal")
	public String getJsonValues(@RequestBody UserVO userVO) {
		//@RequestBody를 객체 앞에 붙이거나, 리스트앞에 붙여서 사용
		System.out.printf("==============%s\n", userVO.getName());
		System.out.printf("==============%d\n", userVO.getAge());
		return "";
	}
}
