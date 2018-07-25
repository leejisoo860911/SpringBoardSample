package com.board.domain;

import org.springframework.util.StringUtils;
import org.springframework.web.util.UriComponentsBuilder;

import com.board.constants.PageConstant;
import com.board.utils.StringUtil;

public class PageMaker {
	
	private int totalCount;
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	private int displayPageNum = PageConstant.DEFAULT_DISPLAY_PAGE;
	private PageVo pageVo;
	
	public PageMaker(PageVo pageVo, int totalCount) {
		setPageVo(pageVo);
		setTotalCount(totalCount);
	}
	
	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo;
	}
	
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		processPage();
	}
	
	private void processPage() {
		endPage =  (int) (Math.ceil(pageVo.getPage() / (double) displayPageNum) * displayPageNum);
		startPage = (endPage - displayPageNum) + 1;
		int tempEndPage = (int) (Math.ceil(totalCount / (double) pageVo.getPerPageNum()));
		if(endPage > tempEndPage) {
			endPage = tempEndPage;
		}
		prev = startPage > 1;
		next = endPage * pageVo.getPerPageNum() < totalCount;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public PageVo getPageVo() {
		return pageVo;
	}
	
	public String makePageUri(int page) {
		UriComponentsBuilder builder = UriComponentsBuilder.newInstance();
		builder.queryParam("page", page);
		builder.queryParam("perPageNum", pageVo.getPerPageNum());
		return builder.toUriString();
	}
	
	public String makePageSearchUri(int page) {
		UriComponentsBuilder builder = UriComponentsBuilder.newInstance();
		builder.queryParam("page", page);
		builder.queryParam("perPageNum", pageVo.getPerPageNum());
		if(pageVo instanceof PageSearchVo) {
			if(StringUtil.isNotEmpty(((PageSearchVo)pageVo).getSearchType())) builder.queryParam("searchType", ((PageSearchVo)pageVo).getSearchType());
			if(StringUtil.isNotEmpty(((PageSearchVo)pageVo).getKeyword()))    builder.queryParam("keyword", ((PageSearchVo)pageVo).getKeyword());
		}
		return builder.toUriString();
	}
}
