package com.todd.utils.tests;

import java.sql.Timestamp;

import org.junit.Test;

public class Time_test {

	@Test
	public void run(){
		Timestamp t = Timestamp.valueOf("2016-09-09 13:22:22");
		System.out.println(t);
	}
}
