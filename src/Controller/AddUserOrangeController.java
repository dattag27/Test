package Controller;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import Base.ComponentFunctions;
import Model.AddUserOrangePage;
import Model.HomePage;

public class AddUserOrangeController  extends ComponentFunctions {
	
	WebDriver driver=null;
	AddUserOrangePage adduser=null;
	
	
	public AddUserOrangeController(WebDriver driver) throws Exception 
	{
		super(driver);
		this.driver = driver;
		adduser =PageFactory.initElements(driver, AddUserOrangePage.class);		
	}
	
	public void selectUserRole(String userRole) throws Exception
	{
		selectValueByValueFromList(adduser.userRole, "User Role", userRole);
	}
	
	public void enterEmpname(String empName) throws Exception
	{
		typeValue(adduser.empName,"Employee Name",empName);
	}
	public void enterUserName(String userName) throws Exception
	{
		typeValue(adduser.empName,"Employee Name",userName);
	}
	public void enterPassword(String password) throws Exception
	{
		typeValue(adduser.empName,"Employee Name",password);
	}
	public void enterConfirmPassword(String passwordConfirm) throws Exception
	{
		typeValue(adduser.empName,"Employee Name",passwordConfirm);
	}

}
