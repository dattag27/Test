package Model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {

	
	//FB
	@FindBy(id="email") public WebElement username;
	@FindBy(id="pass") public WebElement password;
	@FindBy(id="u_0_b") public WebElement signInBtn;
	@FindBy(xpath="//*[@id='1']") public WebElement browse;
	@FindBy(xpath="//*[@class='nav-icon fas fa-file-upload']") public WebElement upload;
	@FindBy(id="btnChooseFiles") public WebElement clickHere;
	
	@FindBy(id="loginbutton1") public WebElement loginVerify;
	
	
    
	//Orange Website
	
	@FindBy(id="menu_admin_viewAdminModule") public WebElement adminTab;
	@FindBy(id="menu_admin_UserManagement") public WebElement userManagement;
	@FindBy(id="menu_admin_viewSystemUsers") public WebElement users;
	@FindBy(id="btnAdd") public WebElement addUserBtn;
	
	public WebElement newCreatedUser(WebDriver driver, String userName)
	{
		return driver.findElement(By.xpath("//*[text()='" +userName +"']"));
	}
	
	@FindBy(id="menu_pim_viewPimModule") public WebElement pim;
	@FindBy(id="menu_pim_addEmployee") public WebElement addEmployee;
	
	//Add Employee
	@FindBy(id="firstName") public WebElement firstName;
	@FindBy(id="lastName") public WebElement lastName;
	@FindBy(id="chkLogin") public WebElement chkloginCred;
	@FindBy(id="user_name") public WebElement userName;
	@FindBy(id="user_password") public WebElement userPassword;
	@FindBy(id="re_password") public WebElement userConfPassword;
	@FindBy(id="btnSave") public WebElement btnSave;
	
	//KPMG
	@FindBy(xpath="")public WebElement auditCommitteeTile;
	public WebElement auditCommitteeTile(WebDriver driver,String title)
	{
		return driver.findElement(By.xpath("//*[contains(text(),'"+title+"')]"));
	}
	
}
