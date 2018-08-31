package com.qa.Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;


import org.apache.commons.io.FileUtils;
import org.apache.log4j.PropertyConfigurator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.util.Util;

public class TestBase {
	
	//WebDriver object is created as a global variable because the scope remains for the whole project(accessed by test case).
		//Properties object is created as a global variable because the scope remains for the whole project(accessed by test case).
		//only one object is created, no object is created until is goes to null. no other class can modify this driver 
		public static WebDriver driver;
		public static Properties prop;
		public static String workingDir;
		public  static EventFiringWebDriver e_driver;
		public static WebEventListner eventListener;
		
		public TestBase()
		{
			try
			{
				//create object of properties
				 prop = new Properties();
				 
				 workingDir = System.getProperty("user.dir"); 
				
				//read  the file(pass the data.properties file location) and throws FileNotFoundException
				FileInputStream fis = new FileInputStream( workingDir + "\\main\\com\\qa\\config\\ApplicationConfig.properties");
				
				//load data.properties file
				prop.load(fis);
				
			}
			catch(Exception e)
			{
				
			}
		}
		
		//browser opening based on data.properties
		public static WebDriver intializeBrowser() throws IOException
		{
				
				String browserName = prop.getProperty("browser");
				
				//Dont use == returns true when both object points to the same memory location
				//equals is used to compare the values of the objects
				if(browserName.equals("chrome"))
				{
					System.setProperty("webdriver.chrome.driver", workingDir + "\\resorces\\browserDrivers\\chromedriver.exe");
					driver = new ChromeDriver();
					
					//If you create WebDriver obj here, it scope is only inside this if
					
				}
				else if(browserName.equals("firefox"))
				{
					System.setProperty("webdriver.firefox.marionette",workingDir + "\\resorces\\browserDrivers\\geckodriver.exe");
					 driver = new FirefoxDriver();
				}
				
			
				e_driver = new EventFiringWebDriver(driver);
				// Now create object of EventListerHandler to register it with EventFiringWebDriver
				eventListener = new WebEventListner();
				e_driver.register(eventListener);
		        driver = e_driver;
				
				//the amount of time to wait for a page load to complete before throwing an error
				driver.manage().timeouts().pageLoadTimeout(Util.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
				driver.manage().deleteAllCookies();			
				 //impilict wait is applicable to all the findElement in the test case(project) before throwing the error
				driver.manage().timeouts().implicitlyWait(Util.IMPLICIT_WAIT,TimeUnit.SECONDS);
				
				//Open the url
				driver.get(prop.getProperty("url1"));
				
				driver.manage().window().maximize();
	
				return driver;
		}
		
		public void getScreenshot() throws IOException
		{
			File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(src,new File(workingDir + "/ScreenShots/" + System.currentTimeMillis() +".png"));
		}
		
		public static Logger createLogger()  {
			Logger aLogger = LogManager.getRootLogger();
			aLogger.debug("\n" + "Inside createLogger - Logger is being set up. New test setup beginning.");
			StringBuffer verificationErrors = new StringBuffer();
			aLogger.info("Logger has been set up.");
			return aLogger;
		}

		/*protected static void createLogFile (FirefoxProfile fp) throws Exception {
			File outfile = new File(outfileName);
			if (!outfile.exists())  
				outfile.createNewFile();
			fp.setPreference("webdriver.log.driver", "DEBUG");
			fp.setPreference("webdriver.log.file", outfileName);
		}*/
}
