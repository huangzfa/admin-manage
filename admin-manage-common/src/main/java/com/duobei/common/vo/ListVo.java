package com.duobei.common.vo;

import java.util.ArrayList;
import java.util.List;

public class ListVo<T> {

	public ListVo() {
		
	}
	public ListVo(int total, List<T> rows) {
		this.total = total;
		this.rows = rows;
	}
	private int total;
	private List<T> rows;
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<T> getRows() {
		if (rows==null) {
			return new ArrayList<T>();
		}
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}
