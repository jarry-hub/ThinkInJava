package com.chapter21;

public class P683SerialNumberGenerator {
	private static volatile int serialNumber = 0;
	public static int nextSerialNubmber() {
		return serialNumber++;	//not thread-safe  java�ĵ�����������ԭ���Ե�
	}
}
