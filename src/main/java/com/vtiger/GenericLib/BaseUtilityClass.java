package com.vtiger.GenericLib;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.ObjectRepo.HomePage;
import com.ObjectRepo.LoginPage;
import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseUtilityClass implements IAutoconsts{

	public  WebDriver driver;
	public static WebDriver sdriver;

	@BeforeSuite(groups = {"smoke","regression"})
	public void connectToDatabase()
	{
		System.out.println("Connected To Database");
	}
	@AfterSuite(groups = {"smoke","regression"})
	public void diconnectToDatabase()
	{
		System.out.println("Disconnected To Database");
	}

	//@Parameters({"browser","url"})
	@BeforeClass(groups = {"smoke","regression"})
	public void  lauchBrowser() throws Throwable
	{
		FileLib flib=new FileLib();
		String browser=flib.readPropertyData(IAutoconsts.propertyfilepth,"browser");

		if(browser.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		else if(browser.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}

		WebDriverUtility webutil=new WebDriverUtility(driver);
		webutil.maximizeWindow();
		webutil.imlicitWait();
		String url=flib.readPropertyData(IAutoconsts.propertyfilepth,"url");
		driver.get(url);
		sdriver=driver;
	}
	@BeforeMethod(groups = {"smoke","regression"})
	public void loginToApp() throws Throwable
	{
		LoginPage loginpage=new LoginPage(driver);
		FileLib flib=new FileLib();
		String un=flib.readPropertyData(IAutoconsts.propertyfilepth,"username");
		String pwd=flib.readPropertyData(IAutoconsts.propertyfilepth,"password");
		loginpage.getloginapp(un, pwd);

	}
	@AfterMethod(groups = {"smoke","regression"})
	public void logout()
	{
		WebDriverUtility webutil=new WebDriverUtility(driver);
		webutil.imlicitWait();
		HomePage homepage=new HomePage(driver);
		webutil.moveToElement(homepage.getLogout_Symbol());
		homepage.getSign_Out_Button().click();
	}
	@AfterClass(groups = {"smoke","regression"})
	public void closebrowser()
	{
		driver.close();
	}

	public static String takeScreenshot(String name) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)BaseUtilityClass.sdriver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String path = IAutoconsts.screenshotpath+name+".png";
		File dest = new File(path);
		Files.copy(src, dest);
		return path;

	}



}


