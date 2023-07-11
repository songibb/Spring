package com.yedam.app.dept.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.dept.mapper.DeptMapper;
import com.yedam.app.dept.service.DeptService;
import com.yedam.app.dept.service.DeptVO;

@Service
public class DeptServiceImpl implements DeptService {
	
	@Autowired
	DeptMapper deptMapper;
	
	@Override
	public List<DeptVO> getDeptAll() {
		return deptMapper.selectDeptAllList();
	}

	@Override
	public DeptVO getDept(DeptVO deptVO) {
		return deptMapper.selectDeptInfo(deptVO);
	}

	@Override
	public int insertDept(DeptVO deptVO) {
		int result = deptMapper.insertDeptInfo(deptVO);
		if(result == 1) {
			return deptVO.getDepartmentId();
		} else {
			return -1;
		}
	}

}
