package com.yedam.web.common;

import lombok.Getter;

@Getter
//내부값 변경이 필요한 경우가 잘 없어서 생성자에서만 값을 변경시키고 읽어들이기만 하기 위해 getter만 설정
public class PagingVO {
	private final static int defaultVal = 10;
	
	private int totalData;		//현재 총 데이터 수
	
	private int nowPage;		//현재 페이지
	private int cntPage = 10;	//View 안에서 보여줄 페이지 수 -> 시작 페이지와 끝 페이지 발생 (직접 결정하는 값)
	private int startPage;		//시작 페이지 = (끝 페이지-5)+1
	private int endPage;		//끝 페이지 = (올림)(현재 페이지/5)*5
	
	private int cntPerPage;		//한 페이지 안에 보여줄 데이터 수   
	private int lastPage;		//마지막 페이지 = 총 데이터수/한 페이지에 보여줄 데이터 수
	
	private int start;			//현재 페이지 안에 보여줄 첫번째 데이터
	private int end;			//현재 페이지 안에 보여줄 마지막 데이터
	
	//생성자를 통해서만 값을 넘김	
	public PagingVO(int totalData, int nowPage, int cntPerPage) {
		this.totalData = totalData;
		this.nowPage = nowPage;
		this.cntPerPage= cntPerPage;
		
		//생성하는 순간 모든 필드에 값이 들어가야함 -> 연산작업이 필요
		calcLastPage();
		calcStartEndPage();
		calcStartEnd();
	}
	
	public PagingVO(int totalData, int nowPage) {
		//보여줄 데이터 개수를 정해놓을 때
		this(totalData, nowPage, defaultVal);
	}
	
	
	//제일 마지막 페이지 계산
	private void calcLastPage() {
		//소수점 필요하므로 double로 강제변환 반드시 할 것 -> 올림처리 -> 올림처리하면 double로 반환됨 -> 다시 int로 변환
		this.lastPage = (int)Math.ceil((double)this.totalData / (double)this.cntPerPage);   
	}
	
	//현재 view안에서 시작, 끝 페이지 계산
	//선택 사항-> 데이터의 개수가 정해져있는 경우엔 필요 없을때가 있기 때문
	private void calcStartEndPage() {
		this.endPage = (int)Math.ceil((double)this.nowPage / (double)this.cntPage) * this.cntPage;
		//끝 페이지는 마지막 페이지보다 작아야함
		if(this.endPage > this.lastPage) {
			this.endPage = this.lastPage;
		}
		
		//시작 페이지 마이너스 값이 나오는지 확인
		this.startPage = (this.endPage - this.cntPage) + 1;
		if(this.startPage < 1) {
			this.startPage = 1;
		}
	}
	
	//현재 페이지 안에서 보여질 첫번째 데이터와 마지막 데이터 -> DB쿼리 안에 사용할 start, end
	private void calcStartEnd() {
		this.start = ((this.nowPage - 1) *  this.cntPerPage)+1;
		this.end = this.nowPage * this.cntPerPage;
		//end는 totalData보다 작아야함
		if(this.end > this.totalData) {
			this.end = this.totalData;
		}
	}
}
