package RestAssured;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class DeleteRequest {

	
	int expStatusCode=200;
	String StatusLine="HTTP/1.1 200 OK";
	@Test
	public void DeleteRequestAPI()
	{
		
		RequestSpecification httpRequest=RestAssured.given();
		
		httpRequest.header("Content-Type","application/json");
		
		for(int i=2;i<=3;i++)
		{
		Response response=httpRequest.delete("http://localhost:3000/posts/"+i);
		
		System.out.println("Status Code is "+response.statusCode());
		System.out.println("Status Line is "+response.statusLine());
		Assert.assertEquals(response.statusCode(), expStatusCode);
		Assert.assertEquals(response.statusLine(), StatusLine);
		}
	
		
		
		
		
	}
}
