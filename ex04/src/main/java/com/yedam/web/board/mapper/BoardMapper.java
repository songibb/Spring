package com.yedam.web.board.mapper;

import java.util.List;

import com.yedam.web.board.service.BoardVO;

public interface BoardMapper {
	//전체 조회
	public List<BoardVO> selectBoardAllList();
	
	//단건 조회
	public BoardVO selectBoard(BoardVO boardVO);
	
	//등록
	// 1) 게시글 번호는 자동생성
	// 2) 테이블을 참조해서 필수값과 옵션값을 구분 -> dynamic sql 사용
	public int insertBoard(BoardVO boardVO);
	
	//수정
	// 1) 수정 대상 항목 - 제목, 내용, 이미지
	// 2) updatedate 수정날짜는 항상 수정
	public int updateBoard(BoardVO boardVO);
	
	//삭제
	public int deleteBoard(int bno);
}
