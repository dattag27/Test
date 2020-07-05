package Tests;

import java.io.IOException;

import org.testng.annotations.Test;

import Base.BaseScripts;
import atu.testng.reports.ATUReports;
import atu.testng.reports.utils.Utils;

public class FileUpload extends BaseScripts{
	
	@Test
	public void FileUploadTest() throws IOException, Exception
	{
		try
		{
			ATUReports.indexPageDescription = "FB Testing Demo";
			ATUReports.setAuthorInfo("Gaurav D", Utils.getCurrentTime(), "1.0");
			String strDescription = "FB Test";
			ATUReports.currentRunDescription = strDescription;
		 
			logger=report.createTest("AutoIT Test");
			
			logger.info("Test Started");
		
		connect().HomePage().launch1();
		connect().HomePage().clickBrowse();
		
		Runtime.getRuntime().exec("./AutoIT/FileUpload.exe");
		System.out.println("File Uploaded Successfully");
	}catch(Exception e)
		{
		e.getMessage();
		}

}
}
