package Tests;

import org.testng.annotations.Test;

import Base.BaseScripts;
import TestData.ExcelReader;
import atu.testng.reports.ATUReports;
import atu.testng.reports.utils.Utils;

public class TestMultipleDataFromExcel extends BaseScripts {

	String userType=null;

	@Test
	public void FBTest() throws Exception
	{
		
		try {
			ATUReports.indexPageDescription = "FB Testing Demo";
			ATUReports.setAuthorInfo("Gaurav D", Utils.getCurrentTime(), "1.0");
			String strDescription = "FB Multiple user Login Test";
			ATUReports.currentRunDescription = strDescription;
		 
			logger=report.createTest("FB Test");
			
			logger.info("Test Started");
			ExcelReader excel=new ExcelReader("MultipleTestDataExcel.xlsx");
			int rowCount= excel.fetchRowCount("User");
			for(int i =1;i<rowCount;i++)
			{
			String userType=excel.getData("User",i, 0);
			String userName=excel.getData("User",i, 1);
			String password=excel.getData("User", i, 2);
			
			
			
			 connect().HomePage().launch();
			 connect().HomePage().enterUserNameForMultipeTest(userName);
			 connect().HomePage().enterPasswordForMultipeTest(password);
		//	 connect().HomePage().signIn();
			 
			 if(userType.contains("Admin"))
			 {
				 connect().HomePage().verfiyLogin(5);
			 }
			} 
					{
							
					}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
}
}