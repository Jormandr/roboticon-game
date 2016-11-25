package com.jormandr.testing;
import com.jormandr.testing.Test;
public class TestRun {
	
	public static void main(String[] args) {
		int x[];
		Test t1=new Test();
		// TODO Auto-generated method stub
		t1.looptest(Test.testfn.TEST);
		x=t1.errorfrequency();
		t1.intvaltest();
	}

}
