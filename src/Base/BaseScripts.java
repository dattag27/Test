package Base;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import TestData.ExcelReader;

public class BaseScripts {
	Properties objProperties = new Properties();
	public ApplicationController connect;
	public static WebDriver driver = null;
	public static String ParameterNValue = null;
	public String strDTParametersNValues;
	public ExtentReports report;
	public static ExtentTest logger;
	public static Logger log=null;
	
	
	public BaseScripts() {
		
		try {
			this.objProperties.load(new FileReader(System.getProperty("user.dir") + "/Config/CONFIG.properties"));
		} catch (Exception var2) {
			var2.printStackTrace();
		}
		System.out.println("##OS NAME=>" + System.getProperty("os.name"));
		if (System.getProperty("os.name").toLowerCase().contains("windows")) {
			System.setProperty("atu.reporter.config", this.objProperties.getProperty("ATUPropertiesPath"));
			System.out.println(
					"##Setting windows atu properties path=>" + this.objProperties.getProperty("ATUPropertiesPathMac"));
		} else if (System.getProperty("os.name").toLowerCase().contains("mac")) {
			System.setProperty("atu.reporter.config", this.objProperties.getProperty("ATUPropertiesPathMac"));
			System.out.println(
					"##Setting mac atu properties path=>" + this.objProperties.getProperty("ATUPropertiesPathMac"));
		} else if (System.getProperty("os.name").toLowerCase().contains("nux")) {
			System.setProperty("atu.reporter.config", this.objProperties.getProperty("ATUPropertiesPathUbuntu"));
			System.out.println("##Setting ubuntu atu properties path=>"
					+ this.objProperties.getProperty("ATUPropertiesPathUbuntu"));
	}
		
		this.connect = null;
	}
	
	
	@BeforeSuite
	public void setup()
	{
		ExtentHtmlReporter reporter=new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/Reports/Automation"+getCurrentDateTime()+".html"));
		report=new ExtentReports();
		report.attachReporter(reporter);
	}
	
	@Parameters({"Browser"})
	@BeforeClass
	public void start(String Browser) throws InterruptedException{
	String browser=Browser;
	try{
	if(browser.contains("chrome")){
		System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
		System.setProperty("webdriver.chrome.driver","./Lib/drivers/chromedriver.exe");
		driver = new ChromeDriver();
		Logger.getLogger(BaseScripts.class).info(Browser+"Launched successfully!!!");
		driver.manage().window().maximize();
		Logger.getLogger(BaseScripts.class).info("Window Maximized");
		
	if(browser.contains("No")){
		
		Logger.getLogger(BaseScripts.class).info("No Browser opened !!!API testing in progress");
	}
		
	}
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}catch(Exception e)
	{
		Logger.getLogger(BaseScripts.class).info("Exception in browser initialization!!! : " + e.getMessage());
		System.out.println(e.toString());
	}
	}
	@BeforeTest
	public static void loadlog4j() throws IOException
	{
		String log4j=System.getProperty("user.dir")+ "/Config/log4j.properties";
		PropertyConfigurator.configure(log4j);
	}
	
	@Test
	public ApplicationController connect() {
		if (this.connect == null) {
			this.connect = new ApplicationController(driver);
		} else if (!ApplicationController.driver.toString().equalsIgnoreCase(driver.toString())) {
			this.connect = new ApplicationController(driver);
		}

		/*this.connect.strParametersNValues = this.strDTParametersNValues;
		if (this.connect.strMainParametersNValues == "") {
			this.connect.strMainParametersNValues = ParameterNValue;
		}*/

		return this.connect;
	}
	
	@AfterMethod
	public void tearDownMethod(ITestResult result) throws IOException
	{
		if(result.getStatus() == ITestResult.FAILURE)
		{
			logger.fail("Test Failed", MediaEntityBuilder.createScreenCaptureFromPath(CaptureScreenshot(driver)).build());
		}else if(result.getStatus() == ITestResult.SUCCESS)
			{
				logger.pass("Test Passed", MediaEntityBuilder.createScreenCaptureFromPath(CaptureScreenshot(driver)).build());
			}
		report.flush();
	}
	
	@AfterClass
	public void teardown()
	{
		driver.quit();
	}
	
	public static String getCurrentDateTime()
	{
		DateFormat dtformat=new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
		Date currentDate=new Date();
		return dtformat.format(currentDate);
		
	}
	
	public static String CaptureScreenshot(WebDriver driver)
	{
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String screenshotPath=System.getProperty("user.dir")+"/Screenshots/Screenshot"+getCurrentDateTime()+".png";
		try{
			
			FileHandler.copy(src,new File(screenshotPath));
		
		}catch (Exception e) {
				System.err.println("Unable to take Screenshot" +e.getMessage());
		}
		return screenshotPath;
	}
	public static void logTestPass(String strInputVal,String strObjval) throws IOException
	{
		logger.pass(strInputVal+ " entered in" +strObjval);
		CaptureScreenshot(driver);
		logger.pass("screenshot", MediaEntityBuilder.createScreenCaptureFromPath(CaptureScreenshot(driver)).build());
		
	}
	public static void logTestPass(String strObjval) throws IOException
	{
		logger.pass(strObjval+ " Loaded / Clicked");
		CaptureScreenshot(driver);
		logger.pass("Screesshot", MediaEntityBuilder.createScreenCaptureFromPath(CaptureScreenshot(driver)).build());
	}
	public static void logTestFail(String strInputVal,String strObjval) throws IOException
	{
		logger.fail("Unable to enter value "+strInputVal+ "in" +strObjval);
		CaptureScreenshot(driver);
		logger.fail("Screenshot", MediaEntityBuilder.createScreenCaptureFromPath(CaptureScreenshot(driver)).build());
	}
	public static void logTestFail(String strObjval) throws IOException
	{
		logger.fail(strObjval+ "Not Clicked / Loaded properly");
		CaptureScreenshot(driver);
		logger.fail("Screenshot", MediaEntityBuilder.createScreenCaptureFromPath(CaptureScreenshot(driver)).build());
	}

}
