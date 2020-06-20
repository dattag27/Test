package Base;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.Listeners;

import atu.testng.reports.ATUReports;
import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;

public class ComponentFunctions{
	
	public String strErrorMsg = "";
	public WebDriver driver = null;
	JavascriptExecutor objJSExecutor = null;
	String strObjectXPATH = "";
	
	
	
	
	
	
	
	
public static final String EXCELFILELOCATION="./Inputsheets/Data.xlsx";
	
	private static FileInputStream fis;
	private static XSSFWorkbook workbook;
	private static XSSFSheet  sheet;
	private static XSSFRow    row;
	
	public ComponentFunctions(WebDriver objTempWebDriver) throws Exception {
		
		this.driver = objTempWebDriver;
		this.objJSExecutor = (JavascriptExecutor) this.driver;
	
		
		}
	
	@Listeners({ ATUReportsListener.class,ConfigurationListener.class,MethodListener.class })
	public class AUTreports {
	{ 
		System.setProperty("atu.reporter.config", "./Config/ATU.properties");
	}
}

	
	public static void loadExcel() throws IOException
	{
		File file=new File(EXCELFILELOCATION);
		fis=new FileInputStream(file);
		workbook=new XSSFWorkbook(fis);
		sheet=workbook.getSheet("Sheet1");
		fis.close();
		
		
	}
	public static Map<String, Map<String,String>> getData() throws IOException{
		if(sheet==null){
			loadExcel();
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
	public static String getValue(String key) throws IOException
	{
		getData();
		Map<String , String> myVal = getData().get("MASTERDATA");
		String retValue=myVal.get(key);
		return retValue;
		
	}

public Boolean typeValue(WebElement objWebElement, String strObjectName, String strInputValue) throws Exception
{
	try {
		objWebElement.sendKeys(new CharSequence[]{strInputValue});
		String strObjectXPATH = "";
		ATUReports.add("Value " + strInputValue + " typed in element " + strObjectName, LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		BaseScripts.logTestPass(strInputValue,strObjectName);
		captureScreenshotOfElement(objWebElement, strObjectName, "Demo");
		return true;
	}catch(Exception e)
	{
		this.strErrorMsg = e.toString();
		ATUReports.add("Unable to type Value " + strInputValue + " in element " + strObjectName, "true","false", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		BaseScripts.logTestFail(strInputValue,strObjectName);
		return false;
	}
	
	
	
}
public Boolean clickObject(WebElement objWebElement, String strObjectName) throws Exception {
	try {
		objWebElement.click();
		String strObjectXPATH = "";
		ATUReports.add("Element " + strObjectName + "</i> clicked",LogAs.PASSED,new CaptureScreen(ScreenshotOf.DESKTOP));
		BaseScripts.logTestPass(strObjectName );
		return true;
	} catch (Exception e) {
		this.strErrorMsg = e.toString();
		ATUReports.add(
				strObjectName + " couldn't be clicked. <br> Error message=>" + this.strErrorMsg,LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		BaseScripts.logTestFail(strObjectName + "not clicked");
		return false;
	}
}
public Boolean waitTillElementEnabled(WebElement objWebElement, String strObjectName, int intWaitTime)
		throws Exception {
	try {
		(new WebDriverWait(this.driver, (long) intWaitTime))
				.until(ExpectedConditions.elementToBeClickable(objWebElement));
		Reporter.log(objWebElement+ " loaded properly");
		BaseScripts.logTestPass(strObjectName+ " Loaded properly");
		return true;
	} catch (Exception var5) {
		this.strErrorMsg = var5.toString();
		Reporter.log(objWebElement+ "not loaded properly");
		BaseScripts.logTestFail(strObjectName+ "Not Loaded properly");
		return false;
	}
	
	
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


}
