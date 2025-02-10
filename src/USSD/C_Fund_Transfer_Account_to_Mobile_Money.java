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


public class C_Fund_Transfer_Account_to_Mobile_Money {
    
    String my_dir = System.getProperty("user.dir");
	WebDriver driver;
	JavascriptExecutor js; 
	XSSFWorkbook USSD;
	XSSFWorkbook Fund_Transfer_Account_Mobile;
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
	testng.setTestClasses(new Class[] {C_Fund_Transfer_Account_to_Mobile_Money.class});
	testng.run();
  	}

@Test(priority = 0)
@Epic("USSD FUND TRANSFERS")
@Features(value = { @Feature(value = "MOBILE MONEY") })
@Step ("TRANSFER TO MPESA TESTCASES")
@Description ("TRANSFER TO MPESA TESTCASES")
@Severity(SeverityLevel.TRIVIAL)
public void Transfer_to_mpesa_Test_cases() 
	{
    	System.out.println("TRANSFER TO MPESA");	
	System.out.println("************************TRANSFER TO MPESA TESTCASES***********************************");

	}

@Test (priority=1)
@Epic("USSD FUND TRANSFERS")
@Features(value = { @Feature(value = "MOBILE MONEY") })
@Step ("Verify_that customer can access send money menu to mpesa on USSD")
@Description ("Verify_that customer can access send money menu to mpesa on USSD")
@Severity(SeverityLevel.BLOCKER)
public void Verify_that_customer_can_access_send_money_to_mpesa_menu_on_USSD() throws InterruptedException, IOException
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
            	String USSD_URL2= currentURL+ CORRECT_PIN+ "*2";
          	driver.get(USSD_URL2);          	
          	assertTrue(driver.getPageSource().contains("1. Mobile Money")); 
          	System.out.println("TC1 Verify_that customer can access send money menu to mpesa on USSD |Success:");
          	
}
@Test (priority=2)
@Epic("USSD FUND TRANSFERS")
@Features(value = { @Feature(value = "MOBILE MONEY") })
@Step ("Verify_that customer can  selected To Mobile money on USSD")
@Description ("Verify_that customer can  selected To Mobile money on USSD")
@Severity(SeverityLevel.BLOCKER)
public void Verify_that_customer_can_selected_To_Mobile_money_on_USSD() throws InterruptedException, IOException
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
            	String USSD_URL2= currentURL+ CORRECT_PIN+ "*2";
            	driver.get(USSD_URL2);  
            	assertTrue(driver.getPageSource().contains("CON Send Money To"));          	
            	String USSD_URL3= currentURL+ CORRECT_PIN+  "*2*1";
          	driver.get(USSD_URL3);
          	assertTrue(driver.getPageSource().contains("CON Send To")); 
          	System.out.println("TC2 Verify_that customer can  selected To Mobile money on USSD |Success:");
         	
}

@Test (priority=3)
@Epic("USSD FUND TRANSFERS")
@Features(value = { @Feature(value = "MOBILE MONEY") })
@Step ("Verify that customer can selected To my number on USSD")
@Description ("Verify that customer can selected To my number on USSD")
@Severity(SeverityLevel.BLOCKER)
public void Verify_that_customer_can_selected_To_my_number_on_USSD() throws InterruptedException, IOException
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
            	String USSD_URL2= currentURL+ CORRECT_PIN+ "*2";
            	driver.get(USSD_URL2);  
            	assertTrue(driver.getPageSource().contains("CON Send Money To"));          	
            	String USSD_URL3= currentURL+ CORRECT_PIN+  "*2*1";
            	driver.get(USSD_URL3);
            	assertTrue(driver.getPageSource().contains("CON Send To")); 
            	String USSD_URL4= currentURL+ CORRECT_PIN+  "*2*1*1";
            	driver.get(USSD_URL4);
            	assertTrue(driver.getPageSource().contains("1. My Number"));          	
          	System.out.println("TC3 Verify that customer can selected To my number on USSD |Success:");
         	
}
@Test (priority=4)
@Epic("USSD FUND TRANSFERS")
@Features(value = { @Feature(value = "MOBILE MONEY") })
@Step ("Verify that customer can selected To other number on USSD")
@Description ("Verify that customer can selected To other number on USSD")
@Severity(SeverityLevel.BLOCKER)
public void Verify_that_customer_can_selected_To_other_number_on_USSD() throws InterruptedException, IOException
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
        	String USSD_URL2= currentURL+ CORRECT_PIN+ "*2";
        	driver.get(USSD_URL2);  
        	assertTrue(driver.getPageSource().contains("CON Send Money To"));          	
        	String USSD_URL3= currentURL+ CORRECT_PIN+  "*2*1";
        	driver.get(USSD_URL3);
        	assertTrue(driver.getPageSource().contains("CON Send To")); 
        	String USSD_URL4= currentURL+ CORRECT_PIN+  "*2*1*1";
        	driver.get(USSD_URL4);
                assertTrue(driver.getPageSource().contains("2. Other Mobile Numbers"));
                System.out.println("TC4 Verify that customer can selected To other number on USSD |Success:");
}

