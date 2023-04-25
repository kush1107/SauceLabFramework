package saucelab.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import saucelab.qa.utils.TestUtil;
import saucelab.qa.utils.WebEventListener;



@SuppressWarnings("deprecation")
public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	 public static Logger logger;
	
	


	public  static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	
	
	public TestBase() {
		
		
		
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "\\src\\main\\java\\saucelab\\qa\\configs\\qa.properties");
		prop.load(ip);
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		logger = LogManager.getLogger(this.getClass());
       PropertyConfigurator.configure(System.getProperty("user.dir")+"\\src\\main\\java\\saucelab\\qa\\configs\\log4j.properties");

        logger.info("********** Getting Environment Properties ***************");
		
	}
	
	@SuppressWarnings("deprecation")
	public static void initialization()
	{
		logger.info("********** Launching  Browser  ***************");
		
		String browserName = prop.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("Chrome")){
			
			driver = new ChromeDriver(); 
		}
		else if(browserName.equalsIgnoreCase("Firefox")){
			
			driver = new FirefoxDriver(); 
		}
		else if(browserName.equalsIgnoreCase("Edge")){
			
			driver = new EdgeDriver(); 
		}
		
		else
		{
			System.out.println("Browser is not available");
		}
		
		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		
		
		
		
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.PAGE_LOAD_TIMEOUT));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICIT_WAIT));
		
		logger.info("********** Launching The website   ***************");
		driver.get(prop.getProperty("url"));
		
	}
		
}

