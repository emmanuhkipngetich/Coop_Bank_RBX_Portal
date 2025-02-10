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
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.TestNG;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Features;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
public class B_Account_Management {
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
  	testng.setTestClasses(new Class[] {B_Account_Management.class});
 	testng.run();
      	}

@Test(priority = 0)
@Epic("USSD ACCOUNT MANAGEMENT")
@Features(value = { @Feature(value = "ACCOUNT MANAGEMENT") })
@Step ("ACCOUNT MANAGEMENT TESTCASES")
@Description ("ACCOUNT MANAGEMENT TESTCASES")
@Severity(SeverityLevel.TRIVIAL)
public void Start_account_management_testcases() 
	{
    	System.out.println("ACCOUNT MANAGEMENT");	
    	System.out.println("************************ACCOUNT_MANAGEMENT TESTCASES***********************************");

	}
//*******************************************************************************************************************************************************
@Test (priority=1)
@Epic("USSD ACCOUNT MANAGEMENT")
@Features(value = { @Feature(value = "ACCOUNT MANAGEMENT") })
@Step ("Verify_that customer can view selected account balance on USSD")
@Description ("Verify_that customer can view selected account balance on USSD")
@Severity(SeverityLevel.BLOCKER)
public void Verify_that_customer_can_view_selected_account_balance_on_USSD() throws InterruptedException, IOException
	{
                File src = new File(my_dir + "\\Data_Driven_Excel_File\\USSD.xlsx");    
            	FileInputStream fis = new FileInputStream(src);       
            	USSD = new XSSFWorkbook(fis);      
            	sheet = USSD.getSheet("USSD");DataFormatter formatter = new DataFormatter();
            	new Random();
            	String val_PHONE_NO = formatter.formatCellValue(sheet.getRow(2).getCell(2));
            	sheet = USSD.getSheet("A_Login");	
            	String Environment = formatter.formatCellValue(sheet.getRow(1).getCell(0));
            	long random_MSSID = ThreadLocalRandom.current().nextLong(800000000L, 89999999999L);
            	String USSD_URL = Environment+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95";
            	driver.get(USSD_URL);
         	driver.manage().window().maximize();
         	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
         	String currentURL = driver.getCurrentUrl();
         	sheet = USSD.getSheet("A_Login");
        	String CORRECT_PIN = formatter.formatCellValue(sheet.getRow(1).getCell(5));
        	String USSD_URL1= currentURL+"*"+ CORRECT_PIN;       	
        	driver.get(USSD_URL1);         	 
          	assertTrue(driver.getPageSource().contains("Account Enquiry")); 
          	String USSD_URL2= currentURL+ CORRECT_PIN + "*1";
          	driver.get(USSD_URL2);
        	String USSD_URL3= currentURL+ CORRECT_PIN+ "*1*1";
          	driver.get(USSD_URL3);
        	String USSD_URL4= currentURL+ CORRECT_PIN+ "*1*1*1";
          	driver.get(USSD_URL4);        	
          	assertTrue(driver.getPageSource().contains("CON Your available balance for account")); 
          	System.out.println("TC1 Verify that customer can view selected account balance on USSD |Success:");
}
//*******************************************************************************************************************************************************
@Test (priority=2)
@Epic("USSD ACCOUNT MANAGEMENT")
@Features(value = { @Feature(value = "ACCOUNT MANAGEMENT") })
@Step ("Verify that the customer can successfully generate and display Mini Statement on USSD")
@Description ("Verify that the customer can successfully generate and display Mini Statement on USSD")
@Severity(SeverityLevel.BLOCKER)
public void Verify_that_the_customer_can_successfully_generate_and_display_Mini_Statement_on_USSD() throws InterruptedException, IOException
	{
                File src = new File(my_dir + "\\Data_Driven_Excel_File\\USSD.xlsx");    
            	FileInputStream fis = new FileInputStream(src);       
            	USSD = new XSSFWorkbook(fis);      
            	sheet = USSD.getSheet("USSD");DataFormatter formatter = new DataFormatter();
            	new Random();
            	String val_PHONE_NO = formatter.formatCellValue(sheet.getRow(2).getCell(2));
            	sheet = USSD.getSheet("A_Login");	
            	String Environment = formatter.formatCellValue(sheet.getRow(1).getCell(0));
            	long random_MSSID = ThreadLocalRandom.current().nextLong(800000000L, 89999999999L);
            	String USSD_URL = Environment+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95";
            	driver.get(USSD_URL);
            	driver.manage().window().maximize();
         	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
         	String currentURL = driver.getCurrentUrl();
         	sheet = USSD.getSheet("A_Login");
        	String CORRECT_PIN = formatter.formatCellValue(sheet.getRow(1).getCell(5));
        	String USSD_URL1= currentURL+"*"+ CORRECT_PIN; 
        	driver.get(USSD_URL1);
          	assertTrue(driver.getPageSource().contains("Account Enquiry")); 
          	String USSD_URL2= currentURL+ CORRECT_PIN + "*1";
          	driver.get(USSD_URL2);
        	String USSD_URL3= currentURL+ CORRECT_PIN+ "*1*3";
          	driver.get(USSD_URL3);
        	String USSD_URL4= currentURL+ CORRECT_PIN+ "*1*3*1";
          	driver.get(USSD_URL4);
          	System.out.println("TC2 Verify that the customer can successfully generate and display Mini Statement on USSD |Success:");
         	
}
//*******************************************************************************************************************************************************
@Test (priority=3)
@Epic("USSD ACCOUNT MANAGEMENT")
@Features(value = { @Feature(value = "ACCOUNT MANAGEMENT") })
@Step ("Verify success message on successful subscription")
@Description ("Verify success message on successful subscription")
@Severity(SeverityLevel.BLOCKER)
public void Verify_success_message_on_successful_subscription() throws InterruptedException, IOException
	{
                File src = new File(my_dir + "\\Data_Driven_Excel_File\\USSD.xlsx");    
            	FileInputStream fis = new FileInputStream(src);       
            	USSD = new XSSFWorkbook(fis);      
            	sheet = USSD.getSheet("USSD");DataFormatter formatter = new DataFormatter();
            	new Random();
            	String val_PHONE_NO = formatter.formatCellValue(sheet.getRow(2).getCell(2));
            	sheet = USSD.getSheet("A_Login");	
            	String Environment = formatter.formatCellValue(sheet.getRow(1).getCell(0));
            	long random_MSSID = ThreadLocalRandom.current().nextLong(800000000L, 89999999999L);
            	String USSD_URL = Environment+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95";
            	driver.get(USSD_URL);
            	driver.manage().window().maximize();
            	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            	String currentURL = driver.getCurrentUrl();
         	sheet = USSD.getSheet("A_Login");
        	String CORRECT_PIN = formatter.formatCellValue(sheet.getRow(1).getCell(5));
        	String USSD_URL1= currentURL+"*"+ CORRECT_PIN; 
        	driver.get(USSD_URL1);
          	assertTrue(driver.getPageSource().contains("Account Enquiry")); 
          	String USSD_URL2= currentURL+ CORRECT_PIN + "*1";
          	driver.get(USSD_URL2);
          	assertTrue(driver.getPageSource().contains("Mini Statement")); 
        	String USSD_URL3= currentURL+ CORRECT_PIN+ "*1*3";
          	driver.get(USSD_URL3);
            	System.out.println("TC3 Verify success message on successful subscription |Success:");
         	
}
//*******************************************************************************************************************************************************
//e-Statement
@Test (priority=4)
@Epic("USSD ACCOUNT MANAGEMENT")
@Features(value = { @Feature(value = "ACCOUNT MANAGEMENT") })
@Step ("Verify a user can select e_statement menu on USSD")
@Description ("Verify a user can select e_statement menu on USSD")
@Severity(SeverityLevel.BLOCKER)
public void Verify_a_user_can_select_e_statement_menu_on_USSD() throws InterruptedException, IOException
	{
                File src = new File(my_dir + "\\Data_Driven_Excel_File\\USSD.xlsx");    
            	FileInputStream fis = new FileInputStream(src);       
            	USSD = new XSSFWorkbook(fis);      
            	sheet = USSD.getSheet("USSD");DataFormatter formatter = new DataFormatter();
            	new Random();
            	String val_PHONE_NO = formatter.formatCellValue(sheet.getRow(2).getCell(2));
            	sheet = USSD.getSheet("A_Login");	
            	String Environment = formatter.formatCellValue(sheet.getRow(1).getCell(0));
            	long random_MSSID = ThreadLocalRandom.current().nextLong(800000000L, 89999999999L);
            	String USSD_URL = Environment+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95";
            	driver.get(USSD_URL);
            	driver.manage().window().maximize();
            	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            	String currentURL = driver.getCurrentUrl();
            	sheet = USSD.getSheet("A_Login");
            	String CORRECT_PIN = formatter.formatCellValue(sheet.getRow(1).getCell(5));
            	String USSD_URL1= currentURL+"*"+ CORRECT_PIN; 
            	driver.get(USSD_URL1); 
            	String USSD_URL2= currentURL+ CORRECT_PIN + "*1";
          	driver.get(USSD_URL2);
          	assertTrue(driver.getPageSource().contains("e-Statement"));          	
            	System.out.println("TC4 Verify a user can select e_statement menu on USSD |Success:");
         	
}
//*******************************************************************************************************************************************************

