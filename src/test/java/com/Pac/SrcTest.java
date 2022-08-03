package com.Pac;

import org.testng.annotations.Test;

public class SrcTest {

	@Test
	public void run() {
		//public static void main(String[] args) {
		//System.out.println("hi");

		String value=System.getProperty("browser");
		System.out.println(value);
		//		String url=System.getProperty("url");
		//		System.out.println(url);

	}

}
