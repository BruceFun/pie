package com.pie.home;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ThreadTest extends Thread{
	public void myMethod(Object o){
		System.out.println("My Object");
	}

	public void myMethod(String s){
		System.out.println("My String");
	}
	

	public static void main(String args[]){
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("e");
		list.add("f");
		
		Iterator<String> iterator = list.iterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next().toString());
		}
		
		
	}
}
