package com.pie.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 用于方法统一返回类型
 * @author bruce_000
 *
 */
public class ResultDataFormat {
	private Integer flag = Integer.valueOf(200); // 返回标识
	private String msg;  // 返回消息
	private Object data;  // 返回数据
	private boolean next = false;  // 是否有下一页
	private Long pageNumber;  // 初始页码
	private Long pageSize;  // 分页大小
	private Long totalPages;  // 总页数
	private Long totalElements;  // 总条数
	
	public ResultDataFormat (String msg,Integer flag) {
		this.msg = msg;
		this.flag = flag;
	}
	public ResultDataFormat (Object data,Integer flag) {
		this.data = data;
		this.flag = flag;
	}
	public ResultDataFormat (String msg,Object data,Integer flag) {
		this.msg = msg;
		this.data = data;
		this.flag = flag;
	}
	public Map<String,Object> convertResultData(){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if(this.msg != null){
			resultMap.put("msg", this.msg);
		}
		if(this.data != null){
			resultMap.put("data", this.data);
		}
		if(this.next){
			resultMap.put("next", Boolean.valueOf(this.next));
		}
		if(this.pageNumber != null){
			resultMap.put("pageNumber", this.pageNumber);
		}
		if(this.pageSize != null){
			resultMap.put("pageSize", this.pageSize);
		}
		if(this.totalPages != null){
			resultMap.put("totalPages", this.totalPages);
		}
		if(this.totalElements != null){
			resultMap.put("totalElements", this.totalElements);
		}
		resultMap.put("flag", this.flag);
		return resultMap;
	}
	
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	public Long getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(Long pageNumber) {
		this.pageNumber = pageNumber;
	}
	public Long getPageSize() {
		return pageSize;
	}
	public void setPageSize(Long pageSize) {
		this.pageSize = pageSize;
	}
	public Long getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(Long totalPages) {
		this.totalPages = totalPages;
	}
	public Long getTotalElements() {
		return totalElements;
	}
	public void setTotalElements(Long totalElements) {
		this.totalElements = totalElements;
	}
}
