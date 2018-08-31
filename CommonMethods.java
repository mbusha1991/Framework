package com.qa.Base;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CommonMethods extends TestBase {
	
	public void click(WebElement ele)
	{
		ele.click();
	}
	
	public void SendKeys(WebElement ele,String text)
	{
		ele.sendKeys(text);
	}
	
	public void SwitchToFrame(WebElement ele)
	{
		driver.switchTo().frame(ele);
	}


}
