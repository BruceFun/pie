package com.pie.utils;

/**
 * 当前枚举适用于所有返回枚举的调用
 * 如：成功 FlagEnum.SUCCESS.value();
 * @author bruce_000
 *
 */
public enum FlagEnum {
	SUCCESS(200),  // 成功状态
	ERROR(500),  // 系统内部错误
	NotSupportMethod(405),  // 不支持GET请求
	InvalidParameter(400),  // 参数值不合法
	IsExists(900);  // 已经存在该信息
	
	private final int value;

	private FlagEnum(int value){
		this.value = value;
	}
	public int value(){
		return this.value;
	}
}
