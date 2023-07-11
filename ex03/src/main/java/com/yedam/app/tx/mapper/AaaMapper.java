package com.yedam.app.tx.mapper;

import org.apache.ibatis.annotations.Insert;

public interface AaaMapper {
	
	//@이용해서 mapper.xml만들지 않고 간단한 sql문 만듦
	@Insert("INSERT INTO aaa VALUES ( #{value} )")
	public void insert(String value);
}
