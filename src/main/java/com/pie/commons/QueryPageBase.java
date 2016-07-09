package com.pie.commons;

public class QueryPageBase {
	// ¹Ø¼ü×Ö
	private String keyWords;
	private String orderField;
	private String orderDerection;
	private String pageNumber = "1";
	private String pageSize = "10";
	public String getKeyWords() {
		return keyWords;
	}
	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}
	public String getOrderField() {
		return orderField;
	}
	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}
	public String getOrderDerection() {
		return orderDerection;
	}
	public void setOrderDerection(String orderDerection) {
		this.orderDerection = orderDerection;
	}
	public String getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(String pageNumber) {
		this.pageNumber = pageNumber;
	}
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	
}
