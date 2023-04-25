package saucelab.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import saucelab.qa.base.TestBase;

public class TestUtil extends TestBase{
	
	public static final long PAGE_LOAD_TIMEOUT = 30;
	public static final long IMPLICIT_WAIT = 30;
	
	public static String TESTDATA_SHEET_PATH = System.getProperty("user.dir")+ "\\src\\main\\java\\saucelab\\qa\\testdata\\SauceDemo_UserLogin.xlsx";

	static Workbook book;
	static Sheet sheet;
	
	public TestUtil()
	{
		PageFactory.initElements(driver,this);
	}
	
	
	public static void ValidateUserLogin(String UserName,String Password) {
		driver.findElement(By.id("user-name")).sendKeys(UserName);
		driver.findElement(By.id("password")).sendKeys(Password);
		driver.findElement(By.xpath("//input[@id='login-button']")).click();
	}
	

	
	
		// It is used for dropdown selection
		
		public static void Dropdown_select(WebElement dropdown_element,String dropdown_text)
		{
			Select dropdown_option = new Select(dropdown_element);
			dropdown_option.selectByVisibleText(dropdown_text);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}		
			}
		
	
		public static void takeScreenshotOfTests()  {
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    String testName = Thread.currentThread().getStackTrace()[2].getMethodName();
		    File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		    String currentDir = System.getProperty("user.dir");
		    String fileName = testName + "_" + System.currentTimeMillis() + ".png";
		    try {
				FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + fileName));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		

		
		
		// It is used for taking data from excel sheets
		public static Object[][] getTestData(String sheetName) {
			FileInputStream file = null;
			try {
				file = new FileInputStream(TESTDATA_SHEET_PATH);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				book = WorkbookFactory.create(file);
			} catch (InvalidFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			sheet = book.getSheet(sheetName);
			Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			// System.out.println(sheet.getLastRowNum() + "--------" +
			// sheet.getRow(0).getLastCellNum());
			for (int i = 0; i < sheet.getLastRowNum(); i++) {
				for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
					data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
					// System.out.println(data[i][k]);
				}
			}
			return data;
		}
		
		public static  double getInt(String str1)
		{
			String str = str1;

			// Get the substring after the dollar sign
			String priceStr = str.substring(str.indexOf("$") + 1);

			// Convert the price string to a double value
			double price = Double.parseDouble(priceStr);
			return price;

			
		}

}
