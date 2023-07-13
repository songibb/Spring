package com.yedam.app.emp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yedam.app.emp.service.EmpVO;

public interface EmpMapper {
	//전체 조회
	public List<EmpVO> selectEmpAllList();
	
	//단건 조회
	public EmpVO selectEmpInfo(EmpVO empVO);
	
	//등록
	public int insertEmpInfo(EmpVO empVO);
	
	//수정 - 급여를 정해진 비율로 인상
	//매개변수의 값이 둘 이상이 되면 @Param("sql문에서 사용할 이름")을 이용 -> 정확히 파악하기 위해서
	//@Param(클래스)도 가능 -> 클래스이름.필드명도 가능
	//public int updateEmpSal(@Param("empId") int employeeId, @Param("raise") int raise);
	public int updateEmpSal(@Param("emp") EmpVO empVO, @Param("raise") int raise);
	
	//수정 - 사원정보를 수정
	public int updateEmpInfo(EmpVO empVO);
	
	//삭제
	//삭제는 primary key 외에는 매겨변수 사용안하는 것이 좋음
	public int deleteEmpInfo(int employeeId);
}
