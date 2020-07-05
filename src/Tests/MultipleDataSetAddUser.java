package Tests;

import org.testng.annotations.Test;

import Base.BaseScripts;
import TestData.UserDataProvider;
import atu.testng.reports.ATUReports;
import atu.testng.reports.utils.Utils;


//TODO----Need to check urgently

public class MultipleDataSetAddUser extends BaseScripts{
	
	
	@Test()
	public void start() throws Exception
	{
		try {
			ATUReports.indexPageDescription = "FB Testing Demo";
			ATUReports.setAuthorInfo("Gaurav D", Utils.getCurrentTime(), "1.0");
			String strDescription = "FB Test";
			ATUReports.currentRunDescription = strDescription;
		 
			logger=report.createTest("Adding Multiple User Test");
			
			logger.info("===========================Test Started=======================");
			
		connect().LoginPage().launchBaseUrl("https://opensource-demo.orangehrmlive.com/");
		connect().LoginPage().enterUserNameForOrange("Admin");
		
		connect().LoginPage().enterPasswordForOrange("admin123");
		connect().LoginPage().clickSignIn();
		
		connect().HomePage().clickUser();
	}catch(Exception e)
		{
		 e.getMessage();
		}
	}
	@Test(dependsOnMethods = "start",dataProvider="User",dataProviderClass=UserDataProvider.class)
	public static void addMultipleUser(String usertype,String empName,String username,
			String password,String confirmPassword)
	{
		BaseScripts base=new BaseScripts();
		try {
			ATUReports.indexPageDescription = "FB Testing Demo";
			ATUReports.setAuthorInfo("Gaurav D", Utils.getCurrentTime(), "1.0");
			String strDescription = "FB Test";
			ATUReports.currentRunDescription = strDescription;
		 
			logger=base.report.createTest("Adding Multiple User Test");
			
			logger.info("===========================Test Started=======================");
			
			
			
			base.connect().Adduser().selectUserRole(usertype);
			base.connect().Adduser().enterEmpname(empName);
			base.connect().Adduser().enterUserName(username);
			base.connect().Adduser().enterPassword(password);
			base.connect().Adduser().enterConfirmPassword(confirmPassword);
			
			base.connect().HomePage().verfiyNewUserCreated(username);
			
			logger.info("-========================Test Ended=======================");
			
	}catch(Exception e)
		{
		e.getMessage();
		}
    
}
}
