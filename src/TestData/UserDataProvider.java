package TestData;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Guice;

@Guice
public class UserDataProvider {
	
	@DataProvider(name = "User")
	public static Object[][]provideData() throws IOException
	{
		try{
		ExcelReader reader=new ExcelReader("./Inputsheets/MultipleTestData.xlsx");
		int rowCount=reader.fetchRowCount("User");
		
		Object[][] excelColumnData=new Object[rowCount][5];
		
		for(int i=0;i<rowCount;i++)
		{
			excelColumnData[i][0]=reader.getData("User", i, 0);
			excelColumnData[i][1]=reader.getData("User", i, 1);
			excelColumnData[i][2]=reader.getData("User", i, 2);
			excelColumnData[i][3]=reader.getData("User", i, 3);
			excelColumnData[i][4]=reader.getData("User", i, 4);
		}
		
		return excelColumnData;
		
	}catch(Exception e)
		{
			e.printStackTrace();
			e.getLocalizedMessage();
			return null;
		}
		
	
}
}
