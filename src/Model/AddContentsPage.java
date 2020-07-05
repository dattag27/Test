package Model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class AddContentsPage {
	
	@FindBy(css=".pull-left > .button-u:nth-child(1)") public WebElement attach;
	@FindBy(css=".col-md-7:nth-child(3) > .mainselection > .full-width") public WebElement relatesTo;
	@FindBy(css=".mainselection > .ng-pristine") public WebElement periodName;
	@FindBy(id="txtDocumentTitle") public WebElement docTitle;
	@FindBy(id="txtReplaceDocumentTitle") public WebElement	editDocTitle;
	@FindBy(id="fakeBrowse2") public WebElement browse;
	@FindBy(css=".col-md-7:nth-child(13) > .ng-pristine") public WebElement saveBtn;
	@FindBy(xpath="//*[@class='col-md-12']/img") public WebElement attachedFile;
	@FindBy(css=".pull-left > .button-u:nth-child(2)") public WebElement edit;
	@FindBy(css="a > .dap-icon-edit-file-small") public WebElement editPencil;
	@FindBy(css=".col-md-6 > .ng-pristine") public WebElement update;
	@FindBy(css=".button-u:nth-child(3)") public WebElement doneEditing;
	
	public WebElement titleChanged(WebDriver driver,String title)
	{
		return driver.findElement(By.xpath("//*[contains(text(),'"+title+"')]"));
	}
	@FindBy(css="a > .dap-icon-bin") public WebElement deleteButton;

}
