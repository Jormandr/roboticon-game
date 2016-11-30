package com.jormandr.testing;

import com.jormandr.*;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.EmptyStackException;
// assumes libgdx is tested and working

public class Test {
	// Test class, tests for individual functions denoted as test_fnname
	// log is refreshed each time, to store permanently, add fn or save screen
	String log[] = new String[100];
	private int count[] = new int[100];
	String keys[] = new String[100];
	private int maxint = Integer.MAX_VALUE;

	// private int limit=0;
	public enum testfn {
		// testfunction names
		TEST
	}

	void intvaltest(int cstop) {
		// tests if the machine's max integer value is greater than the counter
		// limit
		System.out.println("integer limit");
		if (cstop > maxint) {
			System.out.println("fail:int error");
		} else {
			System.out.println("pass: max value " + maxint);
		}
	}

	// stores detailed error report void debug_mode(testfn fn){ }
	void looptest(testfn fn) {
		// looptests the function given by the enum 100 times
		System.out.println("looptest run");
		// selects function to loop
		for (int i = 0; i < 100; i++) {
			try {
				switch (fn) {
				// cases for each test function
				case TEST: {
					// test case for testing the test function
					if (i % 4 == 0) {
						throw new NullPointerException();
					} else if (i % 4 == 1) {
						throw new EmptyStackException();
					} else if (i % 4 == 2) {
						throw new ClassCastException();
					}
					break;

				}
				}
				// stores each iteration to the log array
				log[i] = "ok";
			} catch (Exception e) {
				log[i] = e.toString();
			}
		}
	}

	int[] errorfrequency() {
		// groups the log by error type and prints the frequency of occurence
		// format: times_occured errorname (ok means no error)
		System.out.println("frequency-type report");
		int j;
		for (int i = 0; i < 100; i++) {
			for (j = 0; j < 100; j++) {
				if (keys[j] == null) {
					keys[j] = log[i];
					break;
				} else if (keys[j].equals(log[i])) {
					break;
				}
			}
			count[j]++;
		}
		for (int i = 0; i < 100; i++) {
			if (keys[i] == null) {
				break;
			}
			System.out.println(count[i] + "      " + keys[i]);
			printreport(count[i] + "      " + keys[i]);
		}
		return count;
	}

	int median() {
		return 0;
	}

	int mode() {
		return 0;
	}

	void assetcheck() {
		// test asset paths
	}

	void alltest() {
		// run all tests once
	}

	public void printreport(String text) {
		try (FileWriter fw = new FileWriter("testlog.txt", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			out.println(text);
		} catch (IOException e) {
			System.out.println("logfile error");
		}

	}
}
