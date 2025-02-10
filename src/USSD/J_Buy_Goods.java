package USSD;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.TestNG;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Features;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;


public class J_Buy_Goods 
{
    
    String my_dir = System.getProperty("user.dir");
	WebDriver driver;
	JavascriptExecutor js; 
	XSSFWorkbook USSD;
	XSSFSheet sheet;
	XSSFCell cell;
//***********************************************************************************************************************************                
@BeforeTest
public void setUp() 
	{   
	System.setProperty("webdriver.chrome.driver", my_dir+"\\Chrome\\chromedriver.exe"); 
	driver = new ChromeDriver();
	}
//***********************************************************************************************************************************        
@AfterTest        
public void tearDown() 
  {
 driver.quit();
  }
//**********************************************************************************************************************************
public static void main(String[] args)
  	{
  	TestNG testng = new TestNG();
	testng.setTestClasses(new Class[] {J_Buy_Goods.class});
	testng.run();
  	}



 
@Test (priority=1)
@Epic("USSD BILL PAYMENT")
@Features(value = { @Feature(value = "Electricity postpaid") })
@Step ("Check if system shows error when using amount less than kes 50 -Postpaid amount")
@Severity(SeverityLevel.BLOCKER)
public void Check_if_system_shows_error_when_using_amount_less_than_kes_50_Postpaid_amount() throws InterruptedException, IOException
	{
  
    		File src = new File(my_dir + "\\Data_Driven_Excel_File\\USSD.xlsx");    
    		FileInputStream fis = new FileInputStream(src);       
    		USSD = new XSSFWorkbook(fis);      
    		sheet = USSD.getSheet("USSD");
    		cell = sheet.getRow(2).getCell(0);       
   		DataFormatter formatter = new DataFormatter();
   		cell = sheet.getRow(2).getCell(1);
    		formatter.formatCellValue(sheet.getRow(2).getCell(1));     
    		String val_PHONE_NO = formatter.formatCellValue(sheet.getRow(2).getCell(2)); 
    		formatter.formatCellValue(sheet.getRow(2).getCell(3));
         	new Random();
         	long random_MSSID = ThreadLocalRandom.current().nextLong(800000000L, 89999999999L);
         	String USSD_URL = "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95";
         	driver.manage().window().maximize();
         	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
         	driver.get(USSD_URL);
         	String USSD_URL1= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111";
          	driver.get(USSD_URL1); 
          	assertTrue(driver.getPageSource().contains("Account Enquiry")); 
          	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3";
          	driver.get(USSD_URL2); 
          	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1";
          	driver.get(USSD_URL3);
          	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*2";
          	driver.get(USSD_URL4);
          	String USSD_URL5= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*2*2";
          	driver.get(USSD_URL5);
          	String USSD_URL6= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*2*2*2";
          	driver.get(USSD_URL6);
          	String USSD_URL7= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*2*2*2*0112541334100";
          	driver.get(USSD_URL7);
          	String USSD_URL8= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*2*2*2*0112541334100*1";
          	driver.get(USSD_URL8);
          	String USSD_URL9= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*2*2*2*0112541334100*1*40";
          	driver.get(USSD_URL9);
          	assertTrue(driver.getPageSource().contains("Enter Amount Between 50.00 and 70,000.00")); 
          	

	}
@Test (priority=2)
@Epic("USSD BILL PAYMENT")
@Features(value = { @Feature(value = "Electricity postpaid") })
@Step ("Check if system shows error when using amount More than kes 200000 Daily limit-Postpaid amount")
@Severity(SeverityLevel.BLOCKER)
public void Check_if_system_shows_error_when_using_amount_More_than_kes_200000_Daily_limit_Postpaid_amount() throws InterruptedException, IOException
	{
  
    		File src = new File(my_dir + "\\Data_Driven_Excel_File\\USSD.xlsx");    
    		FileInputStream fis = new FileInputStream(src);       
    		USSD = new XSSFWorkbook(fis);      
    		sheet = USSD.getSheet("USSD");
    		cell = sheet.getRow(2).getCell(0);       
   		DataFormatter formatter = new DataFormatter();
   		cell = sheet.getRow(2).getCell(1);
    		formatter.formatCellValue(sheet.getRow(2).getCell(1));     
    		String val_PHONE_NO = formatter.formatCellValue(sheet.getRow(2).getCell(2)); 
    		formatter.formatCellValue(sheet.getRow(2).getCell(3));
         	new Random();
         	long random_MSSID = ThreadLocalRandom.current().nextLong(800000000L, 89999999999L);
         	String USSD_URL = "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95";
         	driver.manage().window().maximize();
         	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
         	driver.get(USSD_URL);
         	String USSD_URL1= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111";
          	driver.get(USSD_URL1); 
          	assertTrue(driver.getPageSource().contains("Account Enquiry")); 
          	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3";
          	driver.get(USSD_URL2); 
          	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1";
          	driver.get(USSD_URL3);
          	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*2";
          	driver.get(USSD_URL4);
          	String USSD_URL5= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*2*2";
          	driver.get(USSD_URL5);
          	String USSD_URL6= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*2*2*2";
          	driver.get(USSD_URL6);
          	String USSD_URL7= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*2*2*2*0112541334100";
          	driver.get(USSD_URL7);
          	String USSD_URL8= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*2*2*2*0112541334100*1";
          	driver.get(USSD_URL8);
          	String USSD_URL9= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*2*2*2*0112541334100*1*200000";
          	driver.get(USSD_URL9);

	}
@Test (priority=3)
@Epic("USSD BILL PAYMENT")
@Features(value = { @Feature(value = "Electricity postpaid") })
@Step ("Check if system shows error when using amount more than kes 70000-Postpaid amount")
@Severity(SeverityLevel.BLOCKER)
public void Check_if_system_shows_error_when_using_amount_More_than_kes_70000_Postpaid_amount() throws InterruptedException, IOException
	{
  
    		File src = new File(my_dir + "\\Data_Driven_Excel_File\\USSD.xlsx");    
    		FileInputStream fis = new FileInputStream(src);       
    		USSD = new XSSFWorkbook(fis);      
    		sheet = USSD.getSheet("USSD");
    		cell = sheet.getRow(2).getCell(0);       
   		DataFormatter formatter = new DataFormatter();
   		cell = sheet.getRow(2).getCell(1);
    		formatter.formatCellValue(sheet.getRow(2).getCell(1));     
    		String val_PHONE_NO = formatter.formatCellValue(sheet.getRow(2).getCell(2)); 
    		formatter.formatCellValue(sheet.getRow(2).getCell(3));
         	new Random();
         	long random_MSSID = ThreadLocalRandom.current().nextLong(800000000L, 89999999999L);
         	String USSD_URL = "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95";
         	driver.manage().window().maximize();
         	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
         	driver.get(USSD_URL);
         	String USSD_URL1= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111";
          	driver.get(USSD_URL1); 
          	assertTrue(driver.getPageSource().contains("Account Enquiry")); 
          	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3";
          	driver.get(USSD_URL2); 
          	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1";
          	driver.get(USSD_URL3);
          	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*2";
          	driver.get(USSD_URL4);
          	String USSD_URL5= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*2*2";
          	driver.get(USSD_URL5);
          	String USSD_URL6= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*2*2*2";
          	driver.get(USSD_URL6);
          	String USSD_URL7= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*2*2*2*0112541334100";
          	driver.get(USSD_URL7);
          	String USSD_URL8= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*2*2*2*0112541334100*1";
          	driver.get(USSD_URL8);
          	String USSD_URL9= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*2*2*2*0112541334100*1*70001";
          	driver.get(USSD_URL9);

	}
@Test (priority=4)
@Epic("USSD BILL PAYMENT")
@Features(value = { @Feature(value = "Electricity postpaid") })
@Step ("Check if system shows error when using invalid data-invalid account number")
@Severity(SeverityLevel.BLOCKER)
public void Check_if_system_shows_error_when_using_invalid_data_invalid_account_number() throws InterruptedException, IOException
	{
   
    		File src = new File(my_dir + "\\Data_Driven_Excel_File\\USSD.xlsx");    
    		FileInputStream fis = new FileInputStream(src);       
    		USSD = new XSSFWorkbook(fis);      
    		sheet = USSD.getSheet("USSD");
    		cell = sheet.getRow(2).getCell(0);       
   		DataFormatter formatter = new DataFormatter();
   		cell = sheet.getRow(2).getCell(1);
    		formatter.formatCellValue(sheet.getRow(2).getCell(1));     
    		String val_PHONE_NO = formatter.formatCellValue(sheet.getRow(2).getCell(2)); 
    		formatter.formatCellValue(sheet.getRow(2).getCell(3));
         	new Random();
         	long random_MSSID = ThreadLocalRandom.current().nextLong(800000000L, 89999999999L);
         	String USSD_URL = "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95";
         	driver.manage().window().maximize();
         	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
         	driver.get(USSD_URL);
         	String USSD_URL1= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111";
          	driver.get(USSD_URL1); 
          	assertTrue(driver.getPageSource().contains("Account Enquiry")); 
          	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3";
          	driver.get(USSD_URL2); 
          	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1";
          	driver.get(USSD_URL3);
          	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*2";
          	driver.get(USSD_URL4);
          	String USSD_URL5= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*2*2";
          	driver.get(USSD_URL5);
          	String USSD_URL6= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*2*2*2";
          	driver.get(USSD_URL6);
          	String USSD_URL7= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*2*2*2*0112549991334100";
          	driver.get(USSD_URL7);
          	String USSD_URL8= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*2*2*2*0112549991334100*6";
          	driver.get(USSD_URL8);

	}

@Test (priority=5)
@Epic("USSD BILL PAYMENT")
@Features(value = { @Feature(value = "Electricity postpaid") })
@Step ("Check if system shows error when using invalid data-invalid postpaid account number")
@Severity(SeverityLevel.BLOCKER)
public void Check_if_system_shows_error_when_using_invalid_data_invalid_postpaid_account_number() throws InterruptedException, IOException
	{
   

    		File src = new File(my_dir + "\\Data_Driven_Excel_File\\USSD.xlsx");    
    		FileInputStream fis = new FileInputStream(src);       
    		USSD = new XSSFWorkbook(fis);      
    		sheet = USSD.getSheet("USSD");
    		cell = sheet.getRow(2).getCell(0);       
   		DataFormatter formatter = new DataFormatter();
   		cell = sheet.getRow(2).getCell(1);
    		formatter.formatCellValue(sheet.getRow(2).getCell(1));     
    		String val_PHONE_NO = formatter.formatCellValue(sheet.getRow(2).getCell(2)); 
    		formatter.formatCellValue(sheet.getRow(2).getCell(3));
         	new Random();
         	long random_MSSID = ThreadLocalRandom.current().nextLong(800000000L, 89999999999L);
         	String USSD_URL = "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95";
         	driver.manage().window().maximize();
         	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
         	driver.get(USSD_URL);
         	String USSD_URL1= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111";
          	driver.get(USSD_URL1); 
          	assertTrue(driver.getPageSource().contains("Account Enquiry")); 
          	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3";
          	driver.get(USSD_URL2); 
          	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1";
          	driver.get(USSD_URL3);
          	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*2";
          	driver.get(USSD_URL4);
          	String USSD_URL5= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*2*2";
          	driver.get(USSD_URL5);
          	String USSD_URL6= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*2*2*2";
          	driver.get(USSD_URL6);
          	String USSD_URL7= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*2*2*2*01125476771334100";
          	driver.get(USSD_URL7);

	}
@Test (priority=6)
@Epic("USSD BILL PAYMENT")
@Features(value = { @Feature(value = "Electricity postpaid") })
@Step ("Verify correct charges on successful electricity postpaid bill")
@Severity(SeverityLevel.BLOCKER)
public void Verify_correct_charges_on_successful_electricity_postpaid_bill() throws InterruptedException, IOException
	{
  

    		File src = new File(my_dir + "\\Data_Driven_Excel_File\\USSD.xlsx");    
    		FileInputStream fis = new FileInputStream(src);       
    		USSD = new XSSFWorkbook(fis);      
    		sheet = USSD.getSheet("USSD");
    		cell = sheet.getRow(2).getCell(0);       
   		DataFormatter formatter = new DataFormatter();
   		cell = sheet.getRow(2).getCell(1);
    		formatter.formatCellValue(sheet.getRow(2).getCell(1));     
    		String val_PHONE_NO = formatter.formatCellValue(sheet.getRow(2).getCell(2)); 
    		formatter.formatCellValue(sheet.getRow(2).getCell(3));
         	new Random();
         	long random_MSSID = ThreadLocalRandom.current().nextLong(800000000L, 89999999999L);
         	String USSD_URL = "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95";
         	driver.manage().window().maximize();
         	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
         	driver.get(USSD_URL);
         	String USSD_URL1= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111";
          	driver.get(USSD_URL1); 
          	assertTrue(driver.getPageSource().contains("Account Enquiry")); 
          	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3";
          	driver.get(USSD_URL2); 
          	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1";
          	driver.get(USSD_URL3);
          	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*2";
          	driver.get(USSD_URL4);
          	String USSD_URL5= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*2*2";
          	driver.get(USSD_URL5);
          	String USSD_URL6= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*2*2*2";
          	driver.get(USSD_URL6);
          	String USSD_URL7= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*2*2*2*0112541334100";
          	driver.get(USSD_URL7);
          	String USSD_URL8= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*2*2*2*0112541334100*6";
          	driver.get(USSD_URL8);
          	assertTrue(driver.getPageSource().contains("Enter amount"));
          	String USSD_URL9= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*2*2*2*0112541334100*1*50";
          	driver.get(USSD_URL9);

	}

@Test (priority=7)
@Epic("USSD BILL PAYMENT")
@Features(value = { @Feature(value = "Electricity postpaid") })
@Step ("Verify that customers debit account is correctly narrated ")
@Severity(SeverityLevel.BLOCKER)
public void Verify_that_customers_debit_account_is_correctly_narrated () throws InterruptedException, IOException
	{
  

    		File src = new File(my_dir + "\\Data_Driven_Excel_File\\USSD.xlsx");    
    		FileInputStream fis = new FileInputStream(src);       
    		USSD = new XSSFWorkbook(fis);      
    		sheet = USSD.getSheet("USSD");
    		cell = sheet.getRow(2).getCell(0);       
   		DataFormatter formatter = new DataFormatter();
   		cell = sheet.getRow(2).getCell(1);
    		formatter.formatCellValue(sheet.getRow(2).getCell(1));     
    		String val_PHONE_NO = formatter.formatCellValue(sheet.getRow(2).getCell(2)); 
    		formatter.formatCellValue(sheet.getRow(2).getCell(3));
         	new Random();
         	long random_MSSID = ThreadLocalRandom.current().nextLong(800000000L, 89999999999L);
         	String USSD_URL = "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95";
         	driver.manage().window().maximize();
         	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
         	driver.get(USSD_URL);
         	String USSD_URL1= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111";
          	driver.get(USSD_URL1); 
          	assertTrue(driver.getPageSource().contains("Account Enquiry")); 
          	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3";
          	driver.get(USSD_URL2); 
          	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1";
          	driver.get(USSD_URL3);
          	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*2";
          	driver.get(USSD_URL4);
          	String USSD_URL5= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*2*2";
          	driver.get(USSD_URL5);
          	String USSD_URL6= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*2*2*2";
          	driver.get(USSD_URL6);
          	String USSD_URL7= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*2*2*2*0112541334100";
          	driver.get(USSD_URL7);
          	assertTrue(driver.getPageSource().contains("CON Select Account")); 

	}
}

