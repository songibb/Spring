package com.yedam.web.user.mapper;

import com.yedam.web.user.service.MemberVO;

public interface UserMapper {
	public MemberVO getMember(String username);
}
