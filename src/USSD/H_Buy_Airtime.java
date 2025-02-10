
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


public class H_Buy_Airtime {
    
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
	testng.setTestClasses(new Class[] {H_Buy_Airtime.class});
	testng.run();
  	}
@Test(priority = 0)
@Epic("BUY AIRTIME")
@Features(value = { @Feature(value = "BUY AIRTIME") })
@Step ("BUY AIRTIME TESTCASES")
@Description ("BUY AIRTIME TESTCASES")
@Severity(SeverityLevel.TRIVIAL)
public void Starting_Buy_Airtime_Test_cases() 
	{	
	
	System.out.println("************************BUY AIRTIME TESTCASES***********************************");

	}
@Test (priority=1)
@Epic("USSD BUY AIRTIME")
@Features(value = { @Feature(value = "BUY AIRTIME") })
@Step ("Verify that customer can access  buy airtime menu on USSD")
@Description ("Verify that customer can access  buy airtime menu on USSD")
@Severity(SeverityLevel.BLOCKER)
public void Verify_that_customer_can_access_buy_airtime_menu_on_USSD() throws InterruptedException, IOException
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
          	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*4";
          	driver.get(USSD_URL2); 
          	assertTrue(driver.getPageSource().contains("CON Buy Airtime From")); 
          	
                  System.out.println("TC1 Verify that customer can access  buy airtime menu on USSD |Success:");
          	
}

