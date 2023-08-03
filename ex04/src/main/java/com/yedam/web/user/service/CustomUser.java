package com.yedam.web.user.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUser implements UserDetails {
	
	private MemberVO member;
	
	//생성자를 통해 MemberVO를 내부에 가지도록
	public CustomUser(MemberVO member) {
		this.member = member;
	}
	
	//로그인 하면서 MemberVO가 select되고 customUser에 들어감
	//그 정보 이용하기 위해 꺼내는 부분
	public MemberVO getMemberInfo() {
		return this.member;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> auth = new ArrayList<>();
		
		//security내에서 부여된 클래스 형태에 정보를 집어넣어서 감쌈	
		//부여된 권한이 여러개라면 반복문 돌려야함
		auth.add(new SimpleGrantedAuthority(member.getRole()));
		return auth;
	}

	@Override
	public String getPassword() {
		return this.member.getPwd();
	}

	@Override
	public String getUsername() {
		return this.member.getId();
	}

	//사용자를 허용하는지 여부
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
