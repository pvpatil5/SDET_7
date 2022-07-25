package com.vtiger.GenericLib;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listners  extends BaseUtilityClass implements ITestListener 
{
	ExtentSparkReporter reporter;
	ExtentReports reports;
	ExtentTest test;

	@Override
	public void onTestStart(ITestResult result) {
		test=reports.createTest(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS,result.getMethod().getMethodName()+" got Passed" );
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test.log(Status.FAIL,result.getMethod().getMethodName()+" got Fail");
		test.log(Status.FAIL, result.getThrowable());
		//WebDriverUtility webDriverUtility= new WebDriverUtility(driver);
		String path;
		try {
			path=BaseUtilityClass.takeScreenshot(result.getMethod().getMethodName());
			test.addScreenCaptureFromPath(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP,result.getMethod().getMethodName()+" got Skip");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {

		reporter = new ExtentSparkReporter(IAutoconsts.extentreportpath);
		reporter.config().setTheme(Theme.DARK);
		reporter.config().setDocumentTitle("VTIGER APP");
		reporter.config().setReportName("CRM Report");

		reports= new ExtentReports();
		reports.attachReporter(reporter);
		reports.setSystemInfo("Browser", "Chrome");
		reports.setSystemInfo("Build", "15.2.3");
		reports.setSystemInfo("Created by", "Test Team");

	}

	@Override
	public void onFinish(ITestContext context) {
		reports.flush();
	}

}
