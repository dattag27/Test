package Model;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

	//Orange Website
		@FindBy(xpath="//*[text()='Username']") public WebElement usernameOrange;
		@FindBy(xpath="//*[text()='Password']") public WebElement passwordOrange;
		@FindBy(id="btnLogin") public WebElement signInOrange;
		
	//KPMG 
		@FindBy(name="username") public WebElement userNameKPMG;
		@FindBy(name="password") public WebElement passwordKPMG;
		@FindBy(xpath="//*[@value='Login']") public WebElement btn_Login;
		@FindBy(id="ctl00_bannerControl_imgMenu") public WebElement userMenu;
		@FindBy(xpath="//*[contains(text(),'Logout')]") public WebElement logout;
		
}
