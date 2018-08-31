package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.qa.Base.CommonMethods;
import com.qa.Base.TestBase;

public class MakeMyTripHotels extends CommonMethods{
	
	public MakeMyTripHotels()
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how =How.ID,using = "hp-widget__sDest")
	WebElement CityInput;
	
	@FindBy(how=How.ID, using ="searchBtn")
	WebElement searchbtn;
	
	public void SearchHotel(String city)
	{
		SendKeys(CityInput,city);
		click(searchbtn);
		
	}

}
