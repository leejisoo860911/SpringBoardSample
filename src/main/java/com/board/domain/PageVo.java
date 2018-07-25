package com.board.domain;

import com.board.constants.PageConstant;

public class PageVo {
	
	private int page;		// 페이지 번호
	private int perPageNum; // 페이지 당 건수
	
	public PageVo() {
		this.page = PageConstant.DEFAULT_PAGE;
		this.perPageNum = PageConstant.DEFAULT_PER_PAGE_NUM;
	}
	
	public PageVo(Integer page) {
		this.page = page;
		setPerPageNum(PageConstant.DEFAULT_PER_PAGE_NUM);
	}
	
	public PageVo(Integer page, Integer perPageNum) {
		if(page == null) page = PageConstant.DEFAULT_PAGE;
		if(perPageNum == null) perPageNum = PageConstant.DEFAULT_PER_PAGE_NUM;
		setPage(page);
		setPerPageNum(perPageNum);
	}
	
	public void setPage(int page) {
		if(page < 0) page = PageConstant.DEFAULT_PAGE;
		this.page = page;
	}
	
	public int getPage() {
		return page;
	}
	
	public void setPerPageNum(int perPageNum) {
		if(perPageNum < 10) perPageNum = PageConstant.DEFAULT_PER_PAGE_NUM;
		this.perPageNum = perPageNum;
	}
	
	public int getPerPageNum() {
		return perPageNum;
	}
	
	public int getStartRowNum() {
		return (this.page - 1) * this.perPageNum;
	}
	
}
