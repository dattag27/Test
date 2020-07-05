package Model;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddUserOrangePage {

	@FindBy(id="systemUser_userType") public WebElement userRole;
	@FindBy(id="systemUser_employeeName_empName") public WebElement empName;
	@FindBy(id="systemUser_userName") public WebElement userName;
	@FindBy(id="systemUser_password") public WebElement userPassword;
	@FindBy(id="systemUser_confirmPassword") public WebElement usercnfPassword;
	
}
