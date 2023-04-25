package saucelab.qa.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import saucelab.qa.base.TestBase;
import saucelab.qa.utils.TestUtil;

public class SauceDemo extends TestBase{
	
	WebDriverWait wait;


	@FindBy(xpath= "//input[@id='login-button']")
	public static WebElement login_btn;
	
	@FindBy(xpath= "//select[@class='product_sort_container']")
	public static WebElement select_lowtohigh;
	
	@FindBy(xpath= "//div[normalize-space()='Sauce Labs Onesie']")
	public static WebElement product1;
	
	@FindBy(xpath= "//button[@id='add-to-cart-sauce-labs-onesie']")
	public static WebElement product1_addtocart;
	
	@FindBy(xpath= "//div[normalize-space()='Sauce Labs Bike Light']")
	WebElement product2;
	@FindBy(xpath= "//button[@id='add-to-cart-sauce-labs-bike-light']")
	public static WebElement product2_addtocart;
	
	@FindBy(xpath= "//a//span[@class='shopping_cart_badge']")
	public static WebElement cart_count;
	
	
	@FindBy(xpath= "//a[@class='shopping_cart_link']")
	public static WebElement AddToCart_btn;
	
	@FindBy(xpath= "//div[normalize-space()='Sauce Labs Onesie']")
	public static WebElement detials_product1;
	
	@FindBy(xpath= "//div[normalize-space()='Sauce Labs Bike Light']")
	public static WebElement detials_product2;
	
	@FindBy(xpath= "//span[@class='title']")
	public static WebElement text_yourcart;
	
	@FindBy(id= "checkout")
	public static WebElement checkout_btn;
	
	@FindBy(id= "first-name")
	public static WebElement firstname;
	
	@FindBy(id= "last-name")
	public static WebElement lastname;
	
	@FindBy(id= "postal-code")
	public static WebElement zipcode;
	
	@FindBy(id= "continue")
	public static WebElement continue_btn;
	
	@FindBy(xpath= "//*[@class='summary_value_label'][1]")
	public static WebElement Payment_Information;
	
	@FindBy(xpath=  "//*[@class='summary_value_label'][2]")
	public static WebElement Shipping_Information;
	
	@FindBy(xpath=  "//*[@class='summary_subtotal_label']")
	public static WebElement item_Total;
	
	@FindBy(xpath=  "//*[@class='summary_tax_label']")
	public static WebElement Total_tax;
	
	@FindBy(css=  ".summary_info_label.summary_total_label")
	public static WebElement Total;
	
	@FindBy(id= "finish")
	public static WebElement finish_btn;
	
	
	@FindBy(xpath= "//span[contains(text(),'Checkout: Complete!')]")
	public static WebElement checkout_complete_msg;
	
	@FindBy(xpath= "//h2[normalize-space()='Thank you for your order!']")
	public static WebElement orderplace_msg;
	
	
	@FindBy(xpath= "//button[@id='react-burger-menu-btn']")
	public static WebElement menu;
	
	@FindBy(xpath= "//a[@id='logout_sidebar_link']")
	public static WebElement logout;
	

	public SauceDemo()
	{
		PageFactory.initElements(driver,this);
	}
	
	
	public void Select_LowToHigh()
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(8));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@class='product_sort_container']")));
		TestUtil.Dropdown_select(select_lowtohigh, "Price (low to high)");
	}
	 
	
	public void AddToCart()
	{
		String product1_name=product1.getText();
		product1_addtocart.click(); 
		
		System.out.println("The Product added to cart is:"+product1_name);
		
		String product2_name=product2.getText();		
		product2_addtocart.click();
		System.out.println("The Product added to cart is:"+product2_name);
		
		System.out.println("Number of product added to cart:"+cart_count.getText());
		
	}
	
	public void ClickOnCartButton()
	{   try {
		Thread.sleep(3000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		AddToCart_btn.click();
	}

	public boolean getDetails()
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='title']")));
		
		text_yourcart.isDisplayed();
		
		String p1 = product1.getText();
		String p2 = product2.getText();
		String detail_p1 = detials_product1.getText();
		String detail_p2 = detials_product2.getText();
		
		
		
		if(p1.equals(detail_p1) && p2.equals(detail_p2))
		{
			System.out.println("Products are verified by checkout page.");
			
			return true;
			
		}
		
		else
		{
			System.out.println("Product detials does not match.");
			return false;
		}
		
	}
	
	public void ClickOnCheckOut()
	{
		checkout_btn.click();
	}
	
	public void FillCheckOutDetails(String FirstName, String LastName,String ZipCode)
	{
		firstname.sendKeys(FirstName);
		lastname.sendKeys(LastName);
		zipcode.sendKeys(ZipCode);
		continue_btn.click();
		
	}
	
	public boolean CheckPaymentDetials()
	{
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='title']")));
		
		
		
		String paymentinfo = Payment_Information.getText();
		String shippinginfo = Shipping_Information.getText();
		String itemtotal = item_Total.getText();
		String totaltax = Total_tax.getText();
		String total = Total.getText();
		
		System.out.println("Information of Purchase & Total Amount Summary:");
		System.out.println(paymentinfo);
		System.out.println(shippinginfo);
		System.out.println(itemtotal);
		System.out.println(totaltax);
		System.out.println(total);
		
		double newitemtotal = TestUtil.getInt(itemtotal);
		double newtotaltax = TestUtil.getInt(totaltax);
		
		double newtotal = newitemtotal+newtotaltax;
		System.out.println("Calculated total product Price is:"+newtotal);
		double totalprice = TestUtil.getInt(total);
		
		if(newtotal==totalprice)
		{
			
			System.out.println("Total Price of Product are correct & verified");
			finish_btn.click();
			return true;
		}
		
		else
		{
			
			System.out.println("Total Price is wrong!! Something went wrong...");
			return false;
		}
			
	}
	
	public String checkSuccessMessage()
	{
		try {
			Thread.sleep(5000);
			checkout_complete_msg.isDisplayed();
			orderplace_msg.isDisplayed();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println(orderplace_msg.getText());
		
		return orderplace_msg.getText();
	}
	
	public void ClickOnLogout()
	{
		menu.click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logout.click();
		login_btn.isDisplayed();
	}
}
