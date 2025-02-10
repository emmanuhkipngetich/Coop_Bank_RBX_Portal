package USSD;

import static org.junit.Assert.assertTrue;

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

import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Features;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
public class A_Login {
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
    	
    	
    	@Attachment
        public byte[] captureScreenshot(WebDriver driver)
        {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
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
  	testng.setTestClasses(new Class[] {A_Login.class});
 	testng.run();
      	}

@Test(priority = 0)
@Epic("USSD LOGIN")
@Features(value = { @Feature(value = "LOGIN") })
@Step ("LOGIN FUNCTIONALITIES TESTCASES")
@Description ("LOGIN FUNCTIONALITIES TESTCASES")
@Severity(SeverityLevel.TRIVIAL)
public void Starting_login_functionalities_Test_cases() 
	{	
	
	System.out.println("************************LOGIN FUNCTIONALITIES TESTCASES***********************************");

	}
//*******************************************************************************************************************************************************
 @Test (priority=1)
@Epic("USSD LOGIN")
@Features(value = { @Feature(value = "LOGIN") })
@Step ("Check behavior of system when user exhausts number of trials on answering security questions-if entered answers are wrong")
 @Description ("Check behavior of system when user exhausts number of trials on answering security questions-if entered answers are wrong")
@Severity(SeverityLevel.BLOCKER)
public void Check_behavior_of_system_when_user_exhausts_number_of_trials_on_answering_security_questions_if_entered_answers_are_wrong() throws InterruptedException, IOException
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
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	String currentURL = driver.getCurrentUrl();
	sheet = USSD.getSheet("A_Login");
	String WRONG_PIN = formatter.formatCellValue(sheet.getRow(1).getCell(1));
	String USSD_URL1= currentURL+ WRONG_PIN;         
  	driver.get(USSD_URL1);
  	String WRONG_PIN2 = formatter.formatCellValue(sheet.getRow(1).getCell(2));
  	String USSD_URL2= currentURL+ WRONG_PIN + "*"+ WRONG_PIN2;        
  	driver.get(USSD_URL2);
  	String WRONG_PIN3 = formatter.formatCellValue(sheet.getRow(1).getCell(3));
  	String USSD_URL3= currentURL+ WRONG_PIN +"*"+ WRONG_PIN +"*"+ WRONG_PIN3;        
  	driver.get(USSD_URL3);
	System.out.println("TC1 Check behavior of system when user exhausts number of trials on answering security questions-if entered answers are wrong|Success:");
}
//*******************************************************************************************************************************************************
@Test (priority=2)
@Epic("USSD LOGIN")
@Features(value = { @Feature(value = "LOGIN") })
@Step ("Verify that customer can access Omni service by dialing 557 and entering correct password")
@Description ("Verify that customer can access Omni service by dialing 557 and entering correct password")
@Severity(SeverityLevel.CRITICAL)
public void Verify_that_customer_can_access_Omni_service_by_dialing() throws InterruptedException, IOException
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
          	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
          	String currentURL = driver.getCurrentUrl();
          	sheet = USSD.getSheet("A_Login");
        	String CORRECT_PIN = formatter.formatCellValue(sheet.getRow(1).getCell(5));
        	String USSD_URL1= currentURL+"*"+ CORRECT_PIN;         
          	driver.get(USSD_URL1);          	       	       	
          	assertTrue(driver.getPageSource().contains("Account Enquiry"));          
          	assertTrue(driver.getPageSource().contains("Send Money"));          
          	assertTrue(driver.getPageSource().contains("Payments"));          
          	assertTrue(driver.getPageSource().contains("Buy Airtime"));          
          	assertTrue(driver.getPageSource().contains("E-Loans"));          	
          	assertTrue(driver.getPageSource().contains("Buy Goods"));          	
          	assertTrue(driver.getPageSource().contains("Withdraw Cash"));          	
          	assertTrue(driver.getPageSource().contains("Self Service"));          	
          	assertTrue(driver.getPageSource().contains("Exit"));
          	System.out.println("TC2 Verify that customer can access Omni service by dialing 557 and entering correct password |Success:");
	}
//*******************************************************************************************************************************************************
@Test (priority=3)
@Epic("USSD LOGIN")
@Features(value = { @Feature(value = "LOGIN") })
@Step ("Verify that the application navigates to main page with modules for accessing Mbanking Services on successful login")
@Description ("Verify that the application navigates to main page with modules for accessing Mbanking Services on successful login")
@Severity(SeverityLevel.BLOCKER)
public void Verify_that_the_application_navigates_to_main_page_with_modules_for_accessing_Mbanking_Services_on_successful_login() throws InterruptedException, IOException
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
          	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
          	String currentURL = driver.getCurrentUrl();
          	sheet = USSD.getSheet("A_Login");
        	String CORRECT_PIN = formatter.formatCellValue(sheet.getRow(1).getCell(5));        	
        	String USSD_URL1= currentURL+ "*"+ CORRECT_PIN;         
          	driver.get(USSD_URL1);           	 
          	System.out.println("TC3 Verify that the application navigates to main page with modules for accessing Mbanking Services on successful login |Success:");
}
//*******************************************************************************************************************************************************

