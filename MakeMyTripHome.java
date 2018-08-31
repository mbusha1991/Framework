package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.Base.CommonMethods;

public class MakeMyTripHome extends CommonMethods{
	
	public MakeMyTripHome()
	{
		PageFactory.initElements(driver,this);		
	}
	
	@FindBy(xpath= "//*[text()='hotels']")
	WebElement Hotels;
	
	public MakeMyTripHotels NavigateToMakeMyTripHotelsPage()
	{
		click(Hotels);
		return new MakeMyTripHotels();
		
	}

}
