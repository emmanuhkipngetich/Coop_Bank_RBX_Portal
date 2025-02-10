
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
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.TestNG;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.common.io.Files;

import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Features;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;


public class F_Bill_Payment 
{
    
    
    String my_dir = System.getProperty("user.dir");
	WebDriver driver;
	JavascriptExecutor js; 
	XSSFWorkbook USSD;
	XSSFSheet sheet;
	XSSFCell cell;
	File src = new File(my_dir + "\\Data_Driven_Excel_File\\USSD.xlsx");
	
	     
	
	// **********************************************************************************************************************************
	@BeforeTest
	public void setUp()
		{    			
	    	String os = System.getProperty("os.name").toLowerCase();    			
	    	if (os.contains("win"))
	    	{    			    
		System.out.println("You Are on Windows "); 			   
		System.setProperty("webdriver.chrome.driver", my_dir + "\\Chrome\\chromedriver.exe");
	    	driver = new ChromeDriver(getChromeOptions());
	    	} 
	    	else if 
	    	(os.contains("nix") || os.contains("aix") || os.contains("nux")) 
	    	{
	    	    System.out.println("You Are on Linux ");
	    	    System.setProperty("webdriver.chrome.driver", my_dir + "/chromedriver");
	    	    System.out.println("Chrome Driver Path = " + my_dir + "/chromedriver");
	    	    driver = new ChromeDriver(getChromeOptions());
	    	} 
	    	else 
	    	{
	    	    System.out.println("Not Detected");
	    	}
	}
	// **************************************************************************************************************************************
	private ChromeOptions getChromeOptions()
		{
	    	final ChromeOptions options = new ChromeOptions();
	    	options.addArguments("--headless");
	    	options.addArguments("--window-size=1280,800");
	    	return options;
	  	}
	// **************************************************************************************************************************************

	public class ScreenshotUtils {
	    @Attachment(type = "image/png") 
	    public byte[] screenshot(WebDriver driver)
	    {
	        try {
	            File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	            return Files.toByteArray(screen);
	        } catch (IOException e) {
	            return null;
	        }
	    }
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
	testng.setTestClasses(new Class[] {F_Bill_Payment.class});
	testng.run();
  	}

@Test(priority = 0)
@Epic("PAYMENT")
@Features(value = { @Feature(value = "DSTV") })
@Step ("DSTV")
@Description ("DSTV")
@Severity(SeverityLevel.TRIVIAL)
public void Starting_DSTV_Test_cases() 
	{	
	System.out.println("PAYMENTS");
	System.out.println("************************DSTV TESTCASES***********************************");

	}
//**********************************************************************************************************************************

@Test (priority=1)
@Epic("PAYMENT")
@Features(value = { @Feature(value = "DSTV") })
@Step ("Check if Customer can acess payment menu on ussd")
@Description("Check if Customer can acess payment menu on ussd")
@Severity(SeverityLevel.BLOCKER)
public void Check_if_customer_can_acess_payment_menu_on_ussd() throws InterruptedException, IOException
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
          	Thread.sleep(3000);
          	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3";
          	driver.get(USSD_URL2);           	         	
          	assertTrue(driver.getPageSource().contains("CON Payments"));
          	System.out.println("TC1: Check if Customer can acess payment menu on ussd|Sucess");
          	

	}
//**********************************************************************************************************************************
@Test (priority=2)
@Epic("PAYMENT")
@Features(value = { @Feature(value = "DSTV") })
@Step ("Check if Customer can acess PAYTV menu on ussd")
@Description("Check if Customer can acess PAYTV menu on ussd")
@Severity(SeverityLevel.BLOCKER)
public void Check_if_customer_can_acess_PAYTV_menu_on_ussd() throws InterruptedException, IOException
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
          	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3";
          	driver.get(USSD_URL2);      	         	
               	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1";
          	driver.get(USSD_URL3);           	         	
          	assertTrue(driver.getPageSource().contains("Pay TV"));
          	System.out.println("TC2: Check if Customer can acess PAYTV menu on ussd|Sucess");
          	

	}
//**********************************************************************************************************************************
@Test (priority=3)
@Epic("PAYMENT")
@Features(value = { @Feature(value = "DSTV") })
@Step ("Check if Customer can select on DSTV menu on ussd")
@Description("Check if Customer can select on DSTV menu on ussd")
@Severity(SeverityLevel.BLOCKER)
public void Check_if_customer_can_acess_DSTV_menu_on_ussd() throws InterruptedException, IOException
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
          	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3";
          	driver.get(USSD_URL2);      	         	
        
          	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1";
          	driver.get(USSD_URL3);
          	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1";
          	driver.get(USSD_URL4);
          	assertTrue(driver.getPageSource().contains("DSTV"));
          	System.out.println("TC3: Check if Customer can select on DSTV menu on ussd|Sucess");
          	

	}
//**********************************************************************************************************************************
@Test (priority=4)
@Epic("PAYMENT")
@Features(value = { @Feature(value = "DSTV") })
@Step ("Check if Customer can query for DSTV bills  menu on ussd")
@Description("Check if Customer can query for DSTV bills  menu on ussd")
@Severity(SeverityLevel.BLOCKER)
public void Check_if_customer_can_Query_for_DSTV_bills_on_ussd() throws InterruptedException, IOException
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
          	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3";
          	driver.get(USSD_URL2);      	         	
        
          	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1";
          	driver.get(USSD_URL3);
          	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1";
          	driver.get(USSD_URL4);
          	assertTrue(driver.getPageSource().contains("DSTV"));
          	String USSD_URL5= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*1";
          	driver.get(USSD_URL5);
               	System.out.println("TC4: Check if Customer can query for DSTV bills  menu on ussd|Sucess");
          	

	}
//**********************************************************************************************************************************
//customer should use main menu item on dstv
@Test (priority=5)
@Epic("PAYMENT")
@Features(value = { @Feature(value = "DSTV") })
@Step ("Check if Customer can use main item on the list paytv item menu")
@Description("Check if Customer can use main item on the list paytv item menu")
@Severity(SeverityLevel.BLOCKER)
public void Check_if_Customer_can_use_main_item_on_the_list_paytv_item_menu() throws InterruptedException, IOException
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
          	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3";
          	driver.get(USSD_URL2);      	         	
        
          	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1";
          	driver.get(USSD_URL3);
          	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1";
          	driver.get(USSD_URL4);
          	assertTrue(driver.getPageSource().contains("DSTV"));
          	String USSD_URL5= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*1";
          	driver.get(USSD_URL5);
          	String USSD_URL6= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*1*1";
          	driver.get(USSD_URL6);
          	String USSD_URL7= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*1*1*3";
          	driver.get(USSD_URL7);
               	System.out.println("TC5: Check if Customer can use main item on the list paytv item menu|Sucess");
          	

	}
