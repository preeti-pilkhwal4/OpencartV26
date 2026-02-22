package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MyAccountPAge extends BasePage{
	
	public MyAccountPAge(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//h2[normalize-space()='My Account']")
	WebElement msgHeading;
	
	@FindBy(xpath="//a[@class='list-group-item' and text()='Logout']")
	WebElement lnkLogout;
	
	public Boolean isMyAccountPageExists()
	{
		try
		{
			return(msgHeading.isDisplayed());
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public void clickLogout()
	{
		wait.until(ExpectedConditions.elementToBeClickable(lnkLogout));

		lnkLogout.click();
	}
	

}
