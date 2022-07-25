package com.Pac;

import org.testng.annotations.Test;

import com.vtiger.GenericLib.FileLib;
import com.vtiger.GenericLib.IAutoconsts;

public class timeepass {

	@Test
	public void tt() throws Throwable
	{
		FileLib flib=new FileLib();
		String browser=flib.readPropertyData(IAutoconsts.propertyfilepth, "browser");
		System.out.println(browser);
	}

}
