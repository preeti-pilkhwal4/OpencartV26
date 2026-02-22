package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPAge;
import testBase.BaseClass;

public class LoginTest extends BaseClass{

	@Test(groups={"Sanity","Master"})
	public void verify_login()
	{
		logger.info("****Starting logintest****");
		
		try
		{
		HomePage hp= new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPWD(p.getProperty("password"));	
		lp.clickLogin();
		
		//Login
		MyAccountPAge macc=new MyAccountPAge(driver);
		boolean targetPage=macc.isMyAccountPageExists();
		
		//Assert.assertEquals(targetPage, true,"Login Failed");
		Assert.assertTrue(targetPage);
		
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("Finished Login Test");
	}
	
	
	
	
	
}