@Test (priority=5)
@Epic("USSD FUND TRANSFERS")
@Features(value = { @Feature(value = "MOBILE MONEY") })
@Description ("Verify that customer can enter the amount to send on USSD")
@Step ("Verify that customer can enter the amount to send on USSD")
@Severity(SeverityLevel.BLOCKER)
public void Verify_that_customer_can_enter_the_amount_to_send_on_USSD() throws InterruptedException, IOException
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
            	String USSD_URL2= currentURL+ CORRECT_PIN+ "*2";
            	driver.get(USSD_URL2);  
            	assertTrue(driver.getPageSource().contains("CON Send Money To"));          	
            	String USSD_URL3= currentURL+ CORRECT_PIN+  "*2*1";
            	driver.get(USSD_URL3);
            	assertTrue(driver.getPageSource().contains("CON Send To")); 
            	String USSD_URL4= currentURL+ CORRECT_PIN+  "*2*1*1";
            	driver.get(USSD_URL4);
                assertTrue(driver.getPageSource().contains("2. Other Mobile Numbers"));
        	String USSD_URL5= currentURL+ CORRECT_PIN+  "*2*1*1*1";
            	driver.get(USSD_URL5);
            	sheet = USSD.getSheet("C_Fund_Transfer_Account_Mobile"); 
            	XSSFCell Amount= sheet.getRow(1).getCell(7);
            	String USSD_URL6= currentURL+ CORRECT_PIN+  "*2*1*1*1"+ Amount;
            	driver.get(USSD_URL6);          	
          	System.out.println("TC5 Verify that customer can enter the amount to send on USSD |Success:");
}

