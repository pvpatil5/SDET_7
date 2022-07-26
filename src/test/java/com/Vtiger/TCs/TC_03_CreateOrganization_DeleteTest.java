package com.Vtiger.TCs;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.ObjectRepo.HomePage;
import com.ObjectRepo.OrganizationCreatePage;
import com.ObjectRepo.OrganizationPage;
import com.ObjectRepo.ResponsePage;
import com.vtiger.GenericLib.BaseUtilityClass;
import com.vtiger.GenericLib.RetryAnalyser;
import com.vtiger.GenericLib.WebDriverUtility;
import com.vtiger.GenericLib.fakeData;

public class TC_03_CreateOrganization_DeleteTest extends BaseUtilityClass {


	@Test(priority = 3,retryAnalyzer = RetryAnalyser.class)
	public void Endtoend() throws InterruptedException {

		fakeData fakerdata=new fakeData();
		String Org_Name=fakerdata.companyName();

		HomePage homepage=new HomePage(driver);
		homepage.getOrganization_Linkbutton().click();

		OrganizationPage organizationpage=new OrganizationPage(driver);
		organizationpage.getCreate_Organization_linkButton().click();

		OrganizationCreatePage organizationCreatePage=new OrganizationCreatePage(driver);
		organizationCreatePage.getOrganization_name_Fill_TextBox().sendKeys(Org_Name);
		WebElement DD=organizationCreatePage.getIndustryDD();
		DD.click();
		WebDriverUtility webutil=new WebDriverUtility(driver);
		webutil.select_dd(DD, 7);
		organizationCreatePage.getSave_Button().click();

		//validation statement
		ResponsePage organizationResponsePage=new ResponsePage(driver);
		String confirm_message=organizationResponsePage.getHeader_output().getText();

		if(confirm_message.contains(Org_Name))
		{
			System.out.println("Organization is added and verified Passed");
		}
		else 
		{
			System.out.println("Organization is not added and verified Failed");
		}
		homepage.getOrganization_Linkbutton().click();
		organizationpage.getSearchOrgTextBox().sendKeys(Org_Name);
		WebElement Type_of_Organization_Dd=organizationpage.getTypeofOrg_DD();
		webutil.select_dd(Type_of_Organization_Dd, "accountname");
		organizationpage.getSearchbtn().click();
		Thread.sleep(4000);
		organizationpage.getSearchOrgcheckbox().click();
		organizationpage.getDeleteCheckboxbtn().click();
		webutil.alertAccept();
		Thread.sleep(2000);
		organizationpage.getSearchOrgcheckbox().click();
		WebElement info_data=organizationResponsePage.getConfirmMassege();
		String output=info_data.getText();

		Assert.assertEquals("No Organization Found !", output);

	}

}
