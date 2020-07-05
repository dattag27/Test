package Tests;

import org.testng.annotations.Test;

import Base.BaseScripts;
import TestData.ExcelReader;
import atu.testng.reports.ATUReports;
import atu.testng.reports.utils.Utils;

public class MultipleUsersVerification extends BaseScripts{
	
	@Test()
	public void start() throws Exception
	{
		try {
			logger=report.createTest("Adding Multiple User Test");
			
			ATUReports.indexPageDescription = "FB Testing Demo";
			ATUReports.setAuthorInfo("Gaurav D", Utils.getCurrentTime(), "1.0");
			String strDescription = "FB Test";
			ATUReports.currentRunDescription = strDescription;
		 
			logger=report.createTest("Adding Multiple User Test");
			
			logger.info("===========================Test Started=======================");
			
			ExcelReader reader=new ExcelReader("KPMGUserAccess.xlsx");
			int rowCount=reader.fetchRowCount("Sheet1");
			
			for(int i=1;i<=1;i++)
			{
				String access=reader.getData("Sheet1", i, 0);
				String appUrl=reader.getData("Sheet1", i, 1);
				String username=reader.getData("Sheet1", i, 2);
				String password=reader.getData("Sheet1", i, 3);
				String relatesTo=reader.getData("Sheet1", i, 4);
				String periodName=reader.getData("Sheet1", i, 5);
				String docTitle=reader.getData("Sheet1", i, 6);
				Thread.sleep(2000);
			
				connect().LoginPage().launchBaseUrl(appUrl);
				connect().LoginPage().enterUserNameKPMG(username);
				connect().LoginPage().enterPasswordKPMG(password);
				connect().LoginPage().SignInKPMG();
				

				connect().HomePage().clickTile("Audit Committee reporting");
				
				connect().AddContents().clickAttach();
				connect().AddContents().selectValueRelatesTo(relatesTo);
				connect().AddContents().selectValuePeriodName(periodName);
				connect().AddContents().enterDocOriginalTitle(docTitle);
				connect().AddContents().clickBrowse();
				Runtime.getRuntime().exec("./AutoIT/FileUpload.exe");
				Thread.sleep(1000);
				connect().AddContents().clickSave();
				connect().LoginPage().logout();
			}
			
		
		
		}catch(Exception e)
		{
			e.getMessage();
		}
}
}
