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


public class D_Fund_Transfer_Account_to_Coop_Account {
    
    String my_dir = System.getProperty("user.dir");
	WebDriver driver;
	JavascriptExecutor js; 
	XSSFWorkbook USSD;
	XSSFSheet sheet;
	XSSFCell cell;
	
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
	testng.setTestClasses(new Class[] {D_Fund_Transfer_Account_to_Coop_Account.class});
	testng.run();
  	}

@Test(priority = 0)
@Epic("USSD FUND TRANSFERS")
@Features(value = { @Feature(value = "TRANSFER TO COOP ACCOUNT") })
@Step ("TRANSFER TO COOP_ACCOUNT TESTCASES")
@Description ("TRANSFER TO COOP_ACCOUNT TESTCASES")
@Severity(SeverityLevel.TRIVIAL)
public void Starting_transfer_to_COOP_ACCOUNT_Test_cases() 
	{	
	
	System.out.println("************************TRANSFER TO COOP_ACCOUNT TESTCASES***********************************");

	}

@Test (priority=1)
@Epic("USSD FUND TRANSFERS")
@Features(value = { @Feature(value = "TRANSFER TO COOP ACCOUNT") })
@Step ("Verify_that customer can access send money menu to COOP ACCOUNT on USSD")
@Description ("Verify_that customer can access send money menu to COOP ACCOUNT on USSD")
@Severity(SeverityLevel.BLOCKER)
public void Verify_that_customer_can_access_send_money_to_COOP_ACCOUNT_menu_on_USSD() throws InterruptedException, IOException
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
          	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2";
          	driver.get(USSD_URL2); 
          	assertTrue(driver.getPageSource().contains("To Co-op Accounts")); 
          	System.out.println("TC1 Verify_that customer can access send money menu to COOP_ACCOUNT on USSD |Success:");
          	
}
@Test (priority=2)
@Epic("USSD FUND TRANSFERS")
@Features(value = { @Feature(value = "TRANSFER TO COOP ACCOUNT") })
@Step ("Verify_that customer can  selected To TRANSFER TO COOP ACCOUNT on USSD")
@Description ("Verify_that customer can  selected To TRANSFER TO COOP ACCOUNT on USSD")
@Severity(SeverityLevel.BLOCKER)
public void Verify_that_customer_can_selected_To_COO_aCCOUNT_on_USSD() throws InterruptedException, IOException
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
          	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2";
          	driver.get(USSD_URL2); 
          	assertTrue(driver.getPageSource().contains("CON Send Money To"));
          	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*2";
          	driver.get(USSD_URL3); 
          	assertTrue(driver.getPageSource().contains("My Accounts")); 
          	System.out.println("TC2 Verify_that customer can  selected To TRANSFER TO COOP ACCOUNT on USSD |Success:");
         	
}

@Test (priority=3)
@Epic("USSD FUND TRANSFERS")
@Features(value = { @Feature(value = "TRANSFER TO COOP ACCOUNT") })
@Step ("Verify that customer can selected To my number on USSD")
@Description ("Verify that customer can selected To my number on USSD")
@Severity(SeverityLevel.BLOCKER)
public void Verify_that_customer_can_selected_To_my_Account_on_USSD() throws InterruptedException, IOException
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
	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2";
	driver.get(USSD_URL2); 
	assertTrue(driver.getPageSource().contains("CON Send Money To"));
	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*2";
	driver.get(USSD_URL3); 
	assertTrue(driver.getPageSource().contains("My Accounts")); 
	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*2*1";
	driver.get(USSD_URL4); 
          	System.out.println("TC3 Verify that customer can selected To my number on USSD |Success:");
         	
}
@Test (priority=4)
@Epic("USSD FUND TRANSFERS")
@Features(value = { @Feature(value = "TRANSFER TO COOP ACCOUNT") })
@Step ("Verify that customer can selected To other number on USSD")
@Description ("Verify that customer can selected To other number on USSD")
@Severity(SeverityLevel.BLOCKER)
public void Verify_that_customer_can_selected_To_other_Accounts_on_USSD() throws InterruptedException, IOException
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
   	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2";
   	driver.get(USSD_URL2); 
   	assertTrue(driver.getPageSource().contains("CON Send Money To"));
   	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*2";
   	driver.get(USSD_URL3); 
   	assertTrue(driver.getPageSource().contains("My Accounts")); 
   	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*2*2";
   	driver.get(USSD_URL4); 
          	System.out.println("TC4 Verify that customer can selected To other number on USSD |Success:");
}

