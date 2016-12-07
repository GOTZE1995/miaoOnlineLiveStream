package com.miao.utils;

import java.util.List;

/**
 * Page分页
 * 
 * @author sunlanyun 2016/11/17
 */
public class Page<T> {
	//保存当前页的对象
	private List<T> list;
	//当前页号
	private int currentPageNum;
	//当前页容量
	private int pageSize = 4;
	//前一页页码
	private int prePageNum;
	//下一页页码
	private int nextPageNum;
	//总页数
	private int totalPageNum;
	//总记录数
	private int totalCount;

	public Page() {
	}

	public Page(int pageNum, int pageSize) {
		this.currentPageNum = pageNum;
		this.pageSize = pageSize;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getCurrentPageNum() {
		return currentPageNum;
	}

	public void setCurrentPageNum(int currentPageNum) {
		this.currentPageNum = currentPageNum;
	}

	public int getTotalPageNum() {
		return totalPageNum;
	}

	public void setTotalPageNum(int totalPageNum) {
		this.totalPageNum = totalPageNum;
	}

	public int getPrePageNum() {
		return prePageNum;
	}

	public void setPrePageNum(int prePageNum) {
		this.prePageNum = prePageNum;
	}

	public int getNextPageNum() {
		return nextPageNum;
	}

	public void setNextPageNum(int nextPageNum) {
		this.nextPageNum = nextPageNum;
	}

	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * 设置总记录数，总页数，当前页码，前一页页码，下一页页码
	 * @param totalCount
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		if (totalCount % pageSize == 0)
			totalPageNum = totalCount / pageSize;
		else
			totalPageNum = totalCount / pageSize + 1;

		if (currentPageNum > 1)
			prePageNum = currentPageNum - 1;
		else
			prePageNum = 1;

		if (currentPageNum < totalPageNum)
			nextPageNum = currentPageNum + 1;
		else
			nextPageNum = totalPageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
