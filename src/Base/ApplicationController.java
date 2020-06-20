package Base;

import org.openqa.selenium.WebDriver;


import Controller.HomePageController;

public class ApplicationController {
	
public static WebDriver driver;
	
	//====================CONTROLLER OBJECTS=======================
	
	public String strParametersNValues = "";
	public String strMainParametersNValues="";
	
	public HomePageController home = null;
	
	public ApplicationController(WebDriver driver) 
	{
		this.driver = driver;
		
	}	
	
	public HomePageController homePage() throws Exception
	{
		if(home == null)
		{
			home = new HomePageController(driver, this.strParametersNValues);
		}
		
		return home;
	}

}
