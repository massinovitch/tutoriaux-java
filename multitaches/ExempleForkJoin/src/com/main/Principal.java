package com.main;

import java.io.File;

import com.fork.join.FileSizeTask;

public class Principal {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File file = new File("D:\\Notes de frais");
		FileSizeTask fileSizeTask = new FileSizeTask(file);
		long result = fileSizeTask.compute();
		System.out.println(result);

	}

}