@Test (priority=5)
@Epic("USSD FUND TRANSFERS")
@Features(value = { @Feature(value = "TRANSFER TO COOP ACCOUNT") })
@Step ("Verify that customer can selected To coop bank Credit card on USSD")
@Description ("Verify that customer can selected To coop bank Credit card on USSD")
@Severity(SeverityLevel.BLOCKER)
public void Verify_that_customer_can_selected_To_Coop_Bank_Credit_Card_on_USSD() throws InterruptedException, IOException
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
   	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2";
   	driver.get(USSD_URL2); 
   	assertTrue(driver.getPageSource().contains("CON Send Money To"));
   	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*2";
   	driver.get(USSD_URL3); 
   	assertTrue(driver.getPageSource().contains("My Accounts")); 
   	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*2*3";
   	driver.get(USSD_URL4); 
          	System.out.println("TC5 Verify that customer can selected To coop bank Credit card on USSD |Success:");
}

@Test (priority=6)
@Epic("USSD FUND TRANSFERS")
@Features(value = { @Feature(value = "TRANSFER TO COOP ACCOUNT") })
@Description ("Verify that customer can select the account to send from on USSD")
@Step ("Verify that customer can select the account to send from on USSD")
@Severity(SeverityLevel.BLOCKER)
public void Verify_that_customer_can_select_the_account_to_send_from_on_USSD() throws InterruptedException, IOException
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
          	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2";
          	driver.get(USSD_URL2); 
          	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*2";
          	driver.get(USSD_URL3); 
          	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*2*2";
          	driver.get(USSD_URL4);
               	String USSD_URL5= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*2*2*1";
          	driver.get(USSD_URL5);
          	
          	System.out.println("TC6 Verify that customer can select the account to send from on USSD |Success:");
}

@Test (priority=7)
@Epic("USSD FUND TRANSFERS")
@Features(value = { @Feature(value = "TRANSFER TO COOP ACCOUNT") })
@Description ("Verify that customer can enter the amount to send on USSD")
@Step ("Verify that customer can enter the amount to send on USSD")
@Severity(SeverityLevel.BLOCKER)
public void Verify_that_customer_can_enter_the_amount_to_send_on_USSD() throws InterruptedException, IOException
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
          	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2";
          	driver.get(USSD_URL2); 
          	 String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*1";
          	driver.get(USSD_URL3);          	
          	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*1*1";
          	driver.get(USSD_URL4);
          	assertTrue(driver.getPageSource().contains("2. Other Mobile Numbers"));
          	String USSD_URL5= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*1*1*1";
          	driver.get(USSD_URL5);
          	String USSD_URL6= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*1*1*1*200";
          	driver.get(USSD_URL6);
          	System.out.println("TC7 Verify that customer can enter the amount to send on USSD |Success:");
}

@Test (priority=8)
@Epic("USSD FUND TRANSFERS")
@Features(value = { @Feature(value = "TRANSFER TO COOP ACCOUNT") })
@Step ("Verify that customer cannot transfer amount above  the maximum amount allowed")
@Description ("Verify that customer cannot transfer amount above  the maximum amount allowed")
@Severity(SeverityLevel.BLOCKER)
public void Verify_that_customer_cannot_transfer_amount_above_the_maximum_amount_allowed() throws InterruptedException, IOException
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
	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2";
	driver.get(USSD_URL2); 
	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*2";
	driver.get(USSD_URL3);          	
	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*2*2";
	driver.get(USSD_URL4);	
	String USSD_URL5= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*2*2*1";
	driver.get(USSD_URL5);
	String USSD_URL6= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*2*2*1*01105607566200";
	driver.get(USSD_URL6);
	String USSD_URL7= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*2*2*1*01105607566200*700000";
	driver.get(USSD_URL7);
        assertTrue(driver.getPageSource().contains("ON Invalid Limit 2 trials Remaining: Enter Amount Between 50.00 and 500,000.00"));
        System.out.println("TC8 Verify that customer cannot transfer amount above  the maximum amount allowed |Success:");
         	
}
@Test (priority=9)
@Epic("USSD FUND TRANSFERS")
@Features(value = { @Feature(value = "TRANSFER TO COOP ACCOUNT") })
@Step ("Verify that customer cannot transfer amount below the minimum amount allowed")
@Description ("Verify that customer cannot transfer amount below the minimum amount allowed")
@Severity(SeverityLevel.BLOCKER)
public void Verify_that_customer_cannot_transfer_amount_below_the_maximum_amount_allowed() throws InterruptedException, IOException
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
	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2";
	driver.get(USSD_URL2); 
	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*2";
	driver.get(USSD_URL3);          	
	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*2*2";
	driver.get(USSD_URL4);	
	String USSD_URL5= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*2*2*1";
	driver.get(USSD_URL5);
	String USSD_URL6= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*2*2*1*01105607566200";
	driver.get(USSD_URL6);
	String USSD_URL7= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*2*2*1*01105607566200*49";
	driver.get(USSD_URL7);
	assertTrue(driver.getPageSource().contains("CON Invalid Limit 2 trials Remaining: Enter Amount Between 50.00 and 500,000.00"));
        System.out.println("TC9 Verify that customer cannot transfer amount below the minimum amount allowed |Success:");
	
         	
}

