package com.yedam.java.book.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.java.book.mapper.BookMapper;
import com.yedam.java.book.service.BookService;
import com.yedam.java.book.service.BookVO;
import com.yedam.java.book.service.RentVO;

@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	BookMapper bookMapper;
	
	//도서 번호
	@Override
	public int selectBookNo() {
		return bookMapper.selectBookNo();
	}
	
	//도서 정보 등록
	@Override
	public int insertBookInfo(BookVO bookVO) {
		int result = bookMapper.insertBook(bookVO);
		if(result > 0) {
			return bookVO.getBookNo();
		}
		return -1;
	}
	
	//도서 목록 조회
	@Override
	public List<BookVO> getBookAll() {

		return bookMapper.selectBookAllList();
	}

	
	//대여 현황 조회
	@Override
	public List<RentVO> getRentAll() {
		return bookMapper.selectRentList();
	}
	

}
