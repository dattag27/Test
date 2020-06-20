package Tests;

import org.testng.annotations.Test;

import Base.BaseScripts;
import atu.testng.reports.ATUReports;
import atu.testng.reports.utils.Utils;

public class TestFile extends BaseScripts {

	

	@Test
	public void FBTest() throws Exception
	{
		try {
			ATUReports.indexPageDescription = "FB Testing Demo";
			ATUReports.setAuthorInfo("Gaurav D", Utils.getCurrentTime(), "1.0");
			String strDescription = "FB Test";
			ATUReports.currentRunDescription = strDescription;
		 
			logger=report.createTest("FB Test");
			
			logger.info("Test Started");
			connect().homePage().enterUserName();
			connect().homePage().enterPassword();
			connect().homePage().signIn();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	
}
}