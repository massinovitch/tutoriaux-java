package com.principal;

import com.thread.TestThread;

public class Test {
	public static void main(String[] args) {
		Thread t = new Thread( new TestThread("A"));
		Thread t2 = new Thread( new TestThread("B"));
		t.start();
		t2.start();
	}
}