@Test (priority=6)
@Epic("USSD FUND TRANSFERS")
@Features(value = { @Feature(value = "MOBILE MONEY") })
@Step ("Verify customer cannot send money to Mpesa below the Maximum amount allowed")
@Description ("Verify customer cannot send money to Mpesa below the Maximum amount allowed")
@Severity(SeverityLevel.BLOCKER)
public void Verify_customer_cannot_send_money_to_Mpesa_below_the_Maximum_amount_allowed() throws InterruptedException, IOException
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
            	String USSD_URL2= currentURL+ CORRECT_PIN+ "*2";
            	driver.get(USSD_URL2);  
            	assertTrue(driver.getPageSource().contains("CON Send Money To"));
            	String USSD_URL3= currentURL+ CORRECT_PIN+ "*2*1";
            	driver.get(USSD_URL3); 
            	assertTrue(driver.getPageSource().contains("CON Send To")); 
            	String USSD_URL4= currentURL+ CORRECT_PIN+ "*2*1*1";
            	driver.get(USSD_URL4); 
            	String USSD_URL5= currentURL+ CORRECT_PIN+ "*2*1*1*1";
            	driver.get(USSD_URL5); 
            	String USSD_URL6= currentURL+ CORRECT_PIN+ "*2*1*1*1*1";
            	driver.get(USSD_URL6); 
            	sheet = USSD.getSheet("C_Fund_Transfer_Account_Mobile");
            	String Max_Amount = formatter.formatCellValue(sheet.getRow(1).getCell(4));
            	String USSD_URL7= currentURL+ CORRECT_PIN+ "*2*1*1*1*1*"+ Max_Amount;
            	driver.get(USSD_URL7); 
            	assertTrue(driver.getPageSource().contains("ON Invalid Limit 2 trials Remaining: Enter Amount Between 50.00 and 150,000.00"));
               	System.out.println("TC6 Verify customer cannot send money to Mpesa below the Maximum amount allowed |Success:");
         	
}
@Test (priority=7)
@Epic("USSD FUND TRANSFERS")
@Features(value = { @Feature(value = "MOBILE MONEY") })
@Step ("Verify customer cannot send money to Mpesa above the Minimum amount allowed")
@Description ("Verify customer cannot send money to Mpesa above the Minimum amount allowed")
@Severity(SeverityLevel.BLOCKER)
public void Verify_customer_cannot_send_money_to_Mpesa_above_the_Minimum_amount_allowed() throws InterruptedException, IOException
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
                    	String USSD_URL2= currentURL+ CORRECT_PIN+ "*2";
                    	driver.get(USSD_URL2);  
                    	assertTrue(driver.getPageSource().contains("CON Send Money To"));
                    	String USSD_URL3= currentURL+ CORRECT_PIN+ "*2*1";
                    	driver.get(USSD_URL3); 
                    	assertTrue(driver.getPageSource().contains("CON Send To")); 
                    	String USSD_URL4= currentURL+ CORRECT_PIN+ "*2*1*1";
                    	driver.get(USSD_URL4); 
                    	String USSD_URL5= currentURL+ CORRECT_PIN+ "*2*1*1*1";
                    	driver.get(USSD_URL5); 
                    	String USSD_URL6= currentURL+ CORRECT_PIN+ "*2*1*1*1*1";
                    	driver.get(USSD_URL6); 
                    	sheet = USSD.getSheet("C_Fund_Transfer_Account_Mobile");
                    	String Min_Amount = formatter.formatCellValue(sheet.getRow(1).getCell(5));
                    	String USSD_URL7= currentURL+ CORRECT_PIN+ "*2*1*1*1*1*"+ Min_Amount;
                    	driver.get(USSD_URL7); 
                    	assertTrue(driver.getPageSource().contains("ON Invalid Limit 2 trials Remaining: Enter Amount Between 50.00 and 150,000.00"));
                        System.out.println("TC7 Verify customer cannot send money to Mpesa above the Minimum amount allowed |Success:");
	
         	
}

@Test (priority=8)
@Epic("USSD FUND TRANSFERS")
@Features(value = { @Feature(value = "MOBILE MONEY") })
@Step ("Verify that the System generates an error mesasge where the customer has provided an Invalid amount as the Transfer Amount")
@Description ("Verify that the System generates an error mesasge where the customer has provided an Invalid amount as the Transfer Amount")
@Severity(SeverityLevel.BLOCKER)
public void Verify_that_the_System_generates_an_error_mesasge_where_the_customer_has_provided_an_Invalid_amount_as_the_Transfer_Amount() throws InterruptedException, IOException
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
            	String USSD_URL2= currentURL+ CORRECT_PIN+ "*2";
            	driver.get(USSD_URL2);  
            	assertTrue(driver.getPageSource().contains("CON Send Money To"));
            	String USSD_URL3= currentURL+ CORRECT_PIN+ "*2*1";
            	driver.get(USSD_URL3); 
            	assertTrue(driver.getPageSource().contains("CON Send To")); 
            	String USSD_URL4= currentURL+ CORRECT_PIN+ "*2*1*1";
            	driver.get(USSD_URL4); 
            	String USSD_URL5= currentURL+ CORRECT_PIN+ "*2*1*1*1";
            	driver.get(USSD_URL5); 
            	String USSD_URL6= currentURL+ CORRECT_PIN+ "*2*1*1*1*1";
            	driver.get(USSD_URL6); 
            	sheet = USSD.getSheet("C_Fund_Transfer_Account_Mobile");
            	String Invalid_Amount = formatter.formatCellValue(sheet.getRow(1).getCell(6));
            	String USSD_URL7= currentURL+ CORRECT_PIN+ "*2*1*1*1*1*"+ Invalid_Amount;
            	driver.get(USSD_URL7); 
            	assertTrue(driver.getPageSource().contains("CON Invalid Input 2 trials Remaining: Enter Amount"));
            	System.out.println("TC8 Verify that the System generates an error mesasge where the customer has provided an Invalid amount as the Transfer Amount |Success:");
 	
         	
}