//**********************************************************************************************************************************
@Test (priority=6)
@Epic("PAYMENT")
@Features(value = { @Feature(value = "DSTV") })
@Step ("Verify whether a customer can enter DSTV number and validate")
@Description("Verify whether a customer can enter DSTV number and validate")
@Severity(SeverityLevel.BLOCKER)
public void Verify_whether_a_customer_can_enter_DSTV_number_and_validate() throws InterruptedException, IOException
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
          	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3";
          	driver.get(USSD_URL2);      	         	
        
          	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1";
          	driver.get(USSD_URL3);
          	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1";
          	driver.get(USSD_URL4);
          	assertTrue(driver.getPageSource().contains("DSTV"));
          	String USSD_URL5= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*1";
          	driver.get(USSD_URL5);
          	String USSD_URL6= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*1*1";
          	driver.get(USSD_URL6);          	
          	assertTrue(driver.getPageSource().contains("Please Enter DSTV Account Number"));          	  
          	    
	}

@Test (priority=7)
@Epic("PAYMENT")
@Features(value = { @Feature(value = "DSTV") })
@Step ("Check if Customer can enter amount to pay for DSTV on ussd")
@Description("Check if Customer can enter amount to pay for DSTV on ussd")
@Severity(SeverityLevel.BLOCKER)
public void Check_if_Customer_can_enter_amount_to_pay_for_DSTV_on_ussd() throws InterruptedException, IOException
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
          	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3";
          	driver.get(USSD_URL2);      	         	
        
          	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1";
          	driver.get(USSD_URL3);
          	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1";
          	driver.get(USSD_URL4);
          	assertTrue(driver.getPageSource().contains("DSTV"));
          	String USSD_URL5= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*1";
          	driver.get(USSD_URL5);
          	String USSD_URL6= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*1*2";
          	driver.get(USSD_URL6);
          	String USSD_URL7= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*1*2*4122276313";
          	driver.get(USSD_URL7);
          	String USSD_URL8= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*1*2*4122276313*1";
          	driver.get(USSD_URL8);
          	assertTrue(driver.getPageSource().contains("CON Enter amount"));
               	System.out.println("TC7: Check if Customer can enter amount to pay for DSTV on ussd|Sucess");
          	

	}
//**********************************************************************************************************************************
@Test (priority=8)
@Epic("PAYMENT")
@Features(value = { @Feature(value = "DSTV") })
@Step ("Check if Customer can confirm bank charges for DSTV on ussd")
@Description("Check if Customer can confirm bank charges for DSTV on ussd")
@Severity(SeverityLevel.BLOCKER)
public void Check_if_Customer_can_confirm_bank_charges_for_DSTV_on_ussd() throws InterruptedException, IOException
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
          	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3";
          	driver.get(USSD_URL2);      	         	
        
          	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1";
          	driver.get(USSD_URL3);
          	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1";
          	driver.get(USSD_URL4);
          	assertTrue(driver.getPageSource().contains("DSTV"));
          	String USSD_URL5= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*1";
          	driver.get(USSD_URL5);
          	String USSD_URL6= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*1*2";
          	driver.get(USSD_URL6);
          	String USSD_URL7= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*1*2*4122276313";
          	driver.get(USSD_URL7);
          	String USSD_URL8= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*1*2*4122276313*1";
          	driver.get(USSD_URL8);
          	assertTrue(driver.getPageSource().contains("CON Enter amount"));
          	String USSD_URL9= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*1*2*4122276313*1*200";
          	driver.get(USSD_URL9);
          	assertTrue(driver.getPageSource().contains("CON Confirm Charges: Bank Charges"));
               	System.out.println("TC8: Check if Customer can confirm bank charges for DSTV on ussd|Sucess");
          	

	}
//**********************************************************************************************************************************

@Test (priority=9)
@Epic("PAYMENT")
@Features(value = { @Feature(value = "DSTV") })
@Step ("Verify that only the maximum allowable amount of kes 70,000 can be posted per transaction")
@Description("Verify that only the maximum allowable amount of kes 70,000 can be posted per transaction")
@Severity(SeverityLevel.BLOCKER)
public void Verify_that_only_the_maximum_allowable_amount_of_kes_70000_can_be_posted_per_transaction() throws InterruptedException, IOException
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
          	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3";
          	driver.get(USSD_URL2);      	         	
        
          	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1";
          	driver.get(USSD_URL3);
          	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1";
          	driver.get(USSD_URL4);
          	assertTrue(driver.getPageSource().contains("DSTV"));
          	String USSD_URL5= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*1";
          	driver.get(USSD_URL5);
          	String USSD_URL6= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*1*2";
          	driver.get(USSD_URL6);
          	String USSD_URL7= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*1*2*4122276313";
          	driver.get(USSD_URL7);
          	String USSD_URL8= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*1*2*4122276313*1";
          	driver.get(USSD_URL8);
          	assertTrue(driver.getPageSource().contains("CON Enter amount"));
          	String USSD_URL9= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*1*2*4122276313*1*70001";
          	driver.get(USSD_URL9);
          	assertTrue(driver.getPageSource().contains("trials Remaining: Enter Amount Between 50.00 and 70,000.00"));
               	System.out.println("TC9: Verify that only the maximum allowable amount of kes 70,000 can be posted per transaction|Sucess");
          	

	}

@Test (priority=10)
@Epic("PAYMENT")
@Features(value = { @Feature(value = "DSTV") })
@Step ("Check if Customer can confirm and pay for DSTV on ussd")
@Description("Check if Customer can confirm and pay for DSTV on ussd")
@Severity(SeverityLevel.BLOCKER)
public void Check_if_Customer_can_confirm_and_pay_for_DSTV_on_ussd() throws InterruptedException, IOException
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
          	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3";
          	driver.get(USSD_URL2);      	         	
        
          	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1";
          	driver.get(USSD_URL3);
          	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1";
          	driver.get(USSD_URL4);
          	assertTrue(driver.getPageSource().contains("DSTV"));
          	String USSD_URL5= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*1";
          	driver.get(USSD_URL5);
          	String USSD_URL6= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*1*2";
          	driver.get(USSD_URL6);
          	String USSD_URL7= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*1*2*4122276313";
          	driver.get(USSD_URL7);
          	String USSD_URL8= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*1*2*4122276313*1";
          	driver.get(USSD_URL8);
          	assertTrue(driver.getPageSource().contains("CON Enter amount"));
          	String USSD_URL9= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*1*2*4122276313*1*200";
          	driver.get(USSD_URL9);
          	assertTrue(driver.getPageSource().contains("CON Confirm Charges: Bank Charges"));
          	String USSD_URL10= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*1*2*4122276313*1*200*1";
          	driver.get(USSD_URL10);
          	Thread.sleep(3000); 
          	assertTrue(driver.getPageSource().contains("Your DSTV bill payment for account"));
               	System.out.println("TC10: Check if Customer can confirm and pay for DSTV on ussd|Sucess");
          	

	}
//**********************************************************************************************************************************
@Test(priority = 10)
@Epic("PAYMENT")
@Features(value = { @Feature(value = "DSTV") })
@Step ("DSTV")
@Description ("DSTV")
@Severity(SeverityLevel.TRIVIAL)
public void END_DSTV_Test_cases() 
	{	
	
	System.out.println("************************END OF DSTV TESTCASES***********************************");

	}
//**********************************************************************************************************************************

@Test(priority = 11)
@Epic("PAYMENT")
@Features(value = { @Feature(value = "GOTV") })
@Step ("GOTV Test cases")
@Description ("GOTV Test cases")
@Severity(SeverityLevel.TRIVIAL)
public void GOTV_Test_cases() 
	{	
	
	System.out.println("*********************** GOTV TESTCASES***********************************");

	}