@Test (priority=5)
@Epic("USSD ACCOUNT MANAGEMENT")
@Features(value = { @Feature(value = "ACCOUNT MANAGEMENT") })
@Step ("Verify that a customer can successfully subscribe for e-statement (Monthly) for any of his/her linked accounts")
@Description ("Verify that a customer can successfully unsubscribe for e-statement (Monthly) for any of his/her linked accounts")
@Severity(SeverityLevel.BLOCKER)
public void Verify_that_a_customer_can_successfully_subscribe_for_e_statement_Monthly_for_any_of_his_her_linked_accounts() throws InterruptedException, IOException
	{
                File src = new File(my_dir + "\\Data_Driven_Excel_File\\USSD.xlsx");    
            	FileInputStream fis = new FileInputStream(src);       
            	USSD = new XSSFWorkbook(fis);      
            	sheet = USSD.getSheet("USSD");DataFormatter formatter = new DataFormatter();
            	new Random();
            	String val_PHONE_NO = formatter.formatCellValue(sheet.getRow(2).getCell(2));
            	sheet = USSD.getSheet("A_Login");	
            	String Environment = formatter.formatCellValue(sheet.getRow(1).getCell(0));
            	long random_MSSID = ThreadLocalRandom.current().nextLong(800000000L, 89999999999L);
            	String USSD_URL = Environment+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95";
            	driver.get(USSD_URL);
            	driver.manage().window().maximize();
            	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            	String currentURL = driver.getCurrentUrl();
            	sheet = USSD.getSheet("A_Login");
            	String CORRECT_PIN = formatter.formatCellValue(sheet.getRow(1).getCell(5));
            	String USSD_URL1= currentURL+"*"+ CORRECT_PIN; 
            	driver.get(USSD_URL1);
            	assertTrue(driver.getPageSource().contains("Account Enquiry")); 
            	String USSD_URL2= currentURL+ CORRECT_PIN+ "*1";
          	driver.get(USSD_URL2);       	
          	String USSD_URL3= currentURL+ CORRECT_PIN+  "*1*2";
          	driver.get(USSD_URL3);
        	String USSD_URL4= currentURL+ CORRECT_PIN+  "*1*2*1";
          	driver.get(USSD_URL4);
        	String USSD_URL5= currentURL+ CORRECT_PIN+ "*1*2*1*1";
          	driver.get(USSD_URL5);
        	String USSD_URL6= currentURL+ CORRECT_PIN+ "*1*2*1*1*1";
          	driver.get(USSD_URL6);
          	System.out.println("TC5 Verify that a customer can successfully subscribe for e-statement (Monthly) for any of his/her linked accounts |Success:");
          	
          	
	}
