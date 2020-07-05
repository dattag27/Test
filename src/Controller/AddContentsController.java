package Controller;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.PageFactory;

import Base.ComponentFunctions;
import Model.AddContentsPage;
import Model.AddUserOrangePage;

public class AddContentsController extends ComponentFunctions{
	
		WebDriver driver=null;
		AddContentsPage addContents=null;
		
		
		public AddContentsController(WebDriver driver) throws Exception 
		{
			super(driver);
			this.driver = driver;
			addContents =PageFactory.initElements(driver, AddContentsPage.class);		
		}
		
		public void clickAttach() throws Exception
		{
			waitTillElementEnabled(addContents.attach, "Attach Button",10);
			clickObject(addContents.attach, "Attach");
		}
		public void selectValueRelatesTo(String value) throws Exception
		{
			selectValueByValueFromList(addContents.relatesTo, "Relates To", value);
		}
		public void selectValuePeriodName(String value) throws Exception
		{
			selectValueByValueFromList(addContents.periodName, "Period name To", value);
		}
		public void enterDocTitle(String title) throws Exception
		{
			clearNTypeValue(addContents.editDocTitle,"Doc Title", title);
		}
		public void enterDocOriginalTitle(String title) throws Exception
		{
			clearNTypeValue(addContents.docTitle,"Doc Title", title);
		}
		public void enterDocEditTitle(String title) throws Exception
		{
			clearNTypeValue(addContents.edit,"Doc Title", title);
		}
		public void verifyTitleChanged(String title) throws Exception
		{
			checkObjectExists(addContents.titleChanged(driver, title), title);
		}
		public void clickBrowse() throws Exception
		{
			clickObject(addContents.browse, "Browse");
		}
		public void clickSave() throws Exception
		{
			clickObject(addContents.saveBtn, "save Button");
		}
		public Boolean verfiypresenceOfContent()
		{
			try
			{
			waiting(2);
			RefreshPage();
			waiting(2);
			checkObjectExists(addContents.attachedFile, "File Content");
			logToConsole("File is present..expected");
			return true;
		}catch(Exception e)
			{
			logToConsole("File is not present... This is not expected");
			return false;
			
			}
		}
		public void verfiyNonEditable()
		{
			try{
		    	checkObjectNotExists(addContents.edit,"Attach");
		    	logToConsole("Edit Button Not Present..  expected");
		    }catch(Exception e)
		    	{
		    		logToConsole("Edit Button Present and is not expected!!!");
		    	}

		}
		public void clickEdit() throws Exception
		{
			waitTillElementDisplayed(addContents.edit, "Edit", 10);
			clickObject(addContents.edit, "Edit ");
			
			
		}
		
		public void clickEditPencil() throws Exception
		{
			waitTillElementDisplayed(addContents.editPencil, "Edit Pencil", 3);
			clickObject(addContents.editPencil, "Edit Pencil");
		}
		public void clickDelete() throws Exception
		{
			waitTillElementDisplayed(addContents.deleteButton, "Delete button", 3);
			clickObject(addContents.deleteButton, "Delete Button");
			waiting(1);
			acceptAlert();
			waiting(1);
		}
		public void doneEditing() throws Exception
		{
			waitTillElementEnabled(addContents.doneEditing,"Done Editing",3);
			clickObject(addContents.doneEditing, "Done Editing");
			
		}
		public void update() throws Exception
		{
			clickObject(addContents.update, "Update");
			acceptAlert();
			waiting(2);
			
		}
	
	public void verifyContentDeleted(String title)
	{
		try{
	    	checkObjectNotExists(addContents.titleChanged(driver, title),"Content");
	    	logToConsole("Content   Present.. not  expected");
	    }catch(Exception e)
	    	{
	    		logToConsole("Content Not Present and is expected!!!");
	    	}
	
	}
	
	public Boolean verfiypresenceOfContent1()
	{
		try
		{
		waiting(2);
		RefreshPage();
		waiting(2);
		Boolean exist=addContents.attachedFile.isDisplayed();
		if(exist)
		{
		logToConsole("File is present..expected");
		return true;
		}else
		{
			logToConsole("File is present..expected");
			return false;
		}
	}catch(Exception e)
		{
		logToConsole("File is not present... This is not expected");
		return false;
		
		}
	}
	}
		