@Test (priority=3)
@Epic("USSD LOGIN")
@Features(value = { @Feature(value = "LOGIN") })
@Step ("Verify that an appropriate Error message is displayed when user enters a wrong confirmation PIN")
@Description ("Verify that an appropriate Error message is displayed when user enters a wrong confirmation PIN")
@Severity(SeverityLevel.BLOCKER)
public void Verify_that_an_appropriate_Error_message_is_displayed_when_user_enters_a_wrong_confirmation_PIN() throws InterruptedException, IOException
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
                driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
          	String currentURL = driver.getCurrentUrl();
          	sheet = USSD.getSheet("A_Login");
        	String WRONG_PIN = formatter.formatCellValue(sheet.getRow(1).getCell(1));
        	String USSD_URL1= currentURL+"*"+ WRONG_PIN;         
          	driver.get(USSD_URL1); 
              	assertTrue(driver.getPageSource().contains("CON Wrong PIN"));
          	System.out.println("TC3 Verify that the application navigates to main page with modules for accessing Mbanking Services on successful login |Success:");
}
//*******************************************************************************************************************************************************

@Test (priority=4)
@Epic("USSD LOGIN")
@Features(value = { @Feature(value = "LOGIN") })
@Step ("Verify that system prompts customer to change pin")
@Description ("Verify that system prompts customer to change pin")
@Severity(SeverityLevel.BLOCKER)
public void Verify_that_system_prompts_customer_to_change_pin() throws InterruptedException, IOException
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
          	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);          	
          	String currentURL = driver.getCurrentUrl();
          	sheet = USSD.getSheet("A_Login");
        	String CORRECT_PIN = formatter.formatCellValue(sheet.getRow(1).getCell(5));
          	String USSD_URL1= currentURL+ CORRECT_PIN;         
          	driver.get(USSD_URL1); 
          	String USSD_URL2= currentURL+ CORRECT_PIN+ "*8";
          	driver.get(USSD_URL2);       	
          	String USSD_URL3= currentURL+ CORRECT_PIN+  "*8*2";
          	driver.get(USSD_URL3);
        	String USSD_URL4= currentURL+ CORRECT_PIN+  "*8*2*1111";
          	driver.get(USSD_URL4);
        	String USSD_URL5= currentURL+ CORRECT_PIN+ "*8*2*2222";
          	driver.get(USSD_URL5);
        	String USSD_URL6= currentURL+ CORRECT_PIN+ "*8*2*2222";
          	driver.get(USSD_URL6);          	
          	System.out.println("TC4 Verify that system prompts customer to change pin |Success:");
	
}
//*******************************************************************************************************************************************************
@Test (priority=5)
@Epic("USSD LOGIN") 
@Features(value = { @Feature(value = "LOGIN") })
@Step ("Client should not login with invalid PIN")
@Description ("Client should not login with invalid PIN")
@Severity(SeverityLevel.BLOCKER)
public void Client_should_not_login_with_invalid_PIN() throws InterruptedException, IOException
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
         	driver.manage().window().maximize();
         	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
         	driver.get(USSD_URL);
         	String USSD_URL1= Environment+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*2121";
          	driver.get(USSD_URL1); 
          	assertTrue(driver.getPageSource().contains("CON Wrong PIN")); 
          	System.out.println("TC5 Client should not login with invalid PIN |Success:");
}
@Test (priority=6)
@Epic("USSD LOGIN")
@Features(value = { @Feature(value = "LOGIN") })
@Step ("Confirm that the user is blocked on entering wrong password more than 3 consecutive times")
@Description ("Confirm that the user is blocked on entering wrong password more than 3 consecutive times")
@Severity(SeverityLevel.BLOCKER)
public void Confirm_that_the_user_is_blocked_on_entering_wrong_password_more_than_3_consecutive_times() throws InterruptedException, IOException
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
  	sheet = USSD.getSheet("A_Login");
	String WRONG_PIN = formatter.formatCellValue(sheet.getRow(1).getCell(1));
	String USSD_URL1= Environment+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95*"+ WRONG_PIN;
	driver.get(USSD_URL1);
	assertTrue(driver.getPageSource().contains("CON Wrong PIN: 2 trial Remaining. Enter your Current 4-digit PIN"));
	sheet = USSD.getSheet("A_Login");
	String WRONG_PIN2 = formatter.formatCellValue(sheet.getRow(1).getCell(2));
	String USSD_URL2= Environment+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95*"+ WRONG_PIN + "*"+ WRONG_PIN2;
	driver.get(USSD_URL2); 
	assertTrue(driver.getPageSource().contains("CON Wrong PIN: 1 trials Remaining. Enter your Current 4-digit PIN")); 
	sheet = USSD.getSheet("A_Login");
	String WRONG_PIN3 = formatter.formatCellValue(sheet.getRow(1).getCell(3));
	String USSD_URL3= Environment+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95*"+ WRONG_PIN +"*"+ WRONG_PIN2 +"*"+ WRONG_PIN3;
	driver.get(USSD_URL3); 
	assertTrue(driver.getPageSource().contains("END Thank you for using MCo-op Cash. We are you")); 
  	System.out.println("TC6 Confirm that the user is blocked on entering wrong password more than 3 consecutive times |Success:");
	}
@Test(priority =7)
@Epic("USSD LOGIN")
@Features(value = { @Feature(value = "LOGIN") })
@Step ("TRANSFER TO MPESA TESTCASES")
@Description ("LOGIN FUNCTIONALITIES TESTCASES")
@Severity(SeverityLevel.TRIVIAL)
public void End_of_log_in_functionalities_Test_cases() 
	{	
	
	System.out.println("************************END OF LOGIN FUNCTIONALITIES TESTCASES***********************************");

	}

}