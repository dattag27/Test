package RestAssured;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import Base.BaseScripts;
import atu.testng.reports.ATUReports;
import atu.testng.reports.utils.Utils;
import io.restassured.RestAssured;

public class JSONRequestPutWithJsonFile {
	
	BaseScripts base=new BaseScripts();
	
	@Test
	public void postWeatherDetails()
	{
		try
		{
			ATUReports.indexPageDescription = "FB Testing Demo";
			ATUReports.setAuthorInfo("Gaurav D", Utils.getCurrentTime(), "1.0");
			String strDescription = "Rest Assured POST Request Test Weather";
			ATUReports.currentRunDescription = strDescription;
		 
			
			RestAssured.baseURI="http://localhost:3000/";  
			String strPostServiceURL="/posts/2";
			String JSONPath="./Inputsheets/JsonRequestPut.json";
			int expStatusCode=200;
			String expStatusLine="HTTP/1.1 200 OK";
			
			base.connect().HomePage().verfiyPUTwithJSON(RestAssured.baseURI,strPostServiceURL,JSONPath,
					expStatusCode,expStatusLine);
			
	
			

}catch(Exception e)
		{
		e.getMessage();
		}
}

}
