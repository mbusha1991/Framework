package com.qa.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.Base.CommonMethods;

public class MakeMyTripLogin extends CommonMethods{
	
	
	public MakeMyTripLogin()
	{
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}

	//
	@FindBy(xpath = "//*[text() ='sign-up']")
	@CacheLookup
	WebElement signUp;
	
	
	@FindBy(id = "ch_signup_email")
	WebElement email;
	
	@FindBy(xpath ="//input[@id='ch_signup_phone']")
	WebElement mobileNo;
	
	@FindBy(css= "input#ch_signup_password")
	WebElement password;
	
	@FindBy(xpath ="//button[text()='SIGN UP']")
	WebElement login;
	
	
   public void Login(String emailid, String mobile,String pass_word)
   {
	  
	   click(signUp);
	   SendKeys(email,emailid);
	   SendKeys(mobileNo,mobile);
	   SendKeys(password,pass_word);
	   click(login);	   
   }

}
