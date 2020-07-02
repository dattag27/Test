package Base;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.remote.server.handler.CaptureScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;

import atu.testng.reports.ATUReports;
import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ComponentFunctions{
	
	public String strErrorMsg = "";
	public WebDriver driver = null;
	JavascriptExecutor objJSExecutor = null;
	String strObjectXPATH = "";
	
	
	
public static final String EXCELFILELOCATION="./Inputsheets/";
	
	private static FileInputStream fis;
	private static XSSFWorkbook workbook;
	private static XSSFSheet  sheet;
	private static XSSFRow    row;
	
	public ComponentFunctions(WebDriver objTempWebDriver) throws Exception {
		
		this.driver = objTempWebDriver;
		this.objJSExecutor = (JavascriptExecutor) this.driver;
		BaseScripts.log = Logger.getLogger(ComponentFunctions.class);
	
		
		}
	
	@Listeners({ ATUReportsListener.class,ConfigurationListener.class,MethodListener.class })
	public class AUTreports {
	{ 
		System.setProperty("atu.reporter.config", "./Config/ATU.properties");
	}
}

	
	public static void loadExcel(String excelName) throws IOException
	{
		File file=new File(EXCELFILELOCATION+excelName+".xlsx");
		fis=new FileInputStream(file);
		workbook=new XSSFWorkbook(fis);
		sheet=workbook.getSheet("Sheet1");
		fis.close();
		
		
	}
	public static Map<String, Map<String,String>> getData(String excelName) throws IOException{
		if(sheet==null){
			loadExcel(excelName);
		}
		Map<String, Map<String,String>> superMap=new HashMap<String,Map<String,String>>();
		Map<String,String> myMap=new HashMap<String,String>();
		
		for(int i=1;i<sheet.getLastRowNum()+1;i++)
		{
			row=sheet.getRow(i);
			String keyCell=row.getCell(0).getStringCellValue();
			int colNum=row.getLastCellNum();
			
			for(int j=1;j<colNum;j++)
			{
				String value=row.getCell(j).getStringCellValue();
				myMap.put(keyCell, value);
			}
			
			superMap.put("MASTERDATA", myMap);
		}
		
		
		return superMap;
		
	}
	public static String getValue(String excelName,String key) throws IOException
	{
		getData(excelName);
		Map<String , String> myVal = getData(excelName).get("MASTERDATA");
		String retValue=myVal.get(key);
		return retValue;
		
	}

	public Boolean launchURL(String strURL) throws Exception {
		try {
			this.driver.get(strURL);
			ATUReports.add(strURL +" launched in Browser!!! " , LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		//	BaseScripts.logTestPass(strURL);
			logToConsole(strURL +" launched in Browser!!! ");
			return true;
		} catch (Exception var3) {
			this.strErrorMsg = var3.getMessage();
			ATUReports.add(
					"Unable to launch URL=>" + strURL + " in browser.<BR>Error message=>" + this.strErrorMsg, "true",
					"false",LogAs.FAILED,(new CaptureScreen(ScreenshotOf.BROWSER_PAGE)));
			logToConsole("Exception Unable to launch the Browser!!! ");
			return false;
		}
	}

public Boolean typeValue(WebElement objWebElement, String strObjectName, String strInputValue) throws Exception
{
	try {
		objWebElement.sendKeys(new CharSequence[]{strInputValue});
		String strObjectXPATH = "";
		ATUReports.add("Value " + strInputValue + " typed in element " + strObjectName, LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
	//	BaseScripts.logTestPass(strInputValue,strObjectName);
		logToConsole("Value " + strInputValue + " typed in element " + strObjectName);
		captureScreenshotOfElement(objWebElement, strObjectName, "Demo");
		return true;
	}catch(Exception e)
	{
		this.strErrorMsg = e.toString();
		logToConsole("Unable to type Value " + strInputValue + " in element " + strObjectName + this.strErrorMsg);
		ATUReports.add("Unable to type Value " + strInputValue + " in element " + strObjectName, "true","false", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
	//	BaseScripts.logTestFail(strInputValue,strObjectName);
		return false;
	}
}
	
	public Boolean typeValueUsingActionBuilder(String strInputValue) throws Exception {
		try {
			Actions builder = new Actions(this.driver);
			builder.sendKeys(new CharSequence[]{strInputValue});
			Action objAction = builder.build();
			objAction.perform();
			ATUReports.add("Value " + strInputValue + " typed in element ", LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			//	BaseScripts.logTestPass(strInputValue,strObjectName);
				logToConsole("Value " + strInputValue + " typed in element " );
				
				return true;
			}catch(Exception e)
			{
				this.strErrorMsg = e.toString();
				logToConsole("Unable to type Value " + strInputValue + " in element " + this.strErrorMsg);
				ATUReports.add("Unable to type Value " + strInputValue + " in element " , "true","false", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			//	BaseScripts.logTestFail(strInputValue,strObjectName);
				return false;
			}
		}
	
	
	
	

public Boolean clickObject(WebElement objWebElement, String strObjectName) throws Exception {
	try {
		objWebElement.click();
		String strObjectXPATH = "";
		logToConsole("Element " + strObjectName + "</i> clicked");
		ATUReports.add("Element " + strObjectName + "</i> clicked",LogAs.PASSED,new CaptureScreen(ScreenshotOf.DESKTOP));
	//	BaseScripts.logTestPass(strObjectName );
		return true;
	} catch (Exception e) {
		this.strErrorMsg = e.toString();
		ATUReports.add(
				strObjectName + " couldn't be clicked. <br> Error message=>" + this.strErrorMsg,LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		logToConsole(strObjectName + " couldn't be clicked. <br> Error message=>"+ this.strErrorMsg);
	//	BaseScripts.logTestFail(strObjectName + "not clicked");
		return false;
	}
}
public void closeApplication()

{
	this.driver.quit();
}
public Boolean waitTillElementEnabled(WebElement objWebElement, String strObjectName, int intWaitTime)
		throws Exception {
	try {
		(new WebDriverWait(this.driver, (long) intWaitTime))
				.until(ExpectedConditions.elementToBeClickable(objWebElement));
		logToConsole(objWebElement+ " loaded properly");
	//	BaseScripts.logTestPass(strObjectName+ " Loaded properly");
		
		return true;
	} catch (Exception var5) {
		this.strErrorMsg = var5.toString();
		logToConsole(objWebElement+ "not loaded properly");
	//	BaseScripts.logTestFail(strObjectName+ "Not Loaded properly");
		return false;
	}
	
	
}

public void logToConsole(String message)
{
		BaseScripts.log.info(message);
}
public boolean captureScreenshotOfElement(WebElement objWebElement, String strObjectName, String strFilePath)
		throws IOException {
	try {
		File screenshot = (File) ((TakesScreenshot) this.driver).getScreenshotAs(OutputType.FILE);
		BufferedImage fullImg = ImageIO.read(screenshot);
		Point point = ((Locatable) objWebElement).getCoordinates().inViewPort();
		int eleWidth = objWebElement.getSize().getWidth();
		int eleHeight = objWebElement.getSize().getHeight();
		System.out.println("Screenshot x:=" + point.getX());
		System.out.println("Screenshot y:=" + point.getY());
		BufferedImage eleScreenshot = fullImg.getSubimage(point.getX(),
				point.getY() + 100 + 1, eleWidth, eleHeight);
		ImageIO.write(eleScreenshot, "png", screenshot);
		FileUtils.copyFile(screenshot, new File(strFilePath + strObjectName + ".png"));
		return true;
	} catch (Exception var10) {
		return false;
	}
}

public static String passwordEncoder(String password)
{
	{
		String pass= password;
		
		byte[] byteCode=Base64.getEncoder().encode(pass.getBytes());
		String strcode=new String(byteCode);
		System.out.println("String after encoding" +strcode);
		

		byte[] decodePassword=Base64.getDecoder().decode(strcode.getBytes());
		String strDeco= new String(decodePassword);
		System.out.print(strDeco);
		return strDeco;
	}
	
}
public Boolean clickObjectUsingJSExecutor(WebElement objWebElement, String strObjectName) throws Exception {
	try {
		JavascriptExecutor js = (JavascriptExecutor) this.driver;
		js.executeScript("arguments[0].click();", new Object[]{objWebElement});
		String strObjectXPATH = "";
		System.out.println("Clicked");
		return true;
	} catch (Exception var5) {
		
		System.out.println("NOt Clicked");
		return false;
	}
}

public void waiting(int waitTime) throws InterruptedException
{
	Thread.sleep((long) (waitTime * 1000));
}

public boolean selectValueByValueFromList(WebElement objWebElement, String strObjectName, String strInputValue)
		throws Exception {
	try {
		Select objSelectCountry = new Select(objWebElement);
		objSelectCountry.selectByValue(strInputValue);
		String strObjectXPATH = "";
		ATUReports.add("Value " + strInputValue + " selected in element " + strObjectName, LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		//	BaseScripts.logTestPass(strInputValue,strObjectName);
			logToConsole("Value " + strInputValue + " selected in element " + strObjectName);
			captureScreenshotOfElement(objWebElement, strObjectName, "Demo");
			return true;
		}catch(Exception e)
		{
			this.strErrorMsg = e.toString();
			logToConsole("Unable to select Value " + strInputValue + " in element " + strObjectName + this.strErrorMsg);
			ATUReports.add("Unable to select Value " + strInputValue + " in element " + strObjectName, "true","false", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		//	BaseScripts.logTestFail(strInputValue,strObjectName);
			return false;
		}
}

public Boolean waitTillElementDisplayed(WebElement objWebElement, String strObjectName, int intWaitTime)
		throws Exception {
	try {
		(new WebDriverWait(this.driver, (long) intWaitTime)).until(ExpectedConditions.visibilityOf(objWebElement));
		return true;
	} catch (Exception var5) {
		this.strErrorMsg = var5.getMessage();
		return false;
	}
}

public Boolean checkObjectExists(WebElement objWebElement, String strObjectName) throws Exception {
	try {
		this.driver.manage().timeouts().implicitlyWait(2L, TimeUnit.SECONDS);
		boolean blnObjectExists = this.waitTillElementDisplayed((WebElement) objWebElement, strObjectName, 3);
		String strObjectXPATH = "";
		/*Assert.assertEquals(blnObjectExists, true,
				"Asserting element " + strObjectName + " displayed in the current page");*/
		if (blnObjectExists) {
			ATUReports.add("Value " + strObjectName + " present in page " + strObjectName, LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			logToConsole("Value " + strObjectName + " present in page " + strObjectName);
			Assert.assertEquals(true, true);
		} else {
			ATUReports.add("Value " + strObjectName + "not present in page " + strObjectName, LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			logToConsole("Value " + strObjectName + "not present in page " + strObjectName);
			Assert.assertEquals(false, true);
			return false;
		}

		this.driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
		return blnObjectExists;
	} catch (Exception var5) {
		this.driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
		this.strErrorMsg = var5.getMessage();
		ATUReports.add("Value " + strObjectName + "not present in page " + strObjectName, LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		logToConsole("Value " + strObjectName + "not present in page " + strObjectName);
		return false;
	}
}

public Boolean checkObjectNotExists(WebElement objWebElement, String strObjectName) throws Exception {
	try {
		this.driver.manage().timeouts().implicitlyWait(2L, TimeUnit.SECONDS);
		Boolean blnObjectExists = this.waitTillElementEnabled((WebElement) objWebElement, strObjectName, 3);
		String strObjectXPATH = "";
		this.driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
		if (!blnObjectExists) {
			
			ATUReports.add("Value " + strObjectName + "not present in page " + strObjectName, LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			logToConsole("Value " + strObjectName + "not present in page " + strObjectName);
			Assert.assertEquals(true, true);
			return true;
		} else {
			ATUReports.add("Value " + strObjectName + "not present in page " + strObjectName, LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			logToConsole("Value " + strObjectName + "not present in page " + strObjectName);
			Assert.assertEquals(false, true);
			return false;
		}
	} catch (Exception var5) {
		this.driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
		this.strErrorMsg = var5.getMessage();
		ATUReports.add("Value " + strObjectName + "not present in page " + strObjectName, LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		logToConsole("Value " + strObjectName + "not present in page " + strObjectName);
		return false;
	}
}

public Boolean clearNTypeValue(WebElement objWebElement, String strObjectName, String strInputValue)
		throws Exception {
	try {
		objWebElement.clear();
		objWebElement.sendKeys(new CharSequence[]{strInputValue});
		String strObjectXPATH = "";
		ATUReports.add("value type in " + objWebElement + "The value"  + strObjectName, LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		logToConsole("value Typed in " + objWebElement + "The value " + strObjectName);
		return true;
	} catch (Exception var5) {
		this.strErrorMsg = var5.getMessage();
		ATUReports.add("unable to type in " + objWebElement + "The value"  + strObjectName, LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		logToConsole("unable to type in " + objWebElement + "The value " + strObjectName);
		return false;
	}
}


public void RefreshPage()
{
	driver.navigate().refresh();
}

//Rest Assured

public boolean postMessageFromJSONFileRestAssured(String strBaseURI, String strPostServiceURL,
		String strJSONFilePath,int statusCodeExp,String expStatusLine) throws Exception {
	String txtFileLines = "";

	try {
		txtFileLines = this.readTextFileAndGetAsString(strJSONFilePath);
		RestAssured.baseURI = strBaseURI;
		Response output = (Response) RestAssured.given().contentType("application/json").body(txtFileLines).when()
				.post(strPostServiceURL, new Object[0]);
		/*ATUReports.add("Posting the message " + txtFileLines + " to the URL "
				+ strBaseURI + strPostServiceURL + " passed" + " with response " + output.asString(), LogAs.PASSED,new CaptureScreen(ScreenshotOf.valueOf(null)));*/
		System.out.println("Respone Data sent is "+output.asString() + "\nWith Status Code returned is " + output.statusCode() + "\nAnd Status Line Returned is " + output.statusLine());
		Assert.assertEquals(output.statusCode(), statusCodeExp);
		Assert.assertEquals(output.statusLine(), expStatusLine);
		return true;
	} catch (Exception var6) {
		System.out.println("Exception while posting the message " + txtFileLines + " to the URL " + strBaseURI
				+ strPostServiceURL + "\n\n" + var6.toString());
		return false;
	}
}

public boolean putMessageFromJSONFileRestAssured(String strBaseURI, String strPostServiceURL,
		String strJSONFilePath,int statusCodeExp,String expStatusLine) throws Exception {
	String txtFileLines = "";

	try {
		txtFileLines = this.readTextFileAndGetAsString(strJSONFilePath);
		RestAssured.baseURI = strBaseURI;
		Response output = (Response) RestAssured.given().contentType("application/json").body(txtFileLines).when()
				.put(strPostServiceURL, new Object[0]);
		
		System.out.println("Respone Data sent is "+output.asString() + "\nWith Status Code returned is " + output.statusCode() + "\nAnd Status Line Returned is " + output.statusLine());
		Assert.assertEquals(output.statusCode(), statusCodeExp);
		Assert.assertEquals(output.statusLine(), expStatusLine);
		return true;
	} catch (Exception var6) {
		System.out.println("Exception while posting the message " + txtFileLines + " to the URL " + strBaseURI
				+ strPostServiceURL + "\n\n" + var6.toString());
		return false;
	}
}

public String readTextFileAndGetAsString(String strFilePath) throws IOException {
	try {
		BufferedReader bufferedReader = new BufferedReader(new FileReader(strFilePath));
		String txtFileLines = "";

		for (String strCurrentLine = ""; (strCurrentLine = bufferedReader
				.readLine()) != null; txtFileLines = txtFileLines + strCurrentLine) {
			;
		}

		System.out.println(txtFileLines);
		return txtFileLines;
	} catch (Exception var5) {
		System.out.println("Exception while reading the file " + strFilePath + "\n" + var5.toString());
		return null;
	}
}

public boolean acceptAlert() throws Exception {
	try {
		Alert objAlert = this.driver.switchTo().alert();
		String strAlertText = objAlert.getText();
		objAlert.accept();
		ATUReports.add("Alert with text <i>" + strAlertText + "</i> accepted", "true",
				"true", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		logToConsole("Alert with text <i>" + strAlertText + "</i> accepted");
		return true;
	} catch (Exception var3) {
		this.strErrorMsg = var3.getMessage();
		ATUReports.add("Error while accepting the alert.<br> Error message=>" + this.strErrorMsg, "true",
				"true", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		logToConsole("Error while accepting the alert.<br> Error message=>" + this.strErrorMsg);
		return false;
	}
}







}
