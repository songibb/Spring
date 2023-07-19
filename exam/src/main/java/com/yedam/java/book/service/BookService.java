package com.yedam.java.book.service;

import java.util.List;

public interface BookService {
	//도서번호
	public int selectBookNo();
	
	//도서 정보 등록
	public int insertBookInfo(BookVO bookVO);
	
	//도서 목록 조회
	public List<BookVO> getBookAll();
	
	//대여 현황 조회
	public List<RentVO> getRentAll();
}
