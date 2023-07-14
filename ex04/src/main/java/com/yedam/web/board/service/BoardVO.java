package com.yedam.web.board.service;

import java.util.Date;

import lombok.Data;

@Data
public class BoardVO {
	private int bno;            //게시글 번호 - primary key
	private String title;		//게시글 제목 - not null
	private String contents;	//게시글 내용
	private String writer;		//게시글 작성자 - not null
	private Date regdate;		//게시글 등록일 - not null
	private Date updatedate;	//게시글 수정일
	private String image;		//게시글 첨부파일
}
