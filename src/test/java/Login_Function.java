package selenium_jenkins;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Login_Function {
	  ChromeDriver driver;

	    @BeforeClass
	    public void setUp() {
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	    }
	
	@Test(priority = 1)
	public  void login() throws InterruptedException, IOException {
		Properties properties=new Properties();
		FileInputStream file = new FileInputStream("/home/nandhu/eclipse-workspace/Selenium-automation/src/main/java/configurationProperties");

		properties.load(file);
		
		driver.get("https://www.amazon.in/");
		driver.findElement(By.xpath("//a[@href=\"https://www.amazon.in/ap/signin?openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.in%2F%3Fref_%3Dnav_ya_signin&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.assoc_handle=inflex&openid.mode=checkid_setup&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0\"]")).click();
		WebElement username=driver.findElement(By.id("ap_email_login"));
		username.sendKeys(properties.getProperty("username"));
		driver.findElement(By.xpath("//input[@class=\"a-button-input\"]")).click();
		WebElement password=driver.findElement(By.id("ap_password"));
		password.sendKeys(properties.getProperty("password"));
		driver.findElement(By.id("signInSubmit")).click();
		Thread.sleep(3000);
	}
		@Test(priority = 2)
		public  void Searchelement() throws InterruptedException {
			
		WebElement Search=driver.findElement(By.id("twotabsearchtextbox"));
		Search.sendKeys("electric cycle");
		driver.findElement(By.id("nav-search-submit-button")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[contains(text(), 'EMotorad X2 Unisex Mountain Electric Cycle')]")).click();
		
		 ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
		    driver.switchTo().window(tabs.get(1)); // Switch to product page tab
		    Thread.sleep(3000);
		
	}
		@Test(priority = 3)
		public  void AddCart() throws InterruptedException {
			
			
			driver.findElement(By.xpath("//span[@class=\"a-button a-button-dropdown\"]")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("quantity_25")).click();
			Thread.sleep(2000);
		driver.findElement(By.id("add-to-cart-button")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[contains(text(), 'EMotorad X2 Unisex Mountain Electric Cycle')]")).click();
		Thread.sleep(3000);
		
		
		
	}
		@Test(priority = 4)
		public  void GoCart() throws InterruptedException {
			
			driver.findElement(By.xpath("//a[contains(@href, \"/cart\") and @class=\"a-button-text\"]")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//input[@name=\"proceedToRetailCheckout\"]")).click();
			Thread.sleep(3000);
		}	
		 @AfterClass
		    public void CloseWebsite() {
		        driver.quit();
		    }
	
}


