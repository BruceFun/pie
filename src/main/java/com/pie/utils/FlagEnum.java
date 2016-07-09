package com.pie.utils;

/**
 * ��ǰö�����������з���ö�ٵĵ���
 * �磺�ɹ� FlagEnum.SUCCESS.value();
 * @author bruce_000
 *
 */
public enum FlagEnum {
	SUCCESS(200),  // �ɹ�״̬
	ERROR(500),  // ϵͳ�ڲ�����
	NotSupportMethod(405),  // ��֧��GET����
	InvalidParameter(400),  // ����ֵ���Ϸ�
	IsExists(900);  // �Ѿ����ڸ���Ϣ
	
	private final int value;

	private FlagEnum(int value){
		this.value = value;
	}
	public int value(){
		return this.value;
	}
}
