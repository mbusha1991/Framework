package com.qa.testCases;

import org.testng.annotations.Test;

import com.qa.Base.TestBase;
import com.qa.pages.MakeMyTripHome;

import com.qa.pages.MakeMyTripHotels;
import com.qa.pages.MakeMyTripLogin;
import com.qa.testData.DataDrivenExcel;

import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.PropertyConfigurator;
import org.apache.logging.log4j.*;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;

public class LoginTest extends TestBase
{
	
	 MakeMyTripLogin login;
	 private static final Logger log = LogManager.getLogger(LoginTest.class);
	 

	
	 //Constructor
	 public LoginTest() throws IOException
	 {
		 super();
		 	 
	 }
	 
	  @BeforeMethod
	  public void beforeMethod() throws IOException 
	  {
		  log.info("Initialization");
		   intializeBrowser();
		   login = new MakeMyTripLogin();
	  }

	  
	  @Test(priority = 2)
	  public void First()
	  {
		  log.debug("Before login");
		  MakeMyTripHome homes = new MakeMyTripHome();
		  log.fatal("fatal");
		  MakeMyTripHotels	hotel = homes.NavigateToMakeMyTripHotelsPage();
		  hotel.SearchHotel("Goa");
		  log.warn("warning");
	  }
	  
	  
	  //this will be executed 1nd as priority is 1.
	  //only this test case will execute as per the number of rows in excel. before each test case execution beforeMethod is executed
	  //after each test case execution afterMethod is executed 
	  @Test(dataProvider = "getData",priority =1 , enabled = false)
	  public void Login(String email, String mobile,String password)
	  {
		  log.debug("Before login");
		  login.Login(email,mobile,password );
		  log.fatal("fatal");
	  }
	  
	  @AfterMethod
	  public void afterMethod() 
	  {
		  log.error("tear down");
		 driver.quit();
	/*	 driver = null;
		 prop = null;*/
	  }


  @DataProvider(name = "getData")
  public Object[][] getTestData() throws IOException, EncryptedDocumentException, InvalidFormatException
  {
	   /* return new Object[][] {
	      new Object[] { "mb.usha1991@gmail.com", "8050624820","Welcome@123" },
	   //   new Object[] { 2, "b" },
	    };*/
	/*  String name = this.getClass().getName();
	  String[] split = name.split(".");
	  int length =split.length;*/
	  return DataDrivenExcel.ReadValues("LoginTest");
  }
}
