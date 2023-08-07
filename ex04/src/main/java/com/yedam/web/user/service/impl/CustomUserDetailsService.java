package com.yedam.web.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.yedam.web.user.mapper.UserMapper;
import com.yedam.web.user.service.CustomUser;
import com.yedam.web.user.service.MemberVO;


public class CustomUserDetailsService implements UserDetailsService {
	//정보를 가져오는 방식을 변경하기 위해 userDetailsService를 건드림
	
	@Autowired
	UserMapper userMapper;
	
	@Override
	//인증 시도하는 사람의 정보가 있는지 없는지 확인후 반환
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			
		//로그인이 인증 시도하는 사람의 정보를 가져옴
		MemberVO vo = userMapper.getMember(username)
;		
		//CustomUser로 감싼 후 return
		return vo == null ? null : new CustomUser(vo);
		
	}

}