//**********************************************************************************************************************************

@Test (priority=12)
@Epic("PAYMENT")
@Features(value = { @Feature(value = "GOTV") })
@Step ("Verify that a customer can select GOTV menu on USSD")
@Description ("Verify that a customer can select GOTV menu on USSD")
@Severity(SeverityLevel.BLOCKER)
public void Verify_that_a_customer_can_select_GOTV_menu_on_USSD() throws InterruptedException, IOException
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
          	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3";
          	driver.get(USSD_URL2);           	         	
          	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1";
          	driver.get(USSD_URL3);        	         	
          	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1";
          	driver.get(USSD_URL4);           	         	
          	//assertTrue(driver.getPageSource().contains("GOTV"));
          	System.out.println("TC1: Check if Customer can acess payment menu on ussd|Sucess");
          	

	}
//**********************************************************************************************************************************
@Test (priority=13)
@Epic("PAYMENT")
@Features(value = { @Feature(value = "GOTV") })
@Step ("Verify that a customer can querry bills for GOTV on USSD")
@Description ("Verify that a customer can querry bills for GOTV on USSD")
@Severity(SeverityLevel.BLOCKER)
public void Verify_that_a_customer_can_querry_bills_for_GOTV_on_USSD() throws InterruptedException, IOException
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
          	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3";
          	driver.get(USSD_URL2);           	         	
          	assertTrue(driver.getPageSource().contains("CON Payments"));
          	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1";
          	driver.get(USSD_URL3);        	         	
          	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1";
          	driver.get(USSD_URL4);           	         	
          	assertTrue(driver.getPageSource().contains("GOTV"));
          	String USSD_URL5= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1";
          	driver.get(USSD_URL5);           	         	
          	assertTrue(driver.getPageSource().contains("Query Bill"));
          	System.out.println("TC2: Verify that a customer can querry bills for GOTV on USSD |Sucess");
          	

	}
//**********************************************************************************************************************************
@Test (priority=14)
@Epic("PAYMENT")
@Features(value = { @Feature(value = "GOTV") })
@Step ("Verify that a customer can enter and search for GOTV number")
@Description ("Verify that a customer can enter and search for GOTV number")
@Severity(SeverityLevel.BLOCKER)
public void Verify_that_a_customer_can_enter_and_search_for_GOTV_number() throws InterruptedException, IOException
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
          	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3";
          	driver.get(USSD_URL2);           	         	
          	assertTrue(driver.getPageSource().contains("CON Payments"));
          	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1";
          	driver.get(USSD_URL3);        	         	
          	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1";
          	driver.get(USSD_URL4);           	         	
          	assertTrue(driver.getPageSource().contains("GOTV"));
          	String USSD_URL5= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*2";
          	driver.get(USSD_URL5);           	         	
          	String USSD_URL6= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*2*1";
          	driver.get(USSD_URL6);
          	assertTrue(driver.getPageSource().contains("CON Please Enter GOTV Account Number"));        
          	System.out.println("TC3: Verify that a customer can enter and search for GOTV number |Sucess");
          	

	}
//**********************************************************************************************************************************

@Test (priority=15)
@Epic("PAYMENT")
@Features(value = { @Feature(value = "GOTV") })
@Step ("verify whether a customer can SELEC account number for payment on USSD")
@Description ("verify whether a customer can SELEC account number for payment on USSD")
@Severity(SeverityLevel.BLOCKER)
public void verify_whether_a_customer_can_SELECT_account_number_for_payment_on_USSD() throws InterruptedException, IOException
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
          	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3";
          	driver.get(USSD_URL2);           	         	
          	assertTrue(driver.getPageSource().contains("CON Payments"));
          	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1";
          	driver.get(USSD_URL3);        	         	
          	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1";
          	driver.get(USSD_URL4);         	         	
          	String USSD_URL5= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*2";
          	driver.get(USSD_URL5);           	         	
          	String USSD_URL6= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*2*2";
          	driver.get(USSD_URL6);
          	String USSD_URL7= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*2*2*272017827574";
          	driver.get(USSD_URL7);
          	assertTrue(driver.getPageSource().contains("CON Select Account"));
          	        
          	System.out.println("TC4: verify whether a customer can SELEC account number for payment on USSD |Sucess");
          	

	}
//**********************************************************************************************************************************
@Test (priority=16)
@Epic("PAYMENT")
@Features(value = { @Feature(value = "GOTV") })
@Step ("verify whether a customer can enter amount when  paying for gotv on USSD")
@Description ("verify whether a customer can enter amount when  paying for gotv on USSD")
@Severity(SeverityLevel.BLOCKER)
public void verify_whether_a_customer_can_enter_amount_when_paying_for_gotv_onUSSD() throws InterruptedException, IOException
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
          	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3";
          	driver.get(USSD_URL2);           	         	
          	assertTrue(driver.getPageSource().contains("CON Payments"));
          	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1";
          	driver.get(USSD_URL3);        	         	
          	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1";
          	driver.get(USSD_URL4);         	         	
          	String USSD_URL5= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*2";
          	driver.get(USSD_URL5);           	         	
          	String USSD_URL6= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*2*2";
          	driver.get(USSD_URL6);
          	String USSD_URL7= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*2*2*272017827574";
          	driver.get(USSD_URL7);
          	assertTrue(driver.getPageSource().contains("CON Select Account"));
          	String USSD_URL8= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*2*2*272017827574*1";
          	driver.get(USSD_URL8);
          	assertTrue(driver.getPageSource().contains("CON Enter amount"));
          	        
          	System.out.println("TC5: verify whether a customer can enter amount when  paying for gotv on USSD |Sucess");
          	

	}
//**********************************************************************************************************************************
@Test (priority=17)
@Epic("PAYMENT")
@Features(value = { @Feature(value = "GOTV") })
@Step ("Verify a customer can enter amount and confirm bank charges for paying for GOTV on USSD")
@Description ("Verify a customer can enter amount and confirm bank charges for paying for GOTV on USSD")
@Severity(SeverityLevel.BLOCKER)
public void Verify_a_customer_can_enter_amount_and_confirm_bank_charges_WHEN_paying_for_GOTV_on_USSD() throws InterruptedException, IOException
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
        	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3";
        	driver.get(USSD_URL2);           	         	
        	assertTrue(driver.getPageSource().contains("CON Payments"));
        	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1";
        	driver.get(USSD_URL3);        	         	
        	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1";
        	driver.get(USSD_URL4);         	         	
        	String USSD_URL5= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*2";
        	driver.get(USSD_URL5);           	         	
        	String USSD_URL6= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*2*2";
        	driver.get(USSD_URL6);
        	String USSD_URL7= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*2*2*272017827574";
        	driver.get(USSD_URL7);
        	assertTrue(driver.getPageSource().contains("CON Select Account"));
        	String USSD_URL8= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*2*2*272017827574*1";
        	driver.get(USSD_URL8);
        	assertTrue(driver.getPageSource().contains("CON Enter amount"));
        	String USSD_URL9= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*2*2*272017827574*1*100";
        	driver.get(USSD_URL9);
        	assertTrue(driver.getPageSource().contains("CON Confirm Charges: Bank Charges 50.00 Excise Duty 10.00 Debit Amount 160.00"));
        	System.out.println("TC6: Verify a customer can enter amount and confirm bank charges for paying for GOTV on USSD |Sucess");
        	

	}
