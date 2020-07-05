package Controller;

import java.awt.AWTException;
import java.awt.Robot;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.gargoylesoftware.htmlunit.javascript.host.event.KeyboardEvent;

import Base.ComponentFunctions;
import Model.LoginPage;

public class LoginPageController extends ComponentFunctions {
	
	WebDriver driver=null;
	LoginPage login=null;
	
	
	public LoginPageController(WebDriver driver) throws Exception 
	{
		super(driver);
		this.driver = driver;
		login =PageFactory.initElements(driver, LoginPage.class);		
	}
	
	public void launchBaseUrl(String value) throws Exception
	{
		launchURL(value);
	}
	
	public void enterUserNameForOrange(String val) throws Exception
	{
		waitTillElementEnabled(login.usernameOrange,"UserName", 10);
		
		clickObject(login.usernameOrange, "UserName");
		typeValueUsingActionBuilder(val);
		waiting(1);
	}
	
	public void enterPasswordForOrange(String val) throws Exception
	{
		waitTillElementEnabled(login.passwordOrange,"Password", 10);
		
		tab();
		clickObjectUsingJSExecutor(login.passwordOrange, "Password");
		typeValueUsingActionBuilder( val);
	}
	
	public void clickSignIn() throws Exception
	{
		clickObjectUsingJSExecutor(login.signInOrange, "Sign In Orange");
	}
	
	public void tab() throws AWTException
	{
		Robot robot = new Robot();
		robot.keyPress(KeyboardEvent.DOM_VK_TAB);
		
	}
	
	public void enterUserNameKPMG(String val) throws Exception
	{
		waitTillElementEnabled(login.userNameKPMG,"UserName", 10);
		
		typeValue(login.userNameKPMG, "Username", val);
		waiting(1);
	}
	public void enterPasswordKPMG(String val) throws Exception
	{
		waitTillElementEnabled(login.passwordKPMG,"Password", 10);
		typeValue(login.passwordKPMG, "Password", val);
	}
	
	public void SignInKPMG() throws Exception
	{
		clickObject(login.btn_Login, "Login Button");
	}
	public void logout() throws Exception
	{
		clickObject(login.userMenu, "User Mennu");
		
		waiting(1);
		clickObject(login.logout, "Logout");
	}
}