@Test (priority=2)
@Epic("USSD BUY AIRTIME")
@Features(value = { @Feature(value = "BUY AIRTIME") })
@Step ("Verify that customer can access buy airtime for safaricom prepaid menu on USSD")
@Description ("Verify that customer can access buy airtime for safaricom prepaid menu on USSD")
@Severity(SeverityLevel.BLOCKER)
public void Verify_that_customer_can_access_buy_airtime_for_safaricom_prepaid_menu_on_USSD() throws InterruptedException, IOException
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
          	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*4";
          	driver.get(USSD_URL2); 
          	assertTrue(driver.getPageSource().contains("Buy Airtime From")); 
          	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*4*1";
          	driver.get(USSD_URL3); 
          	assertTrue(driver.getPageSource().contains("Pre Paid"));
          	
                  System.out.println("TC2 Verify that customer can access buy airtime for safaricom prepaid menu on USSD|Success:");
          	
}
@Test (priority=3)
@Epic("USSD BUY AIRTIME")
@Features(value = { @Feature(value = "BUY AIRTIME") })
@Step ("Verify that customer can access buy airtime for safaricom postpaid menu on USSD")
@Description ("Verify that customer can access buy airtime for safaricom postpaid menu on USSD")
@Severity(SeverityLevel.BLOCKER)
public void Verify_that_customer_can_access_buy_airtime_for_safaricom_postpaid_menu_on_USSD() throws InterruptedException, IOException
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
          	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*4";
          	driver.get(USSD_URL2); 
          	assertTrue(driver.getPageSource().contains("Buy Airtime From")); 
          	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*4*1";
          	driver.get(USSD_URL3); 
          	assertTrue(driver.getPageSource().contains("Post Paid"));
          	
                  System.out.println("TC3 Verify that customer can access buy airtime for safaricom postpaid menu on USSD|Success:");
          	
}
@Test (priority=4)
@Epic("USSD BUY AIRTIME")
@Features(value = { @Feature(value = "BUY AIRTIME") })
@Step ("Verify that customer can access buy airtime for Airtel postpaid menu on USSD")
@Description ("Verify that customer can access buy airtime for Airtel postpaid menu on USSD")
@Severity(SeverityLevel.BLOCKER)
public void Verify_that_customer_can_access_buy_airtime_for_Airtel_postpaid_menu_on_USSD() throws InterruptedException, IOException
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
          	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*4";
          	driver.get(USSD_URL2); 
          	assertTrue(driver.getPageSource().contains("Buy Airtime From")); 
          	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*4*2";
          	driver.get(USSD_URL3); 
          	assertTrue(driver.getPageSource().contains("Post Paid"));
          	
                  System.out.println("TC4 Verify that customer can access buy airtime for Airtel postpaid menu on USSD|Success:");
          	
}
@Test (priority=5)
@Epic("USSD BUY AIRTIME")
@Features(value = { @Feature(value = "BUY AIRTIME") })
@Step ("Verify that customer can access buy airtime for Airtel prepaid menu on USSD")
@Description ("Verify that customer can access buy airtime for Airtel prepaid menu on USSD")
@Severity(SeverityLevel.BLOCKER)
public void Verify_that_customer_can_access_buy_airtime_for_Airtel_Prepaid_menu_on_USSD() throws InterruptedException, IOException
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
          	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*4";
          	driver.get(USSD_URL2); 
          	assertTrue(driver.getPageSource().contains("Buy Airtime From")); 
          	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*4*2";
          	driver.get(USSD_URL3); 
          	assertTrue(driver.getPageSource().contains("Pre Paid"));
          	
                  System.out.println("TC5 Verify that customer can access buy airtime for Airtel prepaid menu on USSD|Success:");
          	
}
@Test (priority=6)
@Epic("USSD BUY AIRTIME")
@Features(value = { @Feature(value = "BUY AIRTIME") })
@Step ("Verify that customer can access safaricomto other number menu on USSD")
@Description ("Verify that customer can access safaricomto other number menu on USSD")
@Severity(SeverityLevel.BLOCKER)
public void Verify_that_customer_can_access_Safaricom_other_number_menu_on_USSD() throws InterruptedException, IOException
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
          	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*4";
          	driver.get(USSD_URL2); 
          	assertTrue(driver.getPageSource().contains("Buy Airtime From")); 
          	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*4*2";
          	driver.get(USSD_URL3); 
          	//assertTrue(driver.getPageSource().contains("Pre Paid"));
          	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*4*2*1";
          	driver.get(USSD_URL4); 
          	assertTrue(driver.getPageSource().contains("Other Mobile Numbers"));
          	
                  System.out.println("TC6 Verify that customer can access safaricomto other number menu on USSD|Success:");
          	
}
@Test (priority=7)
@Epic("USSD BUY AIRTIME")
@Features(value = { @Feature(value = "BUY AIRTIME") })
@Step ("Verify that customer can access safaricomto my number menu on USSD")
@Description ("Verify that customer can access safaricomto my number menu on USSD")
@Severity(SeverityLevel.BLOCKER)
public void Verify_that_customer_can_access_Safaricom_my_number_menu_on_USSD() throws InterruptedException, IOException
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
          	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*4";
          	driver.get(USSD_URL2); 
          	assertTrue(driver.getPageSource().contains("Buy Airtime From")); 
          	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*4*2";
          	driver.get(USSD_URL3); 
          	//assertTrue(driver.getPageSource().contains("Pre Paid"));
          	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*4*2*1";
          	driver.get(USSD_URL4); 
          	assertTrue(driver.getPageSource().contains("My Number"));
          	
                  System.out.println("TC7 Verify that customer can access safaricomto my number menu on USSD|Success:");
          	
}
@Test (priority=8)
@Epic("USSD BUY AIRTIME")
@Features(value = { @Feature(value = "BUY AIRTIME") })
@Step ("Verify that customer can select debit account to use when buying airtime on USSD")
@Description ("Verify that customer can select debit account to use when buying airtime on USSD")
@Severity(SeverityLevel.BLOCKER)
public void Verify_that_customer_can_select_debit_account_to_use_when_buying_airtime_on_USSD() throws InterruptedException, IOException
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
          	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*4";
          	driver.get(USSD_URL2); 
          	//assertTrue(driver.getPageSource().contains("Buy Airtime From")); 
          	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*4*2";
          	driver.get(USSD_URL3); 
          	//assertTrue(driver.getPageSource().contains("Pre Paid"));
          	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*4*2*1";
          	driver.get(USSD_URL4); 
               	String USSD_URL5= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*4*2*1*1";
          	driver.get(USSD_URL5); 
          	assertTrue(driver.getPageSource().contains("Select Account to Debit"));
          	
                  System.out.println("TC8 Verify that customer can select debit account to use when buying airtime on USSD|Success:");
          	
}
@Test (priority=9)
@Epic("USSD BUY AIRTIME")
@Features(value = { @Feature(value = "BUY AIRTIME") })
@Step ("Verify that customer can enter the Amount when  buying airtime on USSD")
@Description ("Verify that customer can enter the Amount when  buying airtime on USSD")
@Severity(SeverityLevel.BLOCKER)
public void Verify_that_customer_can_enter_the_amount_when_buying_airtime_on_USSD() throws InterruptedException, IOException
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
          	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*4";
          	driver.get(USSD_URL2); 
          	assertTrue(driver.getPageSource().contains("Buy Airtime From")); 
          	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*4*2";
          	driver.get(USSD_URL3); 
          	//assertTrue(driver.getPageSource().contains("Pre Paid"));
          	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*4*2*1";
          	driver.get(USSD_URL4); 
               	String USSD_URL5= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*4*2*1*1";
          	driver.get(USSD_URL5); 
          	assertTrue(driver.getPageSource().contains("Select Account to Debit"));
          	
                  System.out.println("TC9 Verify that customer can enter the Amount when  buying airtime on USSD|Success:");
          	
}
@Test (priority=10)
@Epic("USSD BUY AIRTIME")
@Features(value = { @Feature(value = "BUY AIRTIME") })
@Step ("Verify that customer can confirm bank charges when  buying airtime on USSD")
@Description ("Verify that customer can confirm bank charges when  buying airtime on USSD")
@Severity(SeverityLevel.BLOCKER)
public void Verify_that_customer_can_confirm_bank_charges_when_buying_airtime_on_USSD() throws InterruptedException, IOException
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
    		XSSFSheet sheetAmount = USSD.getSheet("Fund Transfer");
    		String amountfromexcel = formatter.formatCellValue(sheetAmount.getRow(1).getCell(0)); 
    		formatter.formatCellValue(sheet.getRow(2).getCell(3));
         	new Random();
         	long random_MSSID = ThreadLocalRandom.current().nextLong(800000000L, 89999999999L);
         	String USSD_URL = "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95";
         	driver.manage().window().maximize();
         	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
         	driver.get(USSD_URL);
         	String USSD_URL1= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111";
          	driver.get(USSD_URL1); 
          	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*4";
          	driver.get(USSD_URL2); 
          	//assertTrue(driver.getPageSource().contains("Buy Airtime From")); 
          	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*4*2";
          	driver.get(USSD_URL3); 
          	//assertTrue(driver.getPageSource().contains("Pre Paid"));
          	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*4*2*1";
          	driver.get(USSD_URL4); 
               	String USSD_URL5= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*4*2*1*1";
          	driver.get(USSD_URL5); 
        	String USSD_URL6= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*4*2*1*1*1";
        	
        	
        	//System.out.println("My Amount = "+amountfromexcel);
          	driver.get(USSD_URL6);
        	String USSD_URL7= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*4*2*1*1*"+amountfromexcel;
          	driver.get(USSD_URL7); 
          	assertTrue(driver.getPageSource().contains("CON Confirm Charges: Bank Charges: 20.00 Excise Duty 4.00 "));
          	
                  System.out.println("TC10 Verify that customer can confirm bank charges when  buying airtime on USSD|Success:");
          	
}
@Test (priority=11)
@Epic("USSD BUY AIRTIME")
@Features(value = { @Feature(value = "BUY AIRTIME") })
@Step ("Verify that customer can enter the Amount and proceed to buy airtime on USSD")
@Description ("Verify that customer can enter the Amount and proceed to buy airtime on USSD")
@Severity(SeverityLevel.BLOCKER)
public void Verify_that_customer_can_enter_amount_and_proceed_to_buy_airtime_on_USSD() throws InterruptedException, IOException
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
	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*4";
	driver.get(USSD_URL2); 
	assertTrue(driver.getPageSource().contains("Buy Airtime From")); 
	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*4*2";
	driver.get(USSD_URL3); 
	//assertTrue(driver.getPageSource().contains("Pre Paid"));
	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*4*2*1";
	driver.get(USSD_URL4); 
   	String USSD_URL5= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*4*2*1*1";
	driver.get(USSD_URL5); 
	String USSD_URL6= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*4*2*1*1*1";
	driver.get(USSD_URL6);
	String USSD_URL7= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*4*2*1*1*200";
	driver.get(USSD_URL7); 
	assertTrue(driver.getPageSource().contains("CON Confirm Charges: Bank Charges: 20.00 Excise Duty 4.00 "));
	String USSD_URL8= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*4*2*1*1*200*1";
	driver.get(USSD_URL8);
	assertTrue(driver.getPageSource().contains("Your Airtime Purchase"));
          	
                  System.out.println("TC11 Verify that customer can enter the Amount and proceed to buy airtime on USSD|Success:");
          	
}
@Test(priority = 12)
@Epic("BUY AIRTIME")
@Features(value = { @Feature(value = "BUY AIRTIME") })
@Step ("BUY AIRTIME TESTCASES")
@Description ("BUY AIRTIME TESTCASES")
@Severity(SeverityLevel.TRIVIAL)
public void End_Buy_Airtime_Test_cases() 
	{	
	
	System.out.println("************************END OF BUY AIRTIME TESTCASES***********************************");

	}


}