package com.yedam.app.dept.service;

import java.util.List;

public interface DeptService {
	//전체 조회
	public List<DeptVO> getDeptAll();
	
	//단건 조회
	public DeptVO getDept(DeptVO deptVO);
	
	//등록
	public int insertDept(DeptVO deptVO);
}
