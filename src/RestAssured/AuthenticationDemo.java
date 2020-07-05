package RestAssured;

//---> // To avoid RestAssured and directly call method
import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import Base.RestAssuredBaseClass;

//Challenged and Preemptive 
public class AuthenticationDemo extends RestAssuredBaseClass {

	int expstatusCode=200;
	
	@Test
	public void Authentication()
	{
			int code=    given()
						.when()
						.get("http://restapi.demoqa.com/authentication/CheckForAuthentication")
						.getStatusCode();
			
	
			Assert.assertEquals(code, expstatusCode);
			System.out.println("Status Code returned is "+code);
	}
	
}
