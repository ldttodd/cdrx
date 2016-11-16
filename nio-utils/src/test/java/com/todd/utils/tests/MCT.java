package com.todd.utils.tests;

import org.junit.Test;

import com.todd.nio.utils.message.MsgContext;

public class MCT {
	@Test
	public void run() throws Exception{
		MsgContext in = new MsgContext();
		String[] a={"123","321"};
		in.addPara("ar", a);
		String[] r = in.getParaArray("ar");
		System.out.println(r[0]+"--------"+r[1]);
		
	}
	
	
}
