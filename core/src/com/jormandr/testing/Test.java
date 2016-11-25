package com.jormandr.testing;

import java.util.EmptyStackException;

class noexception extends Exception{
	//no exception found
	private static final long serialVersionUID = 1L;

	noexception(String s){
		super(s);
	}
}
public class Test {
	//Test class, tests for individual functions denoted as test_fnname
String log[]=new String[100];
private int count[]=new int[100];
String keys[]=new String[100];
private int maxint=Integer.MAX_VALUE;
private int limit=0;
public enum testfn{
	//testfunction names
	TEST
}
void intvaltest(){
	if(999999>maxint){
		System.out.println("fail:int error");
	}
}
void looptest(testfn fn){
	//selects function to loop
	for(int i=0; i<100; i++){
	try{
		switch(fn){
		//cases for each test function
		case TEST:{
			if (i%4==0){			
			throw new NullPointerException();
			}
			else if (i%4==1){
			throw new EmptyStackException();
			}
			else if (i%4==2){
			throw new ClassCastException();
			}
					
		}
		}
		log[i]="ok";
	}
	catch(Exception e){
		log[i]=e.toString();
	}
}
}
int[] errorfrequency(){
	int j;
	for(int i=0;i<100;i++){
		
		for(j=0;j<100;j++){
			if(keys[j]==null){
				keys[j]=log[i];
				break;
			}
			else if(keys[j].equals(log[i])){
				break;
			}
		}
	count[j]++;	
	}
	for(int i=0;i<100;i++){
		if(keys[i]==null){
			break;
		}
	System.out.println(count[i]+"      "+keys[i]);
	}
	return count;
}

int median(){
	return 0;
}
int mode(){
	return 0;
}
void alltest(){
	//run all tests once
}
}