//**********************************************************************************************************************************
@Test (priority=18)
@Epic("PAYMENT")
@Features(value = { @Feature(value = "GOTV") })
@Step ("Verify a customer can confirm and pay for GOTV on USSD")
@Description ("Verify a customer can confirm and pay for GOTV on USSD")
@Severity(SeverityLevel.BLOCKER)
public void Verify_a_customer_can_confirm_and_pay_for_GOTV_on_USSD() throws InterruptedException, IOException
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
        	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3";
        	driver.get(USSD_URL2);           	         	
        	assertTrue(driver.getPageSource().contains("CON Payments"));
        	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1";
        	driver.get(USSD_URL3);        	         	
        	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1";
        	driver.get(USSD_URL4);         	         	
        	String USSD_URL5= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*2";
        	driver.get(USSD_URL5);           	         	
        	String USSD_URL6= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*2*2";
        	driver.get(USSD_URL6);
        	String USSD_URL7= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*2*2*272017827574";
        	driver.get(USSD_URL7);
        	assertTrue(driver.getPageSource().contains("CON Select Account"));
        	String USSD_URL8= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*2*2*272017827574*1";
        	driver.get(USSD_URL8);
        	assertTrue(driver.getPageSource().contains("CON Enter amount"));
        	String USSD_URL9= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*2*2*272017827574*1*100";
        	driver.get(USSD_URL9);
        	assertTrue(driver.getPageSource().contains("CON Confirm Charges: Bank Charges 50.00 Excise Duty 10.00 Debit Amount 160.00"));
        	String USSD_URL10= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*2*2*272017827574*1*100*1";
        	driver.get(USSD_URL10);
        	Thread.sleep(3000);
        	assertTrue(driver.getPageSource().contains("CON Your GOTV bill payment for account"));
        	System.out.println("TC7: Verify a customer can confirm and pay for GOTV on USSD |Sucess");
        	

	}
//**********************************************************************************************************************************
@Test(priority = 19)
@Epic("PAYMENT")
@Features(value = { @Feature(value = "GOTV") })
@Step ("END OF GOTV_Test_cases")
@Description ("END OF GOTV_Test_cases")
@Severity(SeverityLevel.TRIVIAL)
public void End_GOTV_Test_cases() 
	{	
	
	System.out.println("***********************END OF GOTV TESTCASES***********************************");

	}
//**********************************************************************************************************************************
@Test(priority = 20)
@Epic("PAYMENT")
@Features(value = { @Feature(value = "Startimes") })
@Step ("Startimes Test cases")
@Description ("Startimes Test cases")
@Severity(SeverityLevel.TRIVIAL)
public void Startimes_Test_cases() 
	{	
	
	System.out.println("*********************** Startimes TESTCASES***********************************");

	}
//**********************************************************************************************************************************
@Test (priority=21)
@Epic("PAYMENT")
@Features(value = { @Feature(value = "Startimes") })
@Step ("Verify a customer can select startime menu on the list on ussd")
@Description ("Verify a customer can select startime menu on the list on ussd")
@Severity(SeverityLevel.BLOCKER)
public void Verify_a_customer_can_select_startime_menu_on_the_list_on_ussd() throws InterruptedException, IOException
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
        	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3";
        	driver.get(USSD_URL2);           	         	
        	assertTrue(driver.getPageSource().contains("CON Payments"));
        	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1";
        	driver.get(USSD_URL3);      	         	
        	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1";
        	driver.get(USSD_URL4);           	         	
        	assertTrue(driver.getPageSource().contains("Startimes"));
        	System.out.println("TC1: Verify a customer can select startime menu on the list on ussd |Sucess");
	}
//**********************************************************************************************************************************
@Test (priority=22)
@Epic("PAYMENT")
@Features(value = { @Feature(value = "Startimes") })
@Step ("Verify a customer can query for startimes number ")
@Description ("Verify a customer can query for startimes number ")
@Severity(SeverityLevel.BLOCKER)
public void Verify_a_customer_can_query_for_startimes_number() throws InterruptedException, IOException
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
        	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3";
        	driver.get(USSD_URL2);           	         	
        	assertTrue(driver.getPageSource().contains("CON Payments"));
        	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1";
        	driver.get(USSD_URL3);      	         	
        	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1";
        	driver.get(USSD_URL4);           	         	
        	assertTrue(driver.getPageSource().contains("Startimes"));
        	String USSD_URL5= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*3";
        	driver.get(USSD_URL5);           	         	
        	assertTrue(driver.getPageSource().contains("Query Bill"));
        	System.out.println("TC2: Verify a customer can query for startimes number  |Sucess");
	}
//**********************************************************************************************************************************
@Test (priority=23)
@Epic("PAYMENT")
@Features(value = { @Feature(value = "Startimes") })
@Step ("Verify a customer can access paybill menu for starimes")
@Description ("Verify a customer can access paybill menu for starimes")
@Severity(SeverityLevel.BLOCKER)
public void Verify_a_customer_can_access_paybill_menu_for_starimes () throws InterruptedException, IOException
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
        	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3";
        	driver.get(USSD_URL2);           	         	
        	
        	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1";
        	driver.get(USSD_URL3);      	         	
        	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1";
        	driver.get(USSD_URL4);           	         	
        	
        	String USSD_URL5= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*3";
        	driver.get(USSD_URL5);           	         	
        	assertTrue(driver.getPageSource().contains("Pay Bill"));
        	System.out.println("TC3: Verify a customer can access paybill menu for starimes  |Sucess");
	}
//**********************************************************************************************************************************
@Test (priority=23)
@Epic("PAYMENT")
@Features(value = { @Feature(value = "Startimes") })
@Step ("Verify a customer can enter the account number for startimes")
@Description ("Verify a customer can enter the account number for startimes")
@Severity(SeverityLevel.BLOCKER)
public void Verify_a_customer_can_enter_the_account_number_for_startimes () throws InterruptedException, IOException
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
        	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3";
        	driver.get(USSD_URL2);           	         	
        	
        	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1";
        	driver.get(USSD_URL3);      	         	
        	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1";
        	driver.get(USSD_URL4);           	         	
        	
        	String USSD_URL5= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*3";
        	driver.get(USSD_URL5);           	         	
        	
        	String USSD_URL6= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*3*2";
        	driver.get(USSD_URL6);           	         	
        	assertTrue(driver.getPageSource().contains("CON Please Enter STARTIMES Account Number You wish to pay for"));
        	System.out.println("TC4: Verify a customer can enter the account number for startimes |Sucess");
	}
