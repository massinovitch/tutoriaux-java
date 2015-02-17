package com.thread;

public class TestThread implements Runnable{
	private String name;
	
	public TestThread(String name) {
		this.name = name;
	}

	public void run() {
		for (int i = 0; i < 10; i++)
			System.out.println(this.getName());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
