package pageObjects;

import java.time.Duration;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage{
	
		
	public HomePage(WebDriver driver)
	{
		super(driver);

	}

	
	@FindBy(xpath="//span[normalize-space()='My Account']")
	WebElement lnkMyAccount;
	
	@FindBy(xpath="//a[normalize-space()='Register']")
	WebElement lnkRegister;
	
	@FindBy(xpath="//a[normalize-space()='Login']")
	WebElement lnkLogin;
	
	
	public void clickMyAccount()
	{
		wait.until(ExpectedConditions.elementToBeClickable(lnkMyAccount));
		lnkMyAccount.click();
        
		
	}
	
	public void clickRegister()
	{
		wait.until(ExpectedConditions.elementToBeClickable(lnkRegister));
		lnkRegister.click();
		    
	}

		
	
	
	
	public void clickLogin()
	{
		wait.until(ExpectedConditions.elementToBeClickable(lnkLogin));

		lnkLogin.click();
	}
	
	
}
