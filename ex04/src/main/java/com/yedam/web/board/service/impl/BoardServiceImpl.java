package com.yedam.web.board.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.web.board.mapper.BoardMapper;
import com.yedam.web.board.service.BoardService;
import com.yedam.web.board.service.BoardVO;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardMapper boardMapper;
	
	@Override
	public List<BoardVO> getBoardList() {
		return boardMapper.selectBoardAllList();
	}

	@Override
	public BoardVO getBoardInfo(BoardVO boardVO) {
		return boardMapper.selectBoard(boardVO);
	}

	@Override
	public int insertBoardInfo(BoardVO boardVO) {
		int result = boardMapper.insertBoard(boardVO);
		if(result > 0) {
			return boardVO.getBno();   //selectKey의 결과			
		} else {
			return -1;
		}
	}

	@Override
	public int updateBoardInfo(BoardVO boardVO) {
		int result = boardMapper.updateBoard(boardVO);
		if(result > 0) {
			return boardVO.getBno();   //가지고 있던 bno
		} else {
			return -1;
		}
	}

	@Override
	public int deleteBoardInfo(int bno) {
		int result = boardMapper.deleteBoard(bno);
		if(result > 0) {
			return bno;
		} else {
			return -1;
		}
	}

}
