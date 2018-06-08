package com.jw.util;

/**
 * 分页工具类
 */
public class Pager {
	private Integer pageNo;
	private Integer pageSize;
	private Integer count;
	private Integer pageNum;

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getPageNum() {
		int num = count % pageSize;
		return num == 0 ? count / pageSize : count / pageSize + 1;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	
	public Integer getBeginIndex() {
		return (pageNo - 1) * pageSize;
	}

}
