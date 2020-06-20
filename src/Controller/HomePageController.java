package Controller;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import Base.ComponentFunctions;
import Model.HomePage;


public class HomePageController extends ComponentFunctions {
	
	WebDriver driver=null;
	HomePage home=null;
	
	
	public HomePageController(WebDriver driver,String strParametersNValues) throws Exception 
	{
		super(driver);
		this.driver = driver;
		home =PageFactory.initElements(driver, HomePage.class);		
	}

	public void enterUserName() throws Exception
	{
		waitTillElementEnabled(home.username,"UserName",10);
		typeValue(home.username, "UserName", getValue("Username"));
	}
	
	public void enterPassword() throws Exception
	{
		typeValue(home.password, "Password", getValue("Password"));
	}
	
    public void signIn() throws Exception
    {
    	clickObject(home.signInBtn, "Sign In Button");
    }
}
