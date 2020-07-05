package Tests;

import org.testng.annotations.Test;

import Base.BaseScripts;
import TestData.ExcelReader;
import atu.testng.reports.ATUReports;
import atu.testng.reports.utils.Utils;

public class EditContentsBasedOnRoles  extends BaseScripts{
	
	@Test()
	public void start() throws Exception
	{
		try {
			ATUReports.indexPageDescription = "FB Testing Demo";
			ATUReports.setAuthorInfo("Gaurav D", Utils.getCurrentTime(), "1.0");
			String strDescription = "FB Test";
			ATUReports.currentRunDescription = strDescription;
		 
	//		logger=report.createTest("Adding Multiple User Test");
			
	//		logger.info("===========================Test Started=======================");
			
			ExcelReader reader=new ExcelReader("KPMGUserAccess.xlsx");
			int rowCount=reader.fetchRowCount("Sheet1");
			
			for(int i=1;i<rowCount;i++)
			{
				String access=reader.getData("Sheet1", i, 0);
				String appUrl=reader.getData("Sheet1", i, 1);
				String username=reader.getData("Sheet1", i, 2);
				String password=reader.getData("Sheet1", i, 3);
				String relatesTo=reader.getData("Sheet1", i, 4);
				String periodName=reader.getData("Sheet1", i, 5);
				String docTitle=reader.getData("Sheet1", i, 6);
				String editedTitle=reader.getData("Sheet1", i, 7);
				Thread.sleep(2000);
				
		//		logger.info("This is verfiying with Username " +username+ "And Access type is "+access);
				
				connect().LoginPage().launchBaseUrl(appUrl);
				connect().LoginPage().enterUserNameKPMG(username);
				connect().LoginPage().enterPasswordKPMG(password);
				connect().LoginPage().SignInKPMG();
				
				if(access.contains("No"))
				{
				connect().HomePage().verifyPresenceofTile("Audit Committee reporting");
				connect().LoginPage().logout();
				}
				if(access.contains("Read"))
				{
					connect().HomePage().clickTile("Audit Committee reporting");
					connect().AddContents().verfiypresenceOfContent();
					connect().AddContents().verfiyNonEditable();
					connect().LoginPage().logout();
				}
				if(access.contains("Edit"))
				{
					connect().HomePage().clickTile("Audit Committee reporting");
					connect().AddContents().clickEdit();
					connect().AddContents().clickEditPencil();
					connect().AddContents().enterDocTitle(editedTitle);
					connect().AddContents().update();
					connect().AddContents().doneEditing();
					connect().AddContents().verifyTitleChanged(editedTitle);
				
					connect().LoginPage().logout();
					
				}
			   	
			}
						
}catch (Exception e) {
	// TODO: handle exception
}
	}
}