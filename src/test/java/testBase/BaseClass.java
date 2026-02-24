package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

public static WebDriver driver;
public Logger logger; 	//log4j
public Properties p;	

	@SuppressWarnings("deprecation")
	@BeforeClass(groups={"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws IOException, InterruptedException
	{
		//loading config.properties file
		FileReader file= new FileReader("./src/test/resources/config.properties");
		p=new Properties();
		p.load(file);
		
		logger=LogManager.getLogger(this.getClass());
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities cap=new DesiredCapabilities();
			
			//in this framework we are getting the OS and brpswer from xml file.
			//cap.setPlatform(Platform.LINUX); 	
			//cap.setBrowserName("chrome");
			
			if(os.equalsIgnoreCase("linux"))
			{
				cap.setPlatform(Platform.LINUX);
			}
			else if(os.equalsIgnoreCase("windows"))
			{
				cap.setPlatform(Platform.WIN11);
			}
			else
			{
				System.out.println("No matching OS");
				return;
			}
			
			//browser
			switch(br.toLowerCase())
			{
			case "chrome": cap.setBrowserName("chrome"); break;
			case "edge": cap.setBrowserName("MicrosoftEdge"); break;
			case "firefox": cap.setBrowserName("firefox"); break;
			default: System.out.println("No matching browser"); return;
			}
			
			driver=new RemoteWebDriver(new URL("http://10.57.51.128:4444"),cap);
			
		}
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			switch(br.toLowerCase()) 
			{
			case "chrome": //driver= new ChromeDriver(); break;
				 	{
				 		ChromeOptions options = new ChromeOptions();
				 	    options.addArguments("--headless=new");        // Run without GUI
			            options.addArguments("--no-sandbox");          // Required on Linux
			            options.addArguments("--disable-dev-shm-usage");// Avoid memory issues
			            driver = new ChromeDriver(options);
			            break;
				 	}
			case "edge": //driver= new EdgeDriver(); break;
				//for headless mode in jenkins
					{
						EdgeOptions options = new EdgeOptions();
						options.addArguments("--headless=new");        // Headless Edge
						driver = new EdgeDriver(options);
						break;
					 }
			case "firefox"://driver= new FirefoxDriver(); break;
				{
					FirefoxOptions options = new FirefoxOptions();
		            options.addArguments("--headless");            // Headless Firefox
		            driver = new FirefoxDriver(options);
		            break;
				}
			default: System.out.println("Invalid browser name..."); return;
			}
		}
		
				
		driver.manage().deleteAllCookies(); 	//deleting cookies
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(p.getProperty("appURL")); 		//reading values from config.properties
		driver.manage().window().maximize();
		
	}
	
	
		
		@AfterClass(groups={"Sanity","Regression","Master"})
		public void tearDown()
		{
			//if the driver is not initialized(like we saw a case in slenium grid)
			if(driver!=null)
			{
				driver.quit();
			}
		}
		
		//method for generating random alphabetic data for email and other details.
		@SuppressWarnings("deprecation")
		public String randomString()
		{
			String generatedstring=RandomStringUtils.randomAlphabetic(5);
			return generatedstring;
		 }
				
		//method for generating numeric random telephone.
		@SuppressWarnings("deprecation")
		public String randomNumber()
		{
			String generatednumber=RandomStringUtils.randomNumeric(10);
			return generatednumber;
		}
		
		//method for generating numeric random telephone.
			@SuppressWarnings("deprecation")
		public String randomAlphaNumeric()
		{
			String generatedstring=RandomStringUtils.randomAlphabetic(3);
			String generatednumber=RandomStringUtils.randomNumeric(3);
			return (generatedstring+"@"+generatednumber);
		}
			
		public String captureScreen(String tname) throws IOException
		{
			String timeStamp=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			
			TakesScreenshot takesScreenshot=(TakesScreenshot) driver;
			File sourceFile=takesScreenshot.getScreenshotAs(OutputType.FILE);
			
			String targetFilePath=System.getProperty("user.dir")+"/screenshots/"+tname+"_"+timeStamp+".png";
			File targetFile=new File(targetFilePath);
			
			sourceFile.renameTo(targetFile);
			return targetFilePath;
		
		}
}

