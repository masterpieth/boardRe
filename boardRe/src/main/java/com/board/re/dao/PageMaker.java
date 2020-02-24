package com.board.re.dao;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.board.re.vo.Criteria;

public class PageMaker {

	private Criteria cri;
	private int totalCount;
	private int startPage;
	private int endPage;
	private int displayPageNum = 5;
	private boolean prev;
	private boolean next;

	public Criteria getCri() {
		return cri;
	}

	public void setCri(Criteria cri) {
		this.cri = cri;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcData();
	}

	public void calcData() {
		endPage = (int) Math.ceil((double) cri.getPage() / displayPageNum) * displayPageNum;
		startPage = endPage - displayPageNum + 1;
		if (displayPageNum > endPage)
			startPage = 1;
		int totalPage = (int) Math.ceil(totalCount / (double) cri.getPerPageNum());
		if (endPage > totalPage)
			endPage = totalPage;

		prev = startPage == 1 ? false : true;
		next = endPage * cri.getPerPageNum() >= totalCount ? false : true;
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

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}

	public boolean isPrev() {
		return prev;
	}

	public boolean isNext() {
		return next;
	}

	public String makeQuery(int page) {
		UriComponents uriComponents = UriComponentsBuilder.newInstance().queryParam("page", page)
				.queryParam("parPageNum", cri.getPerPageNum()).build();
		return uriComponents.toString();
	}
}
