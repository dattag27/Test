package Base;

import org.openqa.selenium.WebDriver;

import Controller.AddContentsController;
import Controller.AddUserOrangeController;
import Controller.HomePageController;
import Controller.LoginPageController;

public class ApplicationController {
	
public static WebDriver driver;
	
	//====================CONTROLLER OBJECTS=======================
	
	public String strParametersNValues = "";
	public String strMainParametersNValues="";
	
	public HomePageController home = null;
	public LoginPageController login = null;
	public AddUserOrangeController adduser=null;
	public AddContentsController addContents=null;
	
	public ApplicationController(WebDriver driver) 
	{
		this.driver = driver;
		
	}	
	
	public HomePageController HomePage() throws Exception
	{
		if(home == null)
		{
			home = new HomePageController(driver);
		}
		
		return home;
	}
	
	public LoginPageController LoginPage() throws Exception
	{
		if(login == null)
		{
			login = new LoginPageController(driver);
		}
	
		return login;
	}
	public AddUserOrangeController Adduser() throws Exception
	{
		if(adduser == null)
		{
			adduser = new AddUserOrangeController(driver);
		}
	
		return adduser;
	}
	public AddContentsController AddContents() throws Exception
	{
		if(addContents == null)
		{
			addContents = new AddContentsController(driver);
		}
		
		return addContents;
	}
}
