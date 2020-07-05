package TestData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	
	private XSSFWorkbook workbook;
	private XSSFSheet    sheet;
	
	public ExcelReader(String excel) throws IOException
	{
		File file=new File("./Inputsheets/"+excel);
		FileInputStream fis=new FileInputStream(file);
		workbook=new XSSFWorkbook(fis);
	}
	
	public String getData(String sheetName,int row,int column)
	{
		sheet=workbook.getSheet(sheetName);
		String returnData=sheet.getRow(row).getCell(column).getStringCellValue();
		return returnData;
		
	}
	public int fetchRowCount(String sheetName)
	{
		int rows=workbook.getSheet(sheetName).getLastRowNum();
		rows=rows+1;
		return rows;
		
		
	}

	 public int getRowCount(String excel, String Sheet)

     {

         try

              {
        	 		File file=new File("./Inputsheets/"+excel);
        	 		FileInputStream fis = new FileInputStream(file);
        	 		XSSFWorkbook wb = new XSSFWorkbook(fis);
        	 		return wb.getSheet(Sheet).getLastRowNum();
              }catch (Exception e)
         			{
            	  	return 0;
         			}

   }



}
