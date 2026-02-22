package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage{

	public LoginPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmail;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtpwd;
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement btnLogin;
	
	public void setEmail(String mail) {
		
		
        txtEmail.clear();
        txtEmail.sendKeys(mail);
		//txtEmail.sendKeys(mail);
	}
	
	public void setPWD(String pwd) {

		txtpwd.sendKeys(pwd);
	}
	
	public void clickLogin() {
		wait.until(ExpectedConditions.elementToBeClickable(btnLogin));

		btnLogin.click();
	}
}