//*******************************************************************************************************************************************************

@Test (priority=6)
@Epic("USSD ACCOUNT MANAGEMENT")
@Features(value = { @Feature(value = "ACCOUNT MANAGEMENT") })
@Step ("Verify that a customer can successfully unsubscribe for e-statement (Monthly) for any of his/her linked accounts")
@Description ("Verify that a customer can successfully unsubscribe for e-statement (Monthly) for any of his/her linked accounts")
@Severity(SeverityLevel.BLOCKER)
public void Verify_that_a_customer_can_successfully_unsubscribe_for_e_statement_Monthly_for_any_of_his_her_linked_accounts() throws InterruptedException, IOException
	{
                File src = new File(my_dir + "\\Data_Driven_Excel_File\\USSD.xlsx");    
            	FileInputStream fis = new FileInputStream(src);       
            	USSD = new XSSFWorkbook(fis);      
            	sheet = USSD.getSheet("USSD");DataFormatter formatter = new DataFormatter();
            	new Random();
            	String val_PHONE_NO = formatter.formatCellValue(sheet.getRow(2).getCell(2));
            	sheet = USSD.getSheet("A_Login");	
            	String Environment = formatter.formatCellValue(sheet.getRow(1).getCell(0));
            	long random_MSSID = ThreadLocalRandom.current().nextLong(800000000L, 89999999999L);
            	String USSD_URL = Environment+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95";
            	driver.get(USSD_URL);
            	driver.manage().window().maximize();
            	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            	String currentURL = driver.getCurrentUrl();
            	sheet = USSD.getSheet("A_Login");
            	String CORRECT_PIN = formatter.formatCellValue(sheet.getRow(1).getCell(5));
            	String USSD_URL1= currentURL+"*"+ CORRECT_PIN; 
            	driver.get(USSD_URL1);
            	assertTrue(driver.getPageSource().contains("Account Enquiry")); 
            	String USSD_URL2= currentURL+ CORRECT_PIN+ "*1";
          	driver.get(USSD_URL2);       	
          	String USSD_URL3= currentURL+ CORRECT_PIN+  "*1*2";
          	driver.get(USSD_URL3);
        	String USSD_URL4= currentURL+ CORRECT_PIN+  "*1*2*1";
          	driver.get(USSD_URL4);
        	String USSD_URL5= currentURL+ CORRECT_PIN+ "*1*2*1*1";
          	driver.get(USSD_URL5);
        	String USSD_URL6= currentURL+ CORRECT_PIN+ "*1*2*1*1*1";
          	driver.get(USSD_URL6);
            	System.out.println("TC6 Verify that a customer can successfully unsubscribe for e-statement (Monthly) for any of his/her linked accounts |Success:");
          	
          	
	}

//*******************************************************************************************************************************************************

@Test(priority = 7)
@Epic("ACCOUNT MANAGEMENT")
@Features(value = { @Feature(value = "ACCOUNT MANAGEMENT") })
@Step ("ACCOUNT MANAGEMENT TESTCASES")
@Description ("ACCOUNT MANAGEMENT TESTCASES")
@Severity(SeverityLevel.TRIVIAL)
public void End_account_management_testcases() 
	{	
	
	System.out.println("************************END ACCOUNT_MANAGEMENT TESTCASES***********************************");

	}
         	
//*******************************************************************************************************************************************************


}
