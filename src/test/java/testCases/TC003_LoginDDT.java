package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPAge;
import testBase.BaseClass;
import utilities.DataProviders;

/*Data is valid -login success -test pass -logout
 *Data is valid -login failed -test fail
 * 
 *Data is invalid -login success -test fail -logout
 *Data is valid -login failed -test pass
 */
public class TC003_LoginDDT extends BaseClass{

	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups="dataDriven")   //getting the dataprovider class from another package
	public void verify_loginDDT(String email,String pwd,String exp)
	{
		logger.info("****TC003_LoginDDT started****");
		try 
		{
		//Homepage
		HomePage hp= new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//Login page
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(email);
		lp.setPWD(pwd);	
		lp.clickLogin();
		
		//Myaccount
		MyAccountPAge macc=new MyAccountPAge(driver);
		boolean targetPage=macc.isMyAccountPageExists();
		
		if(exp.equalsIgnoreCase("Valid"))
		{
			if(targetPage==true)
			{
				macc.clickLogout();
				Assert.assertTrue(true); 		//assert should come after every statement as after assertion no statement is executed
				
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		if(exp.equalsIgnoreCase("Invalid"))
		{
			if(targetPage==true)
			{
				macc.clickLogout();
				Assert.assertTrue(false); 		
				
			}
			else
			{
				Assert.assertTrue(true); 	
			}
		}
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("****Finished TC003_LoginDDT****");
	}
}
