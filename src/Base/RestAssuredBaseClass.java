package Base;

import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;

public class RestAssuredBaseClass {
	
	@BeforeClass
	public void setup()
	{
		RestAssured.authentication=RestAssured.preemptive().basic("ToolsQA", "TestPassword");
	}

}
//given().auth().preemptive().basic("user","pass").when().get("URi").getStatusCode().