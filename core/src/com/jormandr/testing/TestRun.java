package com.jormandr.testing;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.jormandr.testing.Test;

public class TestRun {
	public static String getCurrentTimeStamp() {
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// dd/MM/yyyy
		Date now = new Date();
		String strDate = sdfDate.format(now);
		return strDate;
	}

	public static void main(String[] args) {

		// start log
		try (FileWriter fw = new FileWriter("testlog.txt", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			out.println(getCurrentTimeStamp());
		} catch (IOException e) {
			System.out.println("logfile error");
		}
		int x[];
		Test t1 = new Test();
		t1.looptest(Test.testfn.TEST);
		x = t1.errorfrequency();
		t1.intvaltest(999999);
	}

}