//**********************************************************************************************************************************
@Test (priority=23)
@Epic("PAYMENT")
@Features(value = { @Feature(value = "Startimes") })
@Step ("Verify a customer can select account number to use for paying startimes on ussd")
@Description ("Verify a customer can select account number to use for paying startimes on ussd")
@Severity(SeverityLevel.BLOCKER)
public void Verify_a_customer_can_select_account_number_to_use_for_paying_startimes_on_ussd () throws InterruptedException, IOException
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
        	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3";
        	driver.get(USSD_URL2);           	         	
        
        	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1";
        	driver.get(USSD_URL3);      	         	
        	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1";
        	driver.get(USSD_URL4);           	         	
        	
        	String USSD_URL5= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*3";
        	driver.get(USSD_URL5);           	         	
        	
        	String USSD_URL6= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*3*2";
        	driver.get(USSD_URL6);           	         	
        	assertTrue(driver.getPageSource().contains("CON Please Enter STARTIMES Account Number You wish to pay for"));
        	String USSD_URL7= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*3*2*02101931678";
        	driver.get(USSD_URL7);           	         	
        	assertTrue(driver.getPageSource().contains("Select Account"));
        	System.out.println("TC5: Verify a customer can select account number to use for paying startimes on ussd |Sucess");
	}
//**********************************************************************************************************************************
@Test (priority=24)
@Epic("PAYMENT")
@Features(value = { @Feature(value = "Startimes") })
@Step ("Verify a customer  can enter amount he/she wishes to pay for GoTV")
@Description ("Verify a customer  can enter amount he/she wishes to pay for GoTV")
@Severity(SeverityLevel.BLOCKER)
public void Verify_a_customer__can_enter_amount_he_she_wishes_to_pay_for_GoTV() throws InterruptedException, IOException
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
        	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3";
        	driver.get(USSD_URL2);           	         	
        	assertTrue(driver.getPageSource().contains("CON Payments"));
        	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1";
        	driver.get(USSD_URL3);      	         	
        	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1";
        	driver.get(USSD_URL4);           	         	
        	
        	String USSD_URL5= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*3";
        	driver.get(USSD_URL5);           	         	
        	
        	String USSD_URL6= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*3*2";
        	driver.get(USSD_URL6);           	         	
        	assertTrue(driver.getPageSource().contains("CON Please Enter STARTIMES Account Number You wish to pay for"));
        	String USSD_URL7= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*3*2*02101931678";
        	driver.get(USSD_URL7);           	         	
        	
        	String USSD_URL8= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*3*2*02101931678*1";
        	driver.get(USSD_URL8);           	         	
        	assertTrue(driver.getPageSource().contains("CON Enter amount"));
        	System.out.println("TC6: Verify a customer  can enter amount he/she wishes to pay for GoTV |Sucess");
	}
//**********************************************************************************************************************************
@Test (priority=24)
@Epic("PAYMENT")
@Features(value = { @Feature(value = "Startimes") })
@Step ("Verify error message is displayed when a customer pay less than 50 shillings for GOTV")
@Description ("Verify error message is displayed when a customer pay less than 50 shillings for GOTV")
@Severity(SeverityLevel.BLOCKER)
public void Verify_error_message_is_displayed_when_a_customer_pay_less_than_50_shillings_for_GOTV() throws InterruptedException, IOException
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
        	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3";
        	driver.get(USSD_URL2);           	         	
        	assertTrue(driver.getPageSource().contains("CON Payments"));
        	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1";
        	driver.get(USSD_URL3);      	         	
        	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1";
        	driver.get(USSD_URL4);           	         	
        	
        	String USSD_URL5= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*3";
        	driver.get(USSD_URL5);           	         	
        	assertTrue(driver.getPageSource().contains("Pay Bill"));
        	String USSD_URL6= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*3*2";
        	driver.get(USSD_URL6);           	         	
        	assertTrue(driver.getPageSource().contains("CON Please Enter STARTIMES Account Number You wish to pay for"));
        	String USSD_URL7= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*3*2*02101931678";
        	driver.get(USSD_URL7);           	         	
        	
        	String USSD_URL8= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*3*2*02101931678*1";
        	driver.get(USSD_URL8);           	         	
        	assertTrue(driver.getPageSource().contains("CON Enter amount"));
        	String USSD_URL9= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*3*2*02101931678*1*20";
        	driver.get(USSD_URL9);           	         	
        	assertTrue(driver.getPageSource().contains("CON Invalid Limit 2 trials Remaining: Enter Amount Between 50.00 and 70,000.00"));
        	System.out.println("TC7: Verify error message is displayed when a customer pay less than 50 shillings for GOTV |Sucess");
	}
//**********************************************************************************************************************************
@Test (priority=25)
@Epic("PAYMENT")
@Features(value = { @Feature(value = "Startimes") })
@Step ("Verify a customer can confirm the  bank charges when paying for GOTV")
@Description ("Verify a customer can confirm the  bank charges when paying for GOTV")
@Severity(SeverityLevel.BLOCKER)
public void Verify_a_customer_can_confirm_the__bank_charges_when_paying_for_GOTV() throws InterruptedException, IOException
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
        	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3";
        	driver.get(USSD_URL2);           	         	
        	assertTrue(driver.getPageSource().contains("CON Payments"));
        	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1";
        	driver.get(USSD_URL3);      	         	
        	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1";
        	driver.get(USSD_URL4);           	         	
        	
        	String USSD_URL5= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*3";
        	driver.get(USSD_URL5);           	         	
        
        	String USSD_URL6= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*3*2";
        	driver.get(USSD_URL6);           	         	
        	assertTrue(driver.getPageSource().contains("CON Please Enter STARTIMES Account Number You wish to pay for"));
        	String USSD_URL7= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*3*2*02101931678";
        	driver.get(USSD_URL7);           	         	
        	assertTrue(driver.getPageSource().contains("Select Account"));
        	String USSD_URL8= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*3*2*02101931678*1";
        	driver.get(USSD_URL8);           	         	
        	assertTrue(driver.getPageSource().contains("CON Enter amount"));
        	String USSD_URL9= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*3*2*02101931678*1*150";
        	driver.get(USSD_URL9); 
        	Thread.sleep(4000);
        	assertTrue(driver.getPageSource().contains("CON Confirm Charges"));
        	System.out.println("TC8: Verify a customer can confirm the  bank charges when paying for GOTV |Sucess");
	}
//**********************************************************************************************************************************
@Test (priority=25)
@Epic("PAYMENT")
@Features(value = { @Feature(value = "Startimes") })
@Step ("Verify a customer can sucessfully pay for Gotv ")
@Description ("Verify a customer can sucessfully pay for Gotv ")
@Severity(SeverityLevel.BLOCKER)
public void Verify_a_customer_can_sucessfully_pay_for_Gotv() throws InterruptedException, IOException
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
        	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3";
        	driver.get(USSD_URL2);           	         	
        	assertTrue(driver.getPageSource().contains("CON Payments"));
        	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1";
        	driver.get(USSD_URL3);      	         	
        	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1";
        	driver.get(USSD_URL4);           	         	
        	assertTrue(driver.getPageSource().contains("Startimes"));
        	String USSD_URL5= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*3";
        	driver.get(USSD_URL5);           	         	
        	assertTrue(driver.getPageSource().contains("Pay Bill"));
        	String USSD_URL6= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*3*2";
        	driver.get(USSD_URL6);           	         	
        	assertTrue(driver.getPageSource().contains("CON Please Enter STARTIMES Account Number You wish to pay for"));
        	String USSD_URL7= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*3*2*02101931678";
        	driver.get(USSD_URL7);           	         	
        	assertTrue(driver.getPageSource().contains("Select Account"));
        	String USSD_URL8= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*3*2*02101931678*1";
        	driver.get(USSD_URL8);           	         	
        	assertTrue(driver.getPageSource().contains("CON Enter amount"));
        	String USSD_URL9= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*3*2*02101931678*1*150";
        	driver.get(USSD_URL9);         	         	
        	String USSD_URL10= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*1*3*2*02101931678*1*150*1";
        	driver.get(USSD_URL10);           	         	
        	assertTrue(driver.getPageSource().contains("CON Your STARTIMES bill payment for account 02101931678"));
        	System.out.println("TC9: Verify a customer can sucessfully pay for Gotv  |Sucess");
	}
