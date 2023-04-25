package saurcelab.qa.tests;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import saucelab.qa.base.TestBase;
import saucelab.qa.pages.SauceDemo;
import saucelab.qa.utils.TestUtil;

public class SauceDemoTest extends TestBase {
	
	SauceDemo saucedemo;
	 Logger logger;
	
	
	
	
	public SauceDemoTest()	
	{
		super();
	}
	
	@BeforeClass
	public void setup()
	{
		initialization();
		saucedemo = new SauceDemo();
		 
		 logger= LogManager.getLogger(this.getClass());

		logger.info("********** Execution of Setup method  ***************");
	}
	
	@AfterClass
	public void teardown()
	{
		driver.quit();
		logger.info("********** Closing the browser  ***************");
	}
	
	
	@DataProvider  //For User Login
	public Object[][] getUserData(){
		Object data[][] = TestUtil.getTestData("UserLogin");
		return data;
		
	}
    
	@DataProvider  //For Checkout details
	public Object[][] getCheckoutDetials(){
		Object data[][] = TestUtil.getTestData("CheckoutDetials");
		return data;
		
	}
    
  
	
	@Test(priority=1,dataProvider = "getUserData")
	public void Verify_UserLogin(String UserName,String Password)
	{
		
		TestUtil.ValidateUserLogin(UserName, Password);
		TestUtil.takeScreenshotOfTests();
		
		logger.info("********** UserLogin Process  ***************");
	}
	
	@Test(priority=2)
	public void VerifySelect_low_to_high()
	{
		saucedemo.Select_LowToHigh();	
		TestUtil.takeScreenshotOfTests();
		
		logger.info("********** Selection of Low to High  ***************");
	}
	
	@Test(priority=3)
	public void Verify_Two_Products_AddedToCart()
	{
		saucedemo.AddToCart();
		Assert.assertEquals(SauceDemo.cart_count.getText(),"2","Verified Two product added to cart");
		TestUtil.takeScreenshotOfTests();
		
		logger.info("********** Added Products to cart ***************");
	}
	
	@Test(priority=4)
	public void Verify_Details()
	{
		saucedemo.ClickOnCartButton();
		saucedemo.getDetails();
		Assert.assertTrue(saucedemo.getDetails());
		saucedemo.ClickOnCheckOut();
		TestUtil.takeScreenshotOfTests();
		
		logger.info("********** Checked Detials of Products   ***************");
	}
	
	@Test(priority=5,dataProvider = "getCheckoutDetials")
	public void VerifyCheckoutDetails(String FirstName, String LastName,String ZipCode)
	{
		saucedemo.FillCheckOutDetails(FirstName,LastName,ZipCode);
		TestUtil.takeScreenshotOfTests();
		logger.info("********** Checked Checkout detials  ***************");
	}
	
	@Test(priority=6)
	public void VerifyPaymentInfo()
	{
		
		Assert.assertTrue(saucedemo.CheckPaymentDetials());
		TestUtil.takeScreenshotOfTests();
		
		logger.info("********** Checked Payment Info  ***************");
	}
	
	@Test(priority=7)
	public void VerifyOrderPlaced()
	{
		Assert.assertEquals(saucedemo.checkSuccessMessage(),"Thank you for your order!");
		TestUtil.takeScreenshotOfTests();
		logger.info("********** OrderPlaced Successfully  ***************");
		
		
	}
	
	@Test(priority=8)
	public void VerifyLogout()
	{
		saucedemo.ClickOnLogout();
		TestUtil.takeScreenshotOfTests();
		
		logger.info("********** User is logout   ***************");
	}

	
}
