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


public class I_Eloans 
{
    
    
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
	testng.setTestClasses(new Class[] {I_Eloans.class});
	testng.run();
  	}

@Test(priority = 0)
@Epic("E-LOANS")
@Features(value = { @Feature(value = "Loan Enquiry") })
@Step ("Loan Enquiry")
@Description ("Loan Enquiry")
@Severity(SeverityLevel.TRIVIAL)
public void Starting_Loan_Enquiry_Test_cases() 
	{	
	System.out.println("E-LOANS");
	System.out.println("***********************Loan Enquiry TESTCASES***********************************");

	}
//**********************************************************************************************************************************

@Test (priority=1)
@Epic("E-LOANS")
@Features(value = { @Feature(value = "Loan Enquiry") })
@Step ("Verify that the user can access E-loan via USSD")
@Description("Verify that the user can access E-loan via USSD")
@Severity(SeverityLevel.BLOCKER)
public void Verify_that_the_user_can_access_E_loan_via_USSD() throws InterruptedException, IOException
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
          	assertTrue(driver.getPageSource().contains("E-Loans"));
          	System.out.println("TC1: Verify that the user can access E-loan via USSD|Sucess");
          	
          	

	}
//**********************************************************************************************************************************
@Test (priority=2)
@Epic("E-LOANS")
@Features(value = { @Feature(value = "Loan Enquiry") })
@Step ("Verify that user can access loan Enquiry")
@Description("Verify that user can access loan Enquiry")
@Severity(SeverityLevel.BLOCKER)
public void Verify_that_user_can_access_loan_Enquiry() throws InterruptedException, IOException
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
          	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*5";
          	driver.get(USSD_URL2);
          	assertTrue(driver.getPageSource().contains("Check Loan Balance"));
          	System.out.println("TC2: Verify that user can access loan Enquiry |Sucess");
          	
          	

	}
//**********************************************************************************************************************************
@Test (priority=3)
@Epic("E-LOANS")
@Features(value = { @Feature(value = "Loan Enquiry") })
@Step ("Verify that user can view his/her loan balance using USSD")
@Description("Verify that user can view his/her loan balance using USSD")
@Severity(SeverityLevel.BLOCKER)
public void Verify_that_user_can_view_his_her_loan_balance_using_USSD() throws InterruptedException, IOException
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
          	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*5";
          	driver.get(USSD_URL2);
          	Thread.sleep(3000); 
          	assertTrue(driver.getPageSource().contains("Check Loan Balance"));
          	System.out.println("TC3: Verify that user can view his/her loan balance using USSD |Sucess");
          	
          	

	}
//**********************************************************************************************************************************
@Test (priority=4)
@Epic("E-LOANS")
@Features(value = { @Feature(value = "Loan Enquiry") })
@Step ("Verify that user can view his/her loan limit using USSD")
@Description("Verify that user can view his/her loan limit using USSD")
@Severity(SeverityLevel.BLOCKER)
public void Verify_that_user_can_view_his_her_loan_limit_using_USSD() throws InterruptedException, IOException
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
          	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*5";
          	driver.get(USSD_URL2);
          	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*5*1";
          	driver.get(USSD_URL3);
          	Thread.sleep(3000); 
          	assertTrue(driver.getPageSource().contains("CON Your Business Plus Loan Limit is KES 1,000,000.00 , Salary Loan Limit is KES 200,000.00"));
          	System.out.println("TC4: Verify that user can view his/her loan limit using USSD |Sucess");
          	
          	

	}
//**********************************************************************************************************************************
@Test (priority=5)
@Epic("E-LOANS")
@Features(value = { @Feature(value = "Loan Enquiry") })
@Step ("Verify that the correct loan limit is displayed-for Salary loan")
@Description("Verify that the correct loan limit is displayed-for Salary loan")
@Severity(SeverityLevel.BLOCKER)
public void Verify_that_the_correct_loan_limit_is_displayed_for_Salary_loan() throws InterruptedException, IOException
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
          	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*5";
          	driver.get(USSD_URL2);
          	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*5*1";
          	driver.get(USSD_URL3);
          	Thread.sleep(3000); 
          	assertTrue(driver.getPageSource().contains("Salary Loan Limit is KES 200,000.00"));
          	System.out.println("TC5: Verify that the correct loan limit is displayed-for Salary loan |Sucess");
          	
          	

	}
//**********************************************************************************************************************************
@Test (priority=6)
@Epic("E-LOANS")
@Features(value = { @Feature(value = "Loan Enquiry") })
@Step ("Verify that the correct loan limit is displayed-for Business plus loan")
@Description("Verify that the correct loan limit is displayed-for Business plus loan")
@Severity(SeverityLevel.BLOCKER)
public void Verify_that_the_correct_loan_limit_is_displayed_for_Business_plus_loan() throws InterruptedException, IOException
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
          	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*5";
          	driver.get(USSD_URL2);
          	String USSD_URL3= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*5*1";
          	driver.get(USSD_URL3);
          	Thread.sleep(3000); 
          	assertTrue(driver.getPageSource().contains("Business Plus Loan Limit is KES 1,000,000.00"));
          	System.out.println("TC6: Verify that the correct loan limit is displayed-for Business plus loan |Sucess");
          	
          	

	}
//**********************************************************************************************************************************
@Test (priority=7)
@Epic("E-LOANS")
@Features(value = { @Feature(value = "Loan Enquiry") })
@Step ("Verify that if the user does not have a loan balance, he is informed that 'You do not have an outstanding  loan balance.")
@Description("Verify that if the user does not have a loan balance, he is informed that 'You do not have an outstanding  loan balance.")
@Severity(SeverityLevel.BLOCKER)
public void Verify_that_if_the_user_does_not_have_a_loan_balance_he_is_informed_that_You_do_not_have_an_outstanding__loan_balance() throws InterruptedException, IOException
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
          	String USSD_URL2= "http://172.16.20.68:60007/USSDapi-0.0.1/ussdReceiver?MSISDN="+val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*5";
          	driver.get(USSD_URL2);
                assertTrue(driver.getPageSource().contains("Check Loan Balance"));
          	System.out.println("TC7: Verify that if the user does not have a loan balance, he is informed that 'You do not have an outstanding  loan balance.|Sucess");
          	
          	

	}
//**********************************************************************************************************************************
@Test(priority = 8)
@Epic("E-LOANS")
@Features(value = { @Feature(value = "Loan Enquiry") })
@Step ("Loan Enquiry")
@Description ("Loan Enquiry")
@Severity(SeverityLevel.TRIVIAL)
public void End_Loan_Enquiry_Test_cases() 
	{	
	System.out.println("E-LOANS");
	System.out.println("***********************End of Loan Enquiry TESTCASES***********************************");

	}
//**********************************************************************************************************************************



}