package com.yedam.app.tx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yedam.app.tx.mapper.AaaMapper;
import com.yedam.app.tx.service.AaaService;

@Service    //구현클래스
public class AaaServiceImpl implements AaaService {
	
	@Autowired
	AaaMapper aaaMapper;
	
	//@Transactional => 해당 메소드(클래스 단위도 가능)가 가진 트랜잭션이 하나로 묶임 -> 결과가 실패되면 해당 메소드 전체가 실행안되는 것과 같음
	//mapper에서 동작하지 않음, @Service 밑에서만 동작
	@Transactional
	@Override
	public void insert() {
		aaaMapper.insert("101");
		//aaaMapper.insert("a101");    //@Transactional 안붙이면, 개별 개별 rollback이 일어나서 여기서 오류나면 위에 값(101)은 들어감
		aaaMapper.insert("987");
	}

}