//**********************************************************************************************************************************
@Test(priority = 26)
@Epic("PAYMENT")
@Features(value = { @Feature(value = "Startimes") })
@Step ("Startimes Test cases")
@Description ("Startimes Test cases")
@Severity(SeverityLevel.TRIVIAL)
public void End_Startimes_Test_cases() 
	{	
	
	System.out.println("*********************** End of Startimes TESTCASES***********************************");

	}
//**********************************************************************************************************************************
@Test(priority = 27)
@Epic("PAYMENT")
@Features(value = { @Feature(value = "Electricity(prepaid)") })
@Step ("Electricity(prepaid)")
@Description ("Electricity(prepaid)")
@Severity(SeverityLevel.TRIVIAL)
public void Electricity_prepaid_Test_cases() 
	{	
	
	System.out.println("***********************Electricity(prepaid) TESTCASES***********************************");

	}
//**********************************************************************************************************************************
@Test (priority=28)
@Epic("PAYMENT")
@Features(value = { @Feature(value = "Electricity(prepaid)") })
@Step ("Verify a user can access Electicity menu")
@Description ("Verify a user can access Electicity menu")
@Severity(SeverityLevel.BLOCKER)
public void Verify_a_user_can_access_Electicity_menu() throws InterruptedException, IOException
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
        	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3";
        	driver.get(USSD_URL2);           	         	
        	assertTrue(driver.getPageSource().contains("CON Payments"));
        	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1";
        	driver.get(USSD_URL3);           	         	
        	assertTrue(driver.getPageSource().contains("Electricity"));
        	
        	System.out.println("TC1: Verify a user can access Electicity menu  |Sucess");
	}
//**********************************************************************************************************************************
@Test (priority=29)
@Epic("PAYMENT")
@Features(value = { @Feature(value = "Electricity(prepaid)") })
@Step ("Verify a user can access electricity prepaid menu")
@Description ("Verify a user can access electricity prepaid menu")
@Severity(SeverityLevel.BLOCKER)
public void Verify_a_user_can_access_electricity_prepaid_menu() throws InterruptedException, IOException
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
        	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3";
        	driver.get(USSD_URL2);           	         	
        	assertTrue(driver.getPageSource().contains("CON Payments"));
        	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1";
        	driver.get(USSD_URL3);           	         	
        	assertTrue(driver.getPageSource().contains("Electricity"));
        	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*2";
        	driver.get(USSD_URL4);
               	Thread.sleep(3000);
        	assertTrue(driver.getPageSource().contains("Prepaid"));
        	System.out.println("TC2: Verify a user can access electricity prepaid menu  |Sucess");
	}
//**********************************************************************************************************************************
@Test (priority=30)
@Epic("PAYMENT")
@Features(value = { @Feature(value = "Electricity(prepaid)") })
@Step ("Verify a user can enter prepaid account number to pay for electricity")
@Description ("Verify a user can enter prepaid account number to pay for electricity")
@Severity(SeverityLevel.BLOCKER)
public void Verify_a_user_can_enter_prepaid_account_number_to_pay_for_electricity() throws InterruptedException, IOException
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
        	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3";
        	driver.get(USSD_URL2);           	         	
        	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1";
        	driver.get(USSD_URL3);         	         	
        	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*2";
        	driver.get(USSD_URL4);
        	String USSD_URL5= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*2*1";
        	driver.get(USSD_URL5);
        	String USSD_URL6= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*2*1*2";
        	driver.get(USSD_URL6);
        	assertTrue(driver.getPageSource().contains("Please Enter Prepaid Account Number You wish to pay for"));
               	System.out.println("TC3: Verify a user can enter prepaid account number to pay for electricity |Sucess");
	}
//**********************************************************************************************************************************
@Test (priority=31)
@Epic("PAYMENT")
@Features(value = { @Feature(value = "Electricity(prepaid)") })
@Step (" Verify a user can select account number to make payment")
@Description (" Verify a user can select account number to make payment")
@Severity(SeverityLevel.BLOCKER)
public void Verify_a_user_can_select_account_number_to_make_payment() throws InterruptedException, IOException
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
        	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3";
        	driver.get(USSD_URL2);           	         	
        	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1";
        	driver.get(USSD_URL3);         	         	
        	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*2";
        	driver.get(USSD_URL4);
        	String USSD_URL5= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*2*1";
        	driver.get(USSD_URL5);
        	String USSD_URL6= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*2*1*2";
        	driver.get(USSD_URL6);
        	String USSD_URL7= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*2*1*2*04216519951";
        	driver.get(USSD_URL7);
        	assertTrue(driver.getPageSource().contains("CON Select Account"));
               	System.out.println("TC4: Verify a user can select account number to make payment|Sucess");
	}
//**********************************************************************************************************************************
@Test(priority = 32)
@Epic("PAYMENT")
@Features(value = { @Feature(value = "Electricity(prepaid)") })
@Step ("Electricity(prepaid)")
@Description ("Electricity(prepaid)")
@Severity(SeverityLevel.TRIVIAL)
public void End_Electricity_prepaid_Test_cases() 
	{	
	
	System.out.println("***********************End of Electricity(prepaid) TESTCASES***********************************");

	}
//**********************************************************************************************************************************
@Test(priority = 33)
@Epic("PAYMENT")
@Features(value = { @Feature(value = "Electricity(postpaid)") })
@Step ("Electricity(postpaid)")
@Description ("Electricity(postpaid)")
@Severity(SeverityLevel.TRIVIAL)
public void Electricity_postpaid_Test_cases() 
	{	
	
	System.out.println("***********************Electricity(postpaid) TESTCASES***********************************");

	}
//**********************************************************************************************************************************

@Test (priority=34)
@Epic("PAYMENT")
@Features(value = { @Feature(value = "Electricity(postpaid)") })
@Step ("Verify a user can access Electicity menu for postpaid")
@Description ("Verify a user can access Electicity menu for postpaid")
@Severity(SeverityLevel.BLOCKER)
public void Verify_a_user_can_access_Electicity_menu_for_postpaid() throws InterruptedException, IOException
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
        	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3";
        	driver.get(USSD_URL2);           	         	
        	assertTrue(driver.getPageSource().contains("CON Payments"));
        	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1";
        	driver.get(USSD_URL3);           	         	
        	assertTrue(driver.getPageSource().contains("Electricity"));
        	
        	System.out.println("TC1: Verify a user can access Electicity menu for postpaid  |Sucess");
	}
