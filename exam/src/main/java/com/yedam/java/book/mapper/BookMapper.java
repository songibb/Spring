package com.yedam.java.book.mapper;

import java.util.List;

import com.yedam.java.book.service.BookVO;
import com.yedam.java.book.service.RentVO;

public interface BookMapper {
	//도서 번호
	public int selectBookNo();
	
	//도서 정보 등록
	public int insertBook(BookVO bookVO);
	
	//도서 목록 조회
	public List<BookVO> selectBookAllList();
	
	//대여 현황 조회
	public List<RentVO> selectRentList();
	
}
