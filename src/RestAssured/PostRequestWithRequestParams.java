package RestAssured;


import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostRequestWithRequestParams {

	
	@Test
	public void RequestParamsPost()
	{
		
		
		RequestSpecification request=RestAssured.given();
		
		request.header("Content-Type","application/json");
		
		JSONObject json=new JSONObject();
		json.put("id",3);
		json.put("title","Gaurav Request Params");
		json.put("author", "Gaurav Test");
		
		request.body(json.toJSONString());
		
		Response respone=request.post("http://localhost:3000/posts");
		
		Assert.assertEquals(respone.getStatusCode(), 201);
		Assert.assertEquals(respone.getStatusLine(), "HTTP/1.1 201 Created");
		
		System.out.println("Data Sent as post request is "+respone.asString()+" \n With Status Code Returned is "+respone.getStatusCode()
								+"\n And Status Line Returned as "+respone.getStatusLine());
	
		
	}
}
