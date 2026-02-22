package testCases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

	
	@Test(groups={"Regression","Master"})
	public void verify_account_Registration()
	{
		logger.info("**** Starting TC001_AccountRegistrationTest ****");
		
		try {
		HomePage hp=new HomePage(driver); 
		
		hp.clickMyAccount();
		logger.info("Clicked on MyAccount Link");
		
		hp.clickRegister();
		logger.info("Clicked on Register Link");
		
		AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
		
		logger.info("Providing Customaer details..");
		regpage.setFName(randomString().toUpperCase());
		regpage.setLName(randomString().toUpperCase());
		regpage.setEMail(randomString()+"@gmail.com"); 		//randomly generated email 
		regpage.setTelephone(randomNumber());
		
		String password=randomAlphaNumeric();
		regpage.setPassword(password);
		regpage.setConfirmPWD(password);
		
		regpage.setPrivacyPolicy();
		regpage.clickContinue();
		
		logger.info("Validating expected message..");
		String confmsg=regpage.getConfirmationMsg();
		if(confmsg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("Test Failed..");
			logger.debug("Debug Logs..");
			Assert.assertTrue(false);
		}
		
		//Assert.assertEquals(confmsg, "Your Account Has Been Created!!!");  	//sometimes assertion doesn't work directly as in catech after assertion fails nothing will be exceuted and we don't get logs in log file so we use if else
		}
		catch(Exception e)
		{
			e.printStackTrace();
		    Assert.fail("Test failed due to exception: " + e.getMessage());
		}
	
		logger.info("**** Finished TC001_AccountRegistrationTest ****");	
	
	}
}
	

