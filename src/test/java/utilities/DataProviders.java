package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	//DataProvider1
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException
	{
		String path="./testData/LoginData.xlsx"; 	//taking xlfile from testdata 
		
		ExcelUtility xlutil=new ExcelUtility(path);  //creating an object for XLUtility
		
		int totalrows=xlutil.getRowCount("Sheet1");
		int totalcols=xlutil.getCellcount("Sheet1",1);
		
		String logindata[][]=new String [totalrows][totalcols]; 	//created for two-dimensional array which can store data from excel file
		
		for(int i=1;i<=totalrows;i++) 		//1  //read the data from xl storing in two dimensional array
		{
			for(int j=0;j<totalcols;j++)
			{
				logindata[i-1][j]=xlutil.getCellData("Sheet1", i, j); 		//1,0
			}
		}
		
		return logindata;  //returning two dimensional array
	}
	
	//DataProvider 2
	
	//DataProvider 3
	
	//DataProvider 4
	
	
}