//**********************************************************************************************************************************
@Test (priority=35)
@Epic("PAYMENT")
@Features(value = { @Feature(value = "Electricity(postpaid)") })
@Step ("Verify a user can access electricity postpaid menu")
@Description ("Verify a user can access electricity postpaid menu")
@Severity(SeverityLevel.BLOCKER)
public void Verify_a_user_can_access_electricity_postpaid_menu() throws InterruptedException, IOException
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
        	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3";
        	driver.get(USSD_URL2);           	         	
        	assertTrue(driver.getPageSource().contains("CON Payments"));
        	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1";
        	driver.get(USSD_URL3);           	         	
        	assertTrue(driver.getPageSource().contains("Electricity"));
        	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*2";
        	driver.get(USSD_URL4);
            	Thread.sleep(3000);
        	assertTrue(driver.getPageSource().contains("Postpaid"));
                System.out.println("TC2: Verify a user can access electricity postpaid menu  |Sucess");
	}
//**********************************************************************************************************************************
@Test (priority=36)
@Epic("PAYMENT")
@Features(value = { @Feature(value = "Electricity(postpaid)") })
@Step ("Verify a user can enter prepaid account number to pay for electricity for postpaid")
@Description ("Verify a user can enter prepaid account number to pay for electricity for postpaid")
@Severity(SeverityLevel.BLOCKER)
public void Verify_a_user_can_enter_postpaid_account_number_to_pay_for_electricity() throws InterruptedException, IOException
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
        	assertTrue(driver.getPageSource().contains("Enter Postpaid Account Number You wish to pay for"));
               	System.out.println("TC3: Verify a user can enter prepaid account number to pay for electricity for postpaid |Sucess");
	}
//**********************************************************************************************************************************
@Test (priority=37)
@Epic("PAYMENT")
@Features(value = { @Feature(value = "Electricity(postpaid)") })
@Step (" Verify a user can select account number to make payment for postpaid")
@Description (" Verify a user can select account number to make payment for postpaid")
@Severity(SeverityLevel.BLOCKER)
public void Verify_a_user_can_select_account_number_to_make_payment_for_postpaid() throws InterruptedException, IOException
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
        	String USSD_URL7= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*2*2*2*15067697";
        	driver.get(USSD_URL7);
        	assertTrue(driver.getPageSource().contains("CON Select Account"));
               	System.out.println("TC4:  Verify a user can select account number to make payment for postpaid |Sucess");
	}
//**********************************************************************************************************************************
@Test(priority = 38)
@Epic("PAYMENT")
@Features(value = { @Feature(value = "Electricity(postpaid)") })
@Step ("Electricity(postpaid)")
@Description ("Electricity(postpaid)")
@Severity(SeverityLevel.TRIVIAL)
public void End_Electricity_postpaid_Test_cases() 
	{	
	
	System.out.println("***********************End of Electricity(postpaid) TESTCASES***********************************");

	}
//**********************************************************************************************************************************

@Test(priority = 39)
@Epic("PAYMENT")
@Features(value = { @Feature(value = "Nairobi Water") })
@Step ("Nairobi Water")
@Description ("Nairobi Water")
@Severity(SeverityLevel.TRIVIAL)
public void Nairobi_Water_Test_cases() 
	{	
	
	System.out.println("***********************Nairobi Water TESTCASES***********************************");

	}
//**********************************************************************************************************************************
@Test (priority=40)
@Epic("PAYMENT")
@Features(value = { @Feature(value = "Nairobi Water") })
@Step ("Verify a user can access water menu")
@Description ("Verify a user can access water menu")
@Severity(SeverityLevel.BLOCKER)
public void Verify_a_user_can_access_water_menu() throws InterruptedException, IOException
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
        	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3";
        	driver.get(USSD_URL2);           	         	
        	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1";
        	driver.get(USSD_URL3); 
        	assertTrue(driver.getPageSource().contains("Water"));
               	System.out.println("TC1:  Verify a user can access  water menu|Sucess");
	}
//95*1111*3*1*3
//**********************************************************************************************************************************
@Test (priority=41)
@Epic("PAYMENT")
@Features(value = { @Feature(value = "Nairobi Water") })
@Step ("Verify a user can access Nairobi water menu")
@Description ("Verify a user can access Nairobi water menu")
@Severity(SeverityLevel.BLOCKER)
public void Verify_a_user_can_access_Nairobi_water_menu() throws InterruptedException, IOException
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
        	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3";
        	driver.get(USSD_URL2);           	         	
        	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1";
        	driver.get(USSD_URL3); 
        	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*3";
        	driver.get(USSD_URL4);        
        	assertTrue(driver.getPageSource().contains("Nairobi Water"));
               	System.out.println("TC2:  Verify a user can access Nairobi water menu|Sucess");
	}
//**********************************************************************************************************************************
@Test (priority=42)
@Epic("PAYMENT")
@Features(value = { @Feature(value = "Nairobi Water") })
@Step ("Verify user can query Nairobi Water Bill using valid data")
@Description ("Verify user can query Nairobi Water Bill using valid data")
@Severity(SeverityLevel.BLOCKER)
public void Verify_user_can_query_Nairobi_Water_Bill_using_valid_data() throws InterruptedException, IOException
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
        	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3";
        	driver.get(USSD_URL2);           	         	
        	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1";
        	driver.get(USSD_URL3); 
        	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*3";
        	driver.get(USSD_URL4);
        	String USSD_URL5= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*3*1";
        	driver.get(USSD_URL5); 
        	assertTrue(driver.getPageSource().contains("Query Bill"));
               	System.out.println("TC3:  Verify_user_can_query_Nairobi_Water_Bill_using_valid_data |Sucess");
	}
//**********************************************************************************************************************************
@Test (priority=43)
@Epic("PAYMENT")
@Features(value = { @Feature(value = "Nairobi Water") })
@Step ("Verify a user can enter meter number to pay for nairobi water bills")
@Description ("Verify a user can enter meter number to pay for nairobi water bills")
@Severity(SeverityLevel.BLOCKER)
public void Verify_a_user_can_enter_meter_number_to_pay_for_nairobi_water_bills() throws InterruptedException, IOException
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
        	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3";
        	driver.get(USSD_URL2);           	         	
        	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1";
        	driver.get(USSD_URL3); 
        	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*3";
        	driver.get(USSD_URL4);
        	String USSD_URL5= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*3*1";
        	driver.get(USSD_URL5); 
        	String USSD_URL6= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*3*1*2";
        	driver.get(USSD_URL6);
        	assertTrue(driver.getPageSource().contains("CON Please Enter the Nairobi Water Account Number You wish to pay for"));
               	System.out.println("TC4:  Verify a user can enter meter number to pay for nairobi water bills |Sucess");
	}