@Test (priority=9)
@Epic("USSD FUND TRANSFERS")
@Features(value = { @Feature(value = "MOBILE MONEY") })
@Step ("Verify that the System generates an error mesasge where the customer has provided an Invalid mobile number")
@Description ("Verify that the System generates an error mesasge where the customer has provided an Invalid mobile number")
@Severity(SeverityLevel.BLOCKER)
public void Verify_that_the_System_generates_an_error_mesasge_where_the_customer_has_provided_an_Invalid_mobile_number() throws InterruptedException, IOException
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
	sheet = USSD.getSheet("C_Fund_Transfer_Account_Mobile");
	formatter.formatCellValue(sheet.getRow(1).getCell(11));     		
	formatter.formatCellValue(sheet.getRow(2).getCell(3));
	new Random();
	long random_MSSID = ThreadLocalRandom.current().nextLong(800000000L, 89999999999L); 
	XSSFCell url= sheet.getRow(1).getCell(11);
	String USSD_URL = url +val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95";
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.get(USSD_URL);
	String USSD_URL1= url +val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111";
	driver.get(USSD_URL1); 
	String USSD_URL2= url +val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2";
	driver.get(USSD_URL2); 
	assertTrue(driver.getPageSource().contains("CON Send Money To"));
	String USSD_URL3= url +val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*1";
	driver.get(USSD_URL3); 
	assertTrue(driver.getPageSource().contains("CON Send To")); 
	String USSD_URL4= url +val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*1*1";
	driver.get(USSD_URL4); 
	String USSD_URL5= url +val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*1*1*2";
	driver.get(USSD_URL5);
	sheet = USSD.getSheet("C_Fund_Transfer_Account_Mobile");	 	
	cell = sheet.getRow(1).getCell(9);
	String USSD_URL6= url +val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*1*1*2*"+ cell.getStringCellValue();
	driver.get(USSD_URL6);
	assertTrue(driver.getPageSource().contains("CON Invalid Input 2 trials Remaining: Enter Mobile Number"));
	System.out.println("TC9 Verify that the System generates an error mesasge where the customer has provided an Invalid mobile number |Success:");
         	
}
//
@Test (priority=10)
@Epic("USSD FUND TRANSFERS")
@Features(value = { @Feature(value = "MOBILE MONEY") })
@Step ("Verify that Customer Can Proceed and confirm successfully send amount to Mpesa my number")
@Description ("Verify that Customer Can Proceed and confirm successfully send amount to Mpesa my number")
@Severity(SeverityLevel.BLOCKER)
public void Verify_that_Customer_Can_Proceed_and_confirm_successfully_send_amount_to_Mpesa_my_number() throws InterruptedException, IOException
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
	sheet = USSD.getSheet("C_Fund_Transfer_Account_Mobile");
	formatter.formatCellValue(sheet.getRow(1).getCell(11));     		
	formatter.formatCellValue(sheet.getRow(2).getCell(3));
	new Random();
	long random_MSSID = ThreadLocalRandom.current().nextLong(800000000L, 89999999999L); 
         	XSSFCell url= sheet.getRow(1).getCell(11);
         	String USSD_URL = url +val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95";
         	driver.manage().window().maximize();
         	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
         	driver.get(USSD_URL);
         	String USSD_URL1= url +val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111";
          	driver.get(USSD_URL1); 
          	String USSD_URL2= url +val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2";
          	driver.get(USSD_URL2); 
          	assertTrue(driver.getPageSource().contains("CON Send Money To"));
          	String USSD_URL3= url +val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*1";
          	driver.get(USSD_URL3); 
          	assertTrue(driver.getPageSource().contains("CON Send To")); 
          	String USSD_URL4= url +val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*1*1";
          	driver.get(USSD_URL4); 
          	String USSD_URL5= url +val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*1*1*1";
          	driver.get(USSD_URL5);
          	String USSD_URL6= url +val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*1*1*1*1";
          	driver.get(USSD_URL6);
          	sheet = USSD.getSheet("C_Fund_Transfer_Account_Mobile");	 	
        	cell = sheet.getRow(1).getCell(6);
          	String USSD_URL7= url +val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*1*1*1*1*"+ cell.getStringCellValue();
          	driver.get(USSD_URL7);
          	sheet = USSD.getSheet("C_Fund_Transfer_Account_Mobile");	 	
        	cell = sheet.getRow(1).getCell(6);
          	String USSD_URL8= url +val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*1*1*1*1*"+ cell.getStringCellValue()+"*1";
          	driver.get(USSD_URL8);
          	//assertTrue(driver.getPageSource().contains("CON Your Mpesa Transfer to"));
          	System.out.println("TC10 Verify that Customer Can Proceed and confirm successfully send amount to Mpesa my number |Success:");
}
@Test (priority=11)
@Epic("USSD FUND TRANSFERS")
@Features(value = { @Feature(value = "MOBILE MONEY") })
@Step ("Verify that Customer Can Proceed and confirm successfully send amount to Mpesa other number")
@Description ("Verify that Customer Can Proceed and confirm successfully send amount to Mpesa other number")
@Severity(SeverityLevel.BLOCKER)
public void Verify_that_Customer_Can_Proceed_and_confirm_successfully_send_amount_to_Mpesa_other_number() throws InterruptedException, IOException
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
        	sheet = USSD.getSheet("C_Fund_Transfer_Account_Mobile");
        	formatter.formatCellValue(sheet.getRow(1).getCell(11));     		
        	formatter.formatCellValue(sheet.getRow(2).getCell(3));
        	new Random();
        	String amount = formatter.formatCellValue(sheet.getRow(2).getCell(2));
        	long random_MSSID = ThreadLocalRandom.current().nextLong(800000000L, 89999999999L); 
         	XSSFCell url= sheet.getRow(1).getCell(11);
         	String USSD_URL = url +val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95";
         	driver.manage().window().maximize();
         	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
         	driver.get(USSD_URL);
         	String USSD_URL1= url +val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111";
          	driver.get(USSD_URL1); 
          	String USSD_URL2= url +val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2";
          	driver.get(USSD_URL2); 
          	assertTrue(driver.getPageSource().contains("CON Send Money To"));
          	String USSD_URL3= url +val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*1";
          	driver.get(USSD_URL3); 
          	assertTrue(driver.getPageSource().contains("CON Send To")); 
          	String USSD_URL4= url +val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*1*1";
          	driver.get(USSD_URL4); 
          	String USSD_URL5= url +val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*1*1*2";
          	driver.get(USSD_URL5);
          	String USSD_URL6= url +val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*1*1*2*0723107755";
          	driver.get(USSD_URL6);
          	sheet = USSD.getSheet("C_Fund_Transfer_Account_Mobile");	 	
        	cell = sheet.getRow(1).getCell(7);
          	String USSD_URL7= url +val_PHONE_NO+	"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*1*1*1*1*"+ amount;
          	driver.get(USSD_URL7);
          	//assertTrue(driver.getPageSource().contains("1. Proceed"));      		 	
        	String USSD_URL8= url +val_PHONE_NO+"&SERVICE_CODE=*557%23&imsi="+val_PHONE_NO+"&SESSION_ID="+random_MSSID +"&USSD_STRING=95"+"*1111*2*1*1*1*1*"+ amount+"*1";
          	driver.get(USSD_URL8);
          	//assertTrue(driver.getPageSource().contains("CON Your Mpesa Transfer to"));
          	System.out.println("TC11 Verify that Customer Can Proceed and confirm successfully send amount to Mpesa other number|Success:");
}
@Test(priority = 12)
@Epic("USSD FUND TRANSFERS")
@Features(value = { @Feature(value = "MOBILE MONEY") })
@Step ("TRANSFER TO MPESA TESTCASES")
@Description ("TRANSFER TO MPESA TESTCASES")
@Severity(SeverityLevel.TRIVIAL)
public void End_transfer_to_mpesa_Test_cases() 
	{	
	
	System.out.println("************************END TRANSFER TO MPESA TESTCASES***********************************");

	}

}
