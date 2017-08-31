package com.chang.once;

import java.util.UUID;

public class Test {
	public static void main(String[] args) throws Throwable  {
			String uuid = UUID.randomUUID().toString();
			String res = uuid.replace("-", "");
			System.out.println(res);
		}
	
}