//**********************************************************************************************************************************
@Test (priority=44)
@Epic("PAYMENT")
@Features(value = { @Feature(value = "Nairobi Water") })
@Step ("Verify a user can select account number to make payment")
@Description ("Verify a user can select account number to make payment")
@Severity(SeverityLevel.BLOCKER)
public void Verify_a_user_can_select_account_number_to_make_payment_for_nairobi_water() throws InterruptedException, IOException
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
        	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3";
        	driver.get(USSD_URL2);           	         	
        	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1";
        	driver.get(USSD_URL3); 
        	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*3";
        	driver.get(USSD_URL4);
        	String USSD_URL5= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*3*1";
        	driver.get(USSD_URL5); 
        	String USSD_URL6= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*3*1*2";
        	driver.get(USSD_URL6);
        	String USSD_URL7= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*3*1*2*1504455";
        	driver.get(USSD_URL7);
        	assertTrue(driver.getPageSource().contains("CON Select Account"));
               	System.out.println("TC5:  Verify a user can select account number to make payment |Sucess");
	}
//**********************************************************************************************************************************
@Test (priority=45)
@Epic("PAYMENT")
@Features(value = { @Feature(value = "Nairobi Water") })
@Step ("Verify a user can enter the amount to make payment")
@Description ("Verify a user can enter the amount to make payment")
@Severity(SeverityLevel.BLOCKER)
public void Verify_a_user_can_enter_the_amount_to_make_payment_for_Nairobi_water() throws InterruptedException, IOException
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
        	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3";
        	driver.get(USSD_URL2);           	         	
        	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1";
        	driver.get(USSD_URL3); 
        	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*3";
        	driver.get(USSD_URL4);
        	String USSD_URL5= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*3*1";
        	driver.get(USSD_URL5); 
        	String USSD_URL6= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*3*1*2";
        	driver.get(USSD_URL6);
        	String USSD_URL7= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*3*1*2*1504455";
        	driver.get(USSD_URL7);
        	String USSD_URL8= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*3*1*2*1504455*1";
        	driver.get(USSD_URL8);
        	assertTrue(driver.getPageSource().contains("CON Enter amount"));
               	System.out.println("TC6:  Verify a user can enter the amount to make payment |Sucess");
	}
//**********************************************************************************************************************************
//verify that only a minimum  allowable amount of kes 50.00 can be posted per transaction.In the event a customer inputs an amount less than the minimum allowable amount an error pops up
@Test (priority=46)
@Epic("PAYMENT")
@Features(value = { @Feature(value = "Nairobi Water") })
@Step ("verify that only a minimum  allowable amount of kes 50.00 can be posted per transaction.In the event a customer inputs an amount less than the minimum allowable amount an error pops up")
@Description ("verify that only a minimum  allowable amount of kes 50.00 can be posted per transaction.In the event a customer inputs an amount less than the minimum allowable amount an error pops up")
@Severity(SeverityLevel.BLOCKER)
public void verify_that_only_a_minimum__allowable_amount_of_kes_50_00_can_be_posted_per_transaction_In_the_event_a_customer_inputs_an_amount_less_than_the_minimum_allowable_amount_an_error_pops_up() throws InterruptedException, IOException
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
        	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3";
        	driver.get(USSD_URL2);           	         	
        	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1";
        	driver.get(USSD_URL3); 
        	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*3";
        	driver.get(USSD_URL4);
        	String USSD_URL5= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*3*1";
        	driver.get(USSD_URL5); 
        	String USSD_URL6= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*3*1*2";
        	driver.get(USSD_URL6);
        	String USSD_URL7= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*3*1*2*1504455";
        	driver.get(USSD_URL7);
        	String USSD_URL8= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*3*1*2*1504455*1";
        	driver.get(USSD_URL8);
        	String USSD_URL9= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*3*1*2*1504455*1*45";
        	driver.get(USSD_URL9);
        	assertTrue(driver.getPageSource().contains("CON Invalid Limit 2 trials Remaining: Enter Amount Between 50.00 and 100,000.00"));
               	System.out.println("TC7:  verify that only a minimum  allowable amount of kes 50.00 can be posted per transaction.In the event a customer inputs an amount less than the minimum allowable amount an error pops up |Sucess");
	}
//**********************************************************************************************************************************

@Test (priority=46)
@Epic("PAYMENT")
@Features(value = { @Feature(value = "Nairobi Water") })
@Step ("Verify a user can enter the amount and confirm bank charges before making payment")
@Description ("Verify a user can enter the amount and confirm bank charges before making payment")
@Severity(SeverityLevel.BLOCKER)
public void Verify_a_user_can_enter_the_amount_and_confirm_bank_charges_before_making_payment() throws InterruptedException, IOException
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
        	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3";
        	driver.get(USSD_URL2);           	         	
        	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1";
        	driver.get(USSD_URL3); 
        	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*3";
        	driver.get(USSD_URL4);
        	String USSD_URL5= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*3*1";
        	driver.get(USSD_URL5); 
        	String USSD_URL6= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*3*1*2";
        	driver.get(USSD_URL6);
        	String USSD_URL7= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*3*1*2*1504455";
        	driver.get(USSD_URL7);
        	String USSD_URL8= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*3*1*2*1504455*1";
        	driver.get(USSD_URL8);
        	String USSD_URL9= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*3*1*2*1504455*1*200";
        	driver.get(USSD_URL9);
        	assertTrue(driver.getPageSource().contains("CON Confirm Charges"));
               	System.out.println("TC7:  Verify a user can enter the amount and confirm bank charges before making payment |Sucess");
	}
//**********************************************************************************************************************************
//
@Test (priority=47)
@Epic("PAYMENT")
@Features(value = { @Feature(value = "Nairobi Water") })
@Step ("Verify user can pay Water-Nairobi water Bill using valid data")
@Description ("Verify user can pay Water-Nairobi water Bill using valid data")
@Severity(SeverityLevel.BLOCKER)
public void Verify_user_can_pay_Water_Nairobi_water_Bill_using_valid_data() throws InterruptedException, IOException
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
        	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3";
        	driver.get(USSD_URL2);           	         	
        	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1";
        	driver.get(USSD_URL3); 
        	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*3";
        	driver.get(USSD_URL4);
        	String USSD_URL5= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*3*1";
        	driver.get(USSD_URL5); 
        	String USSD_URL6= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*3*1*2";
        	driver.get(USSD_URL6);
        	String USSD_URL7= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*3*1*2*1504455";
        	driver.get(USSD_URL7);
        	String USSD_URL8= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*3*1*2*1504455*1";
        	driver.get(USSD_URL8);
        	String USSD_URL9= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*3*1*2*1504455*1*200";
        	driver.get(USSD_URL9);        	
        	String USSD_URL10= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*3*1*3*1*2*1504455*1*200*1";
        	driver.get(USSD_URL10);
        	assertTrue(driver.getPageSource().contains("CON Your Nairobi Water bill payment for account"));
               	System.out.println("TC8:  Verify user can pay Water-Nairobi water Bill using valid data |Sucess");
	}
//**********************************************************************************************************************************
@Test(priority = 48)
@Epic("PAYMENT")
@Features(value = { @Feature(value = "Nairobi Water") })
@Step ("Nairobi Water")
@Description ("Nairobi Water")
@Severity(SeverityLevel.TRIVIAL)
public void End_Nairobi_Water_Test_cases() 
	{	
	
	System.out.println("***********************End of Nairobi Water TESTCASES***********************************");

	}
//**********************************************************************************************************************************

}

