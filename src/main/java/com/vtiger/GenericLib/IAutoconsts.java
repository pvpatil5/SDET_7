package com.vtiger.GenericLib;

public interface IAutoconsts {
	String currentdirectory=System.getProperty("user.dir");
	
	String propertyfilepth=currentdirectory+"/Data/config.properties";
	String excelpath=currentdirectory+"/Data/InputData.xlsx";
	String screenshotpath=currentdirectory+"/Screenshot/";
	String extentreportpath=currentdirectory+"/ExtentReport/Vtiger.html";

}
