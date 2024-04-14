package com.AnkitIndia.ExcelImport;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.springframework.web.multipart.MultipartFile;

import com.AnkitIndia.jwtauthentication.model.State_master;

public class ExcelHelper 
{
	 public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	 //"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
	 static String[] HEADERs = { "id", "state_code", "state_name", "country_code","country_name" };
	 static String SHEET = "State_master";
	  
	 public static boolean hasExcelFormat(MultipartFile file) 
	 {
		 //System.out.println("hello "+file.getContentType());
	    if (!TYPE.equals(file.getContentType()))
	    {
	    //	System.out.println("check");
	      return false;
	    }
	    
	    return true;
	 }
	 
	 
	  
	  
	  public static List<State_master> excelToTutorials(InputStream is) {
		    try {
		    	
		    	
		      Workbook workbook = new XSSFWorkbook(is);
		 
		    //  Sheet sheet = workbook.getSheet(SHEET);
		      Sheet sheet = workbook.getSheetAt(0);
		     
		      Iterator<Row> rows = sheet.iterator();
		     
		      List<State_master> tutorials = new ArrayList<State_master>();
		     
		      int rowNumber = 0;
		      while (rows.hasNext()) 
		      {
		        Row currentRow = rows.next();
		     
		        if (rowNumber == 0) 
		        {
		          rowNumber++;
		          continue;
		        }

		        Iterator<Cell> cellsInRow = currentRow.iterator();

		        State_master tutorial = new State_master();

		        int cellIdx = 0;
		        while (cellsInRow.hasNext()) {
		          Cell currentCell = cellsInRow.next();

		          switch (cellIdx) {
		          case 0:
		            tutorial.setId((long) currentCell.getNumericCellValue());
		            break;

		          case 1:
		            tutorial.setState_code(currentCell.getStringCellValue());
		            break;

		          case 2:
		            tutorial.setState_name(currentCell.getStringCellValue());
		            break;

		          case 3:
		            tutorial.setCountry_code(currentCell.getStringCellValue());
		            break;
		            
		          case 4:
			            tutorial.setCountry_name(currentCell.getStringCellValue());
			            break;   

		          default:
		            break;
		          }

		          cellIdx++;
		        }

		        tutorials.add(tutorial);
		      }

		      workbook.close();

		      return tutorials;
		    } catch (IOException e) {
		      throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
		    }
		  }
	
}
