package RestAssured;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import Base.BaseScripts;
import atu.testng.reports.ATUReports;
import atu.testng.reports.utils.Utils;
import io.restassured.RestAssured;


//Note : For Using this API prerquisites are:
//1 . Install Node.js
//2.install json server--> npm install -g json-server(from Node.js Cmd)
//3.Start the Server--> json-server --watch db.json


public class PostRequestWithJsonFile {
	
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
			String strPostServiceURL="/posts";
			String JSONPath="./Inputsheets/JsonRequest.json";
			int expStatusCode=201;
			String expStatusLine="HTTP/1.1 201 Created";
			
			base.connect().HomePage().verfiyPOSTwithJSON(RestAssured.baseURI,strPostServiceURL,JSONPath,
					expStatusCode,expStatusLine);
			
			

}catch(Exception e)
		{
		e.getMessage();
		}
}
}