package com.chang.once;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Test {
	public static void main(String[] args) throws Throwable  {
		
		String message = "  enter\n  enter2\n  enter3\n";
		System.out.print(message);
		
		ArrayList<String> list = new ArrayList<String>();
		list.add("one");
		list.add("two");
		list.add("three");
		list.add("four");
		Iterator<String> it = list.iterator();
		
		while(it.hasNext()) {
			String str = it.next();
			System.out.println(str);
		}
		
		Set<String> set = new HashSet<String>();
		Iterator<String> it2 = set.iterator();
	}
}