@Test (priority=10)
@Epic("USSD FUND TRANSFERS")
@Features(value = { @Feature(value = "TRANSFER TO COOP ACCOUNT") })
@Step ("Verify that the System generates an error mesasge where the customer has provided an Invalid amount as the Transfer Amount")
@Description ("Verify that the System generates an error mesasge where the customer has provided an Invalid amount as the Transfer Amount")
@Severity(SeverityLevel.BLOCKER)
public void Verify_that_the_System_generates_an_error_mesasge_where_the_customer_has_provided_an_Invalid_amount_as_the_Transfer_Amount() throws InterruptedException, IOException
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
	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2";
	driver.get(USSD_URL2); 
	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*2";
	driver.get(USSD_URL3);          	
	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*2*2";
	driver.get(USSD_URL4);	
	String USSD_URL5= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*2*2*1";
	driver.get(USSD_URL5);
	String USSD_URL6= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*2*2*1*01105607566200";
	driver.get(USSD_URL6);
	String USSD_URL7= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*2*2*1*01105607566200*yt5rrr";
	driver.get(USSD_URL7);
	assertTrue(driver.getPageSource().contains("CON Invalid Input 2 trials Remaining: Enter Amount"));
    System.out.println("TC10 Verify that the System generates an error mesasge where the customer has provided an Invalid amount as the Transfer Amount |Success:");
 	
         	
}

@Test (priority=11)
@Epic("USSD FUND TRANSFERS")
@Features(value = { @Feature(value = "TRANSFER TO COOP ACCOUNT") })
@Step ("Verify that the System generates an error mesasge where the customer has provided an Invalid account number when transfering to other accounts")
@Description ("Verify that the System generates an error mesasge where the customer has provided an Invalid account number when transfering to other accounts")
@Severity(SeverityLevel.BLOCKER)
public void Verify_that_the_System_generates_an_error_mesasge_where_the_customer_has_provided_an_Invalid_account_number_when_transfering_to_other_accounts() throws InterruptedException, IOException
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
	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2";
	driver.get(USSD_URL2); 
	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*2";
	driver.get(USSD_URL3);          	
	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*2*2";
	driver.get(USSD_URL4);	
	String USSD_URL5= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*2*2*1";
	driver.get(USSD_URL5);
	String USSD_URL6= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*2*2*1*4533345";
	driver.get(USSD_URL6);
	assertTrue(driver.getPageSource().contains("Entered Cannot be found"));
	System.out.println("TC11 Verify that the System generates an error mesasge where the customer has provided an Invalid account number |Success:");
         	
}
//
@Test (priority=12)
@Epic("USSD FUND TRANSFERS")
@Features(value = { @Feature(value = "TRANSFER TO COOP ACCOUNT") })
@Step ("Verify that Customer Can Proceed and confirm successfully send amount to COOP_ACCOUNT  other accounts")
@Description ("Verify that Customer Can Proceed and confirm successfully send amount to COOP_ACCOUNT other accounts")
@Severity(SeverityLevel.BLOCKER)
public void Verify_that_Customer_Can_Proceed_and_confirm_successfully_send_amount_to_COOP_ACCOUNT_other_accounts() throws InterruptedException, IOException
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
   	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2";
   	driver.get(USSD_URL2); 
   	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*2";
   	driver.get(USSD_URL3);          	
   	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*2*2";
   	driver.get(USSD_URL4);	
   	String USSD_URL5= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*2*2*1";
   	driver.get(USSD_URL5);
   	String USSD_URL6= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*2*2*1*01105607566200";
   	driver.get(USSD_URL6);
   	String USSD_URL7= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*2*2*1*01105607566200*200";
   	driver.get(USSD_URL7);
   	//assertTrue(driver.getPageSource().contains("CON Confirm Charges"));
   	String USSD_URL8= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*2*2*1*01105607566200*200*1";
   	driver.get(USSD_URL8);
   	Thread.sleep(5000);
        assertTrue(driver.getPageSource().contains("is Successful"));
        System.out.println("TC12 Verify that Customer Can Proceed and confirm successfully send amount to COOP_ACCOUNT my number |Success:");
}

@Test(priority = 13)
@Epic("USSD FUND TRANSFERS")
@Features(value = { @Feature(value = "TRANSFER TO COOP ACCOUNT") })
@Step ("TRANSFER TO COOP ACCOUNT TESTCASES")
@Description ("TRANSFER TO COOP ACCOUNT TESTCASES")
@Severity(SeverityLevel.TRIVIAL)
public void End_transfer_to_COOP_ACCOUNT_Test_cases() 
	{	
	
	System.out.println("************************END TRANSFER TO COOP ACCOUNT TESTCASES***********************************");

	}

}
