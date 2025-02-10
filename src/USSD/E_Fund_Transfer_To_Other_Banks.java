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


public class E_Fund_Transfer_To_Other_Banks {
    
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
	testng.setTestClasses(new Class[] {E_Fund_Transfer_To_Other_Banks.class});
	testng.run();
  	}

@Test(priority = 0)
@Epic("USSD FUND TRANSFERS")
@Features(value = { @Feature(value = "TRANSFER TO OTHER BANKS") })
@Step ("TRANSFER TO OTHER BANKS TESTCASES")
@Description ("TRANSFER TO OTHER BANKS TESTCASES")
@Severity(SeverityLevel.TRIVIAL)
public void Starting_transfer_to_mpesa_Test_cases() 
	{	
	
	System.out.println("************************TRANSFER TO OTHER BANKS TESTCASES***********************************");

	}

@Test (priority=1)
@Epic("USSD FUND TRANSFERS")
@Features(value = { @Feature(value = "TRANSFER TO OTHER BANKS") })
@Step ("Verify_that customer can access  send money to other banks menu on USSD")
@Description ("Verify_that customer can access  send money to other banks menu on USSD")
@Severity(SeverityLevel.BLOCKER)
public void Verify_that_customer_can_access_send_money_to_other_Banks_menu_on_USSD() throws InterruptedException, IOException
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
          	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*3";
          	driver.get(USSD_URL3); 
          	assertTrue(driver.getPageSource().contains("Pesalink")); 
          	System.out.println("TC0 Verify_that customer can access  send money to other banks menu on USSD |Success:");
          	
}

@Test (priority=2)
@Epic("USSD FUND TRANSFERS")
@Features(value = { @Feature(value = "PESALINK") })
public void Pesalink_testcases() throws InterruptedException, IOException
{

System.out.println("**********************PESALINK TESTCASES******************************");

}

@Test (priority=3)
@Epic("USSD FUND TRANSFERS")
@Features(value = { @Feature(value = "PESALINK") })
@Step ("Verify_that customer can access  pesalink menu on USSD")
@Description ("Verify_that customer can access  pesalink menu on USSD")
@Severity(SeverityLevel.BLOCKER)
public void Verify_that_customer_can_access_pesalink_menu_on_USSD() throws InterruptedException, IOException
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
          	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*3";
          	driver.get(USSD_URL3); 
          	assertTrue(driver.getPageSource().contains("Pesalink")); 
          	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*3*1";
          	driver.get(USSD_URL4);
          	assertTrue(driver.getPageSource().contains("Send to Account")); 
                  System.out.println("TC1 Verify_that_customer_can_access_pesalink_menu_on_USSD |Success:");
          	
}
@Test (priority=4)
@Epic("USSD FUND TRANSFERS")
@Features(value = { @Feature(value = "PESALINK") })
@Step ("Verify_that customer can send to account on USSD")
@Description ("Verify_that customer can send to account on USSD")
@Severity(SeverityLevel.BLOCKER)
public void Verify_that_customer_can_select_send_to_account_number_on_USSD() throws InterruptedException, IOException
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
	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*3";
	driver.get(USSD_URL3); 
	assertTrue(driver.getPageSource().contains("Pesalink")); 
	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*3*1";
	driver.get(USSD_URL4);
	assertTrue(driver.getPageSource().contains("Send to Account"));
	Thread.sleep(2000);
          	System.out.println("TC2 Verify_that customer can send to account on USSD |Success:");
          	
}

