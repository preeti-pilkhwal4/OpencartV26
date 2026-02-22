package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AccountRegistrationPage extends BasePage {
	
	public AccountRegistrationPage(WebDriver driver) {
		
		super(driver);
	}

	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement FName;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement LName;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement email;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement telephone;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement pwd;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement pwdconfirm;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement chkdPolicy;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btnContinue;
	
	@FindBy(xpath="//h1[text()='Your Account Has Been Created!']")
	WebElement msgConfirmation;
	
	
	public void setFName(String fname)
	{
		wait.until(ExpectedConditions.visibilityOf(FName));
		FName.sendKeys(fname);
	}
	
	public void setLName(String lname)
	{
		wait.until(ExpectedConditions.visibilityOf(LName));
		LName.sendKeys(lname);
	}
	
	public void setEMail(String mail)
	{
		wait.until(ExpectedConditions.visibilityOf(email));
		email.sendKeys(mail);
	}
	
	public void setTelephone(String tel)
	{
		wait.until(ExpectedConditions.visibilityOf(email));
		telephone.sendKeys(tel);
	}
	
	public void setPassword(String password)
	{
		wait.until(ExpectedConditions.visibilityOf(pwd));
		pwd.sendKeys(password);
	}
	
	public void setConfirmPWD(String cpwd)
	{
		wait.until(ExpectedConditions.visibilityOf(pwdconfirm));
		pwdconfirm.sendKeys(cpwd);
	}
	
	public void setPrivacyPolicy()
	{
		wait.until(ExpectedConditions.visibilityOf(chkdPolicy));
		chkdPolicy.click();
	}
	
	public void clickContinue()
	{
		//sol1
		btnContinue.click(); 	//sometimes it doesn't work so we have multiple alternatives.
		
		//sol2
		//btnContinue.submit(); 
		
		//sol3
		//Actions act=new Actions(driver);
		//act.moveToElement(btnContinue).click().perform();
		
		//sol4
		//JavascriptExecutor js=(JavascriptExecutor)driver;
		//js.executeScript("argument[0].click();",btnContinue);
		
		//sol5
		//WebDriverWait mywait= new WebDriverWait(driver,Duration.ofSeconds(10));
		//mywait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();
		
	}
	
	public String getConfirmationMsg() {
		
		try {
			return(msgConfirmation.getText());
		}
		catch(Exception e)
		{
			return(e.getMessage());
		}
	}
	
	
}
