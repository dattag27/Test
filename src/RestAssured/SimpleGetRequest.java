package RestAssured;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import Base.BaseScripts;
import atu.testng.reports.ATUReports;
import atu.testng.reports.utils.Utils;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class SimpleGetRequest {
	
	int statusCodeExp=200;
	String statusLineExp="HTTP/1.1 200 OK"; 
	
	
	@Test
	public void getDetails()
	{
		try
		{
			ATUReports.indexPageDescription = "FB Testing Demo";
			ATUReports.setAuthorInfo("Gaurav D", Utils.getCurrentTime(), "1.0");
			String strDescription = "Rest Assured GET Request Test Weather";
			ATUReports.currentRunDescription = strDescription;
		 
			
		//Specify base URI
		RestAssured.baseURI="http://localhost:3000/";
		
		//Request object
		RequestSpecification httprequest=RestAssured.given();
		
		//Response object
		Response response=httprequest.request(Method.GET,"/posts/2");
		
		//print response
		
		String responseBody=response.body().asString();
		System.out.println("Response Body :"+responseBody);
		
		//status code validation
		int statusCode=response.getStatusCode();
		Assert.assertEquals(statusCodeExp, statusCode);
		System.out.println("Status Code Returned is  "+statusCode);
		
		//validate status line
		String statusLine=response.getStatusLine();
		Assert.assertEquals(statusLineExp, statusLine);
		System.out.println("Status Line Returned is  "+statusLine);
		
		
		
	}catch(Exception e)
		{
			e.getMessage();
		}

}
}