@Test (priority=5)
@Epic("USSD FUND TRANSFERS")
@Features(value = { @Feature(value = "PESALINK") })
@Step ("Verify_that customer can choose send to account number USSD")
@Description ("Verify_that customer can choose send to account number USSD")
@Severity(SeverityLevel.BLOCKER)
public void Verify_that_customer_can_choose_send_to_account_number_USSD() throws InterruptedException, IOException
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
	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*3";
	driver.get(USSD_URL3); 
	assertTrue(driver.getPageSource().contains("Pesalink")); 
	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*3*1";
	driver.get(USSD_URL4);
	assertTrue(driver.getPageSource().contains("Send to Account"));
        System.out.println("TC3 Verify_that customer can choose send to account number USSD |Success:");
          	
}
@Test (priority=6)
@Epic("USSD FUND TRANSFERS")
@Features(value = { @Feature(value = "PESALINK") })
@Step ("Verify_that customer can choose send to mobile number USSD")
@Description ("Verify_that customer can choose send to mobile number USSD")
@Severity(SeverityLevel.BLOCKER)
public void Verify_that_customer_can_choose_the_customer_to_send_to_Mobile_numberUSSD() throws InterruptedException, IOException
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
	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*3";
	driver.get(USSD_URL3); 
	assertTrue(driver.getPageSource().contains("Pesalink")); 
	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*3*1";
	driver.get(USSD_URL4);
	assertTrue(driver.getPageSource().contains("Send to Mobile"));
        System.out.println("TC4 Verify_that customer can choose send to mobile number USSD|Success:");
          	
}
@Test (priority=7)
@Epic("USSD FUND TRANSFERS")
@Features(value = { @Feature(value = "PESALINK") })
@Step ("Verify_that customer can choose register for Pesalink USSD")
@Description ("Verify_that customer can choose register for Pesalink USSD")
@Severity(SeverityLevel.BLOCKER)
public void Verify_that_customer_can_choose_the_Register_for_pesalink_USSD() throws InterruptedException, IOException
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
	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*3";
	driver.get(USSD_URL3); 
	assertTrue(driver.getPageSource().contains("Pesalink")); 
	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*3*1";
	driver.get(USSD_URL4);	
	assertTrue(driver.getPageSource().contains("Register for Pesalink"));
        System.out.println("TC5 Verify_that customer can choose register for Pesalink USSD|Success:");
          	
}
@Test (priority=8)
@Epic("USSD FUND TRANSFERS")
@Features(value = { @Feature(value = "PESALINK") })
@Step ("Verify_that customer can choose deregister for Pesalink USSD")
@Description ("Verify_that customer can choose deregister for Pesalink USSD")
@Severity(SeverityLevel.BLOCKER)
public void Verify_that_customer_can_choose_the_deRegister_for_pesalink_USSD() throws InterruptedException, IOException
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
	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*3";
	driver.get(USSD_URL3); 
	assertTrue(driver.getPageSource().contains("Pesalink")); 
	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*3*1";
	driver.get(USSD_URL4);	
	assertTrue(driver.getPageSource().contains("De-Register from Pesalink"));
        System.out.println("TC6 Verify_that customer can choose deregister for Pesalink USSD|Success:");
          	
}

@Test (priority=9)
@Epic("USSD FUND TRANSFERS")
@Features(value = { @Feature(value = "PESALINK") })
@Step ("Verify_that customer can choose Bank name from the list USSD")
@Description ("Verify_that customer can choose Bank name from the list USSD")
@Severity(SeverityLevel.BLOCKER)
public void Verify_that_customer_can_choose_Bank_name_from_the_list() throws InterruptedException, IOException
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
	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*3";
	driver.get(USSD_URL3); 
	assertTrue(driver.getPageSource().contains("Pesalink")); 
	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*3*1";
	driver.get(USSD_URL4);	
	String USSD_URL5= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*3*1";
	driver.get(USSD_URL5);
	String USSD_URL6= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*3*1";
	driver.get(USSD_URL6);
	assertTrue(driver.getPageSource().contains("BARCLAYS (ABSA)"));
        System.out.println("TC7 Verify_that customer can choose Bank name from the list USSD|Success:");
          	
}

@Test (priority=10)
@Epic("USSD FUND TRANSFERS")
@Features(value = { @Feature(value = "PESALINK") })
@Step ("Verify_that customer can Enter recipient Account number")
@Description ("Verify_that customer can Enter recipient Account number")
@Severity(SeverityLevel.BLOCKER)
public void Verify_that_customer_can_recipient_account_number() throws InterruptedException, IOException
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
	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*3";
	driver.get(USSD_URL3); 
	assertTrue(driver.getPageSource().contains("Pesalink")); 
	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*3*1";
	driver.get(USSD_URL4);	
	String USSD_URL5= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*3*1*1";
	driver.get(USSD_URL5);
	String USSD_URL6= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*3*1*1*1";
	driver.get(USSD_URL6);
	String USSD_URL7= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*3*1*1*1*2";
	driver.get(USSD_URL7);	
	assertTrue(driver.getPageSource().contains("CON Enter the recepients account number"));
        System.out.println("TC8 Verify_that customer can Enter recipient Account number|Success:");
          	
}
@Test (priority=11)
@Epic("USSD FUND TRANSFERS")
@Features(value = { @Feature(value = "PESALINK") })
@Step ("Verify_that customer can select account to send from")
@Description ("Verify_that customer can select account to send from")
@Severity(SeverityLevel.BLOCKER)
public void Verify_that_customer_can_select_account_to_send_from() throws InterruptedException, IOException
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
	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*3";
	driver.get(USSD_URL3); 
	assertTrue(driver.getPageSource().contains("Pesalink")); 
	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*3*1";
	driver.get(USSD_URL4);	
	String USSD_URL5= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*3*1*1";
	driver.get(USSD_URL5);
	String USSD_URL6= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*3*1*1*1";
	driver.get(USSD_URL6);
	String USSD_URL7= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*3*1*1*1*2";
	driver.get(USSD_URL7);	
	assertTrue(driver.getPageSource().contains("CON Enter the recepients account number"));
	String USSD_URL8= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*3*1*1*1*2*0990786555*1";
	driver.get(USSD_URL8);
	assertTrue(driver.getPageSource().contains("CON Select Account to send from"));	
        System.out.println("TC9 Verify_that customer can select account to send from|Success:");
          	
}
@Test (priority=12)
@Epic("USSD FUND TRANSFERS")
@Features(value = { @Feature(value = "PESALINK") })
@Step ("Verify_that customer can Enter the amount to send")
@Description ("Verify_that customer can Enter the amount to send")
@Severity(SeverityLevel.BLOCKER)
public void Verify_that_customer_can_Enter_amount_to_send() throws InterruptedException, IOException
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
	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*3";
	driver.get(USSD_URL3); 
	assertTrue(driver.getPageSource().contains("Pesalink")); 
	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*3*1";
	driver.get(USSD_URL4);	
	String USSD_URL5= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*3*1*1";
	driver.get(USSD_URL5);
	String USSD_URL6= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*3*1*1*1";
	driver.get(USSD_URL6);
	String USSD_URL7= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*3*1*1*1*2";
	driver.get(USSD_URL7);	
	assertTrue(driver.getPageSource().contains("CON Enter the recepients account number"));
	String USSD_URL8= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*3*1*1*1*2*0990786555*1";
	driver.get(USSD_URL8);
	assertTrue(driver.getPageSource().contains("CON Select Account to send from"));	
	
        System.out.println("TC10 Verify_that customer can Enter the amount to send|Success:");
          	
}

