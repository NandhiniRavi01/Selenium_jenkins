package selenium_jenkins;

import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Login_Function {
    ChromeDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new"); // more stable for newer Chrome versions
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

   @Test(priority = 1)
public void login() throws InterruptedException {
    driver.get("https://www.amazon.in/");
    
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href,'signin')]"))).click();

    // Wait for email field
    WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ap_email_login")));
   username.sendKeys("nandhiniravi1402@gmail.com");
		driver.findElement(By.xpath("//input[@class=\"a-button-input\"]")).click();
		WebElement password=driver.findElement(By.id("ap_password"));
		password.sendKeys("Nandhu@01");
		driver.findElement(By.id("signInSubmit")).click();
		Thread.sleep(3000);
}
   /* @Test(priority = 2)
    public void Searchelement() {
        WebElement search = wait.until(ExpectedConditions.elementToBeClickable(By.id("twotabsearchtextbox")));
        search.sendKeys("electric cycle");
        driver.findElement(By.id("nav-search-submit-button")).click();

        wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//span[contains(text(), 'EMotorad X2 Unisex Mountain Electric Cycle')]"))).click();

        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
    }

    @Test(priority = 3)
    public void AddCart() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='a-button a-button-dropdown']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("quantity_25"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-button"))).click();
    }

    @Test(priority = 4)
    public void GoCart() {
        wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//a[contains(@href, '/cart') and @class='a-button-text']"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.name("proceedToRetailCheckout"))).click();
    }

    @AfterClass
    public void CloseWebsite() {
        if (driver != null) {
            driver.quit();
        }
    }*/
}
