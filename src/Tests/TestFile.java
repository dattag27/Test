package Tests;

import org.apache.log4j.Logger;
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
			
			Logger.getLogger("Test Started");
			
			connect().HomePage().launch();
			connect().HomePage().enterUserName();
			connect().HomePage().enterPassword();
			connect().HomePage().signIn();
			 
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	
}
}