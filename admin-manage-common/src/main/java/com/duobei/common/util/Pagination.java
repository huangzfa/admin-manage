package com.duobei.common.util;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class Pagination implements Serializable {
	private static final long serialVersionUID = -3294096020910905389L;

	public static final String ORDER_ASC = "ASC";

	public static final String ORDER_DESC = "DESC";

	/**
	 * 默认分页大小. 无需分页时，为0时则表示无需分页。
	 */
	private int pagesize = 0;

	/**
	 * 当前页
	 */
	private int page = 1;

	/**
	 * 记录总数
	 */
	private int total;

	/**
	 * 需要排序的字段
	 */
	private String sort;

	/**
	 * 排序方式。ASC or DESC(默认)。
	 */
	private String order = ORDER_DESC;

	/**
	 * @return 从第几条记录开始显示
	 */
	public int getStart() {
		return getOffset() + 1;
	}

	/**
	 * 记录起始索引
	 * 
	 * @return
	 */
	public int getOffset() {
		return (page - 1) * pagesize;
	}

	/**
	 * @return 显示到第几条记录结束
	 */
	public int getEnd() {
		return page * pagesize;
	}

	/**
	 * @return 当前页
	 */
	public int getPage() {
		if (page > getMaxPageNumber()) {
			page = getMaxPageNumber();
		}
		return page;
	}

	/**
	 * @param pageNum
	 *            当前页
	 */
	public void setPage(int pageNum) {
		if (pageNum <= 0) {
			pageNum = 1;
		}
		this.page = pageNum;
	}

	/**
	 * @return 总页数
	 */
	public int getTotalPage() {

		if (pagesize == 0) {
			return total != 0 ? 1 : 0;
		}

		if (0 == total % pagesize) {
			return total / pagesize;
		} else {
			return (total / pagesize) + 1;
		}
	}

	/**
	 * 获得总页数
	 */
	public static int getTotalPage(int totalCount, int pageSize) {
		int totalPage = 0;
		if (totalCount % pageSize == 0) {
			totalPage = totalCount / pageSize;
		} else {
			totalPage = totalCount / pageSize + 1;
		}
		return totalPage;
	}

	/**
	 * @param order
	 *            排序方式。ASC或DESC
	 */
	public void setOrder(String order) {
		this.order = order;
	}



	/**
	 * 返回下一页页码
	 * 
	 * @return
	 */
	public int getNextPageNumber() {
		int nextPageNumber = page + 1;
		if (nextPageNumber > getMaxPageNumber()) {
			nextPageNumber = getMaxPageNumber();
		}
		return nextPageNumber;
	}

	/**
	 * 返回上一页页码
	 * 
	 * @return
	 */
	public int getPreviousPageNumber() {
		int previousPageNumber = page - 1;
		if (previousPageNumber < 1) {
			previousPageNumber = 1;
		}
		return previousPageNumber;
	}

	/**
	 * 返回最大页码编号
	 * 
	 * @return
	 */
	public int getMaxPageNumber() {
		int maxPageNumber = 1;
		if (total > pagesize) {
			if (0 == total % pagesize) {
				maxPageNumber = total / pagesize;
			} else {
				maxPageNumber = total / pagesize + 1;
			}
		}
		return maxPageNumber;
	}

}
