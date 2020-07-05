package Tests;

import org.testng.annotations.Test;

import Base.BaseScripts;
import TestData.UserDataProvider;
import atu.testng.reports.ATUReports;
import atu.testng.reports.utils.Utils;

public class AddOrangeEmployee  extends BaseScripts{
	
	@Test(dataProvider="Users",dataProviderClass=UserDataProvider.class)
	public void AddEmployee(String url, String userName,String password)
	{
	try
	{
		ATUReports.indexPageDescription = "FB Testing Demo";
		ATUReports.setAuthorInfo("Gaurav D", Utils.getCurrentTime(), "1.0");
		String strDescription = "FB Test";
		ATUReports.currentRunDescription = strDescription;
	 
		logger=report.createTest("AutoIT Test");
		
		logger.info("Test Started");
	
		connect().LoginPage().launchBaseUrl(url);
		connect().LoginPage().enterUserNameForOrange(userName);
		connect().LoginPage().enterPasswordForOrange(password);

}catch(Exception e)
	{
	e.getMessage();
}
}
}
