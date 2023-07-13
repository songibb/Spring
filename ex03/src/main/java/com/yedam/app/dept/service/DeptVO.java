package com.yedam.app.dept.service;

import lombok.Data;

@Data
public class DeptVO {
	private int departmentId;
	private String departmentName;
	private Integer managerId;
	private Integer locationId;
	
	//input 태그는 값을 넣지 않으면 ''공백이 있음 
	//int 타입은 공백도 null도 허용하지 않아서 Integer를 사용하는 것이 좋음
	//등록 기능 구현시, departmentId는 input태그를 사용하지 않아서 int를 사용해도 문제가 없지만,
	//managerId나 locationId의 경우 Integer를 사용해야 공백의 값을 받을 수 있다.



}