@Test (priority=13)
@Epic("USSD FUND TRANSFERS")
@Features(value = { @Feature(value = "PESALINK") })
@Step ("Verify_that customer can confirm bank charges and send using pesalink")
@Description ("Verify_that customer can confirm bank charges and send using pesalink")
@Severity(SeverityLevel.BLOCKER)
public void Verify_that_customer_can_Confirm_bank_charges_and_send_using_pesalink() throws InterruptedException, IOException
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
	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*3";
	driver.get(USSD_URL3); 
	assertTrue(driver.getPageSource().contains("Pesalink")); 
	String USSD_URL4= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*3*1";
	driver.get(USSD_URL4);	
	String USSD_URL5= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*3*1*1";
	driver.get(USSD_URL5);
	String USSD_URL6= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*3*1*1*1";
	driver.get(USSD_URL6);
	String USSD_URL7= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*3*1*1*1*2";
	driver.get(USSD_URL7);	
	assertTrue(driver.getPageSource().contains("CON Enter the recepients account number"));
	String USSD_URL8= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*3*1*1*1*2*0990786555*1";
	driver.get(USSD_URL8);
	assertTrue(driver.getPageSource().contains("CON Select Account to send from"));
	String USSD_URL9= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*3*1*1*1*2*0990786555*1*200";
	driver.get(USSD_URL9);	
	String USSD_URL10= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*3*1*1*1*2*0990786555*1*200";
	driver.get(USSD_URL10);	
	String USSD_URL11= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*3*1*1*1*2*0990786555*1*200*1";
	driver.get(USSD_URL11);
	assertTrue(driver.getPageSource().contains("Your Pesalink transfer"));
	
        System.out.println("TC11 Verify_that customer can confirm bank charges and send using pesalink|Success:");
          	
}
@Test(priority = 14)
@Epic("USSD FUND TRANSFERS")
@Features(value = { @Feature(value = "PESALINK") })
public void End_pesalink_Test_cases() 
{	

System.out.println("************************END PESALINK TESTCASES***********************************");

}

@Test(priority = 15)
@Epic("USSD FUND TRANSFERS")
@Features(value = { @Feature(value = "RTGS") })
public void RTGS_Test_cases() 
{	

System.out.println("************************RTGS TESTCASES***********************************");
}

@Test (priority=16)
@Epic("USSD FUND TRANSFERS")
@Features(value = { @Feature(value = "RTGS") })
@Step ("Verify_that customer can access  RTGS menu on USSD")
@Description ("Verify_that customer can access  RTGS menu on USSD")
@Severity(SeverityLevel.BLOCKER)
public void Verify_that_customer_can_access_RTGS_menu_on_USSD() throws InterruptedException, IOException
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
          	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*3";
          	driver.get(USSD_URL3); 
          	assertTrue(driver.getPageSource().contains("RTGS")); 
          	System.out.println("TC1 Verify_that customer can access  RTGS menu on USSD |Success:");
          	
}
@Test(priority = 17)
@Epic("USSD FUND TRANSFERS")
@Features(value = { @Feature(value = "RTGS") })
public void END_RTGS_Test_cases() 
{	

System.out.println("************************END RTGS TESTCASES***********************************");
}
@Test(priority = 18)
@Epic("USSD FUND TRANSFERS")
@Features(value = { @Feature(value = "TRANSFER TO OTHER BANKS") })
@Step ("TRANSFER TO OTHER BANKS TESTCASES")
@Description ("TRANSFER TO OTHER BANKS TESTCASES")
@Severity(SeverityLevel.TRIVIAL)
public void End_of_transfer_to_mpesa_Test_cases() 
	{	
	
	System.out.println("************************END OF TRANSFER TO OTHER BANKS TESTCASES***********************************");

	}
}

