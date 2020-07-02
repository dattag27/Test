package Controller;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Base.ComponentFunctions;
import Model.HomePage;


public class HomePageController extends ComponentFunctions {
	
	WebDriver driver=null;
	HomePage home=null;
	
	
	public HomePageController(WebDriver driver) throws Exception 
	{
		super(driver);
		this.driver = driver;
		home =PageFactory.initElements(driver, HomePage.class);		
	}

	public void launch() throws IOException, Exception
	{
		launchURL(getValue("Data2","URL"));
	}
	public void enterUserNameForMultipeTest(String userName) throws Exception
	{
		waitTillElementEnabled(home.username,"UserName",10);
		typeValue(home.username, "UserName", userName);
	}
	public void enterPasswordForMultipeTest(String password) throws Exception
	{
		waitTillElementEnabled(home.password,"Password",10);
		typeValue(home.password, "Password", password);
	}
	public void clickBrowse() throws Exception
	{
		waitTillElementEnabled(home.upload, "browser", 10);
		clickObject(home.upload, "browse");
		Thread.sleep(1000);
		clickObject(home.clickHere, "Click here");
	}
	public void launch1() throws IOException, Exception
	{
		launchURL(getValue("Data2", "AutoIT_Url"));
	}
	public void enterUserName() throws Exception
	{
		waitTillElementEnabled(home.username,"UserName",10);
		typeValue(home.username, "UserName", getValue("Data2","Username"));
	}
	
	public void enterPassword() throws Exception
	{
		String password=getValue("Data2","Password");
		String decodedPassword=passwordEncoder(password);
		typeValue(home.password, "Password", decodedPassword);
	}
	
    public void signIn() throws Exception
    {
    	clickObject(home.signInBtn, "Sign In Button");
    }
    
    //Orange website
    public void clickUser() throws Exception
    {
    	waitTillElementEnabled(home.adminTab, "Admin", 10);
    	clickObject(home.adminTab, "Admin Tab");
    	waiting(1);
    	clickObject(home.userManagement, "User Management");
    	waiting(1);
    	clickObject(home.users, "Users");
    }
    
    public void clickAddUserButton() throws Exception
    {
    	waiting(1);
    	clickObject(home.addUserBtn, "Add user Button");
    }
    
    public void verfiyNewUserCreated(String userName) throws Exception
    {
    	waiting(2);
    	checkObjectExists(home.newCreatedUser(driver, userName), userName);
    	waiting(2);
    }
    public void verfiyLogin(int refreshtime) throws Exception
    {

    	int refreshTime=refreshtime;
    	boolean displayed = false;
 
    		for(int i=0;i<refreshTime;i++)
    		{
    	  try{
    	    displayed = home.signInBtn.isDisplayed();
    	    if(displayed){
    	    	System.out.println("Yayyyy Element Found!!!");
    	    	break;
    	    }
    	  } catch (Exception e){
    	   
    	   System.out.println("Seached for " +i+ "time but Element not found still.. Continuining to search");
    	   RefreshPage();
    	  }
    	} 
    }
    public void clickAddEmployee() throws Exception
    {
    	waitTillElementEnabled(home.pim,"Pim",10);
    	clickObject(home.pim,"Pim");
    	waiting(1);
    	clickObject(home.addEmployee, "Add Employee");
    }
    
    public void verfiyPOSTwithJSON(String strBaseURI,String strPostServiceURL,String strJSONFilePath
    										,int statusCodeExp,String expStatusLine) throws Exception
    {
    	postMessageFromJSONFileRestAssured(strBaseURI, strPostServiceURL, strJSONFilePath,statusCodeExp,expStatusLine);
    }
    
    public void verfiyPUTwithJSON(String strBaseURI,String strPostServiceURL,String strJSONFilePath
			,int statusCodeExp,String expStatusLine) throws Exception
{
    		putMessageFromJSONFileRestAssured(strBaseURI, strPostServiceURL, strJSONFilePath,statusCodeExp,expStatusLine);
}
    public void clickTile(String tile) throws Exception
    {
    	Thread.sleep(3000);;
    	clickObject(home.auditCommitteeTile(driver, tile), tile);
    	Thread.sleep(2000);
    	
    }
    
    public void verifyPresenceofTile(String tile) throws Exception
    {
    	try{
    		
    	
    	checkObjectNotExists(home.auditCommitteeTile(driver,tile), tile);
    	logToConsole(tile+"Present.. It was not expected");
    }catch(Exception e)
    	{
    		logToConsole(tile + "Not Present as expected!!!");
    	}

}
}
