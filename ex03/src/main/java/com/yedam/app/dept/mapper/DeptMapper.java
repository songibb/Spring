package com.yedam.app.dept.mapper;

import java.util.List;

import com.yedam.app.dept.service.DeptVO;

public interface DeptMapper {
	//전체 부서 조회
	public List<DeptVO> selectDeptAllList();
	
	//단건 부서 조회
	public DeptVO selectDeptInfo(DeptVO deptVO);
	
	//부서 등록
	public int insertDeptInfo(DeptVO deptVO);
	
	//부서 수정
	public int updateDeptInfo(DeptVO deptVO);
	
	//부서 삭제  
	public int deleteDeptInfo(int departmentId);
}
