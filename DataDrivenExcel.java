package com.qa.testData;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


import com.qa.Base.TestBase;



public class DataDrivenExcel extends TestBase{
	
	

	/* The @SuppressWarnings annotation tells the compiler to suppress the warning messages it normally show during compilation time.
	 *  It has some level of suppression to be added to the code, these level including: all, deprecation, fallthrough, finally, path, serial and unchecked.
		@SuppressWarnings("deprecation")
	 */

	public static Object[][] ReadValues(String sheetName) throws IOException, EncryptedDocumentException, InvalidFormatException
	{
		//file name
		
		    
		  //create object of properties
			
		    FileInputStream fis = new FileInputStream(workingDir + "\\resources\\ExcelData.xlsx");
			
		//	XSSFWorkbook wb = new XSSFWorkbook(fis);
		    
		    Workbook book = WorkbookFactory.create(fis);
		    
		    
			
			//Sheets in a workbook - Limited by available memory  you can then add more > sheets until your system's resources run out
			org.apache.poi.ss.usermodel.Sheet sheet = book.getSheet(sheetName);
			
			Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			
			for(int i =0; i<sheet.getLastRowNum() ; i++)
			{
				for(int j= 0; j< sheet.getRow(0).getLastCellNum();j++)
				{
					data[i][j] = sheet.getRow(i+1).getCell(j).toString();
				}
			}
		return data;

	}
	
}
