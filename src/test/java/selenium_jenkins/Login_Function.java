package selenium_jenkins;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Login_Function {
    ChromeDriver driver;

    @BeforeClass
    public void setUp() {
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--headless"); // Run in headless mode
    options.addArguments("--no-sandbox"); // Required for some Linux environments
    options.addArguments("--disable-dev-shm-usage"); // Prevents crashes
    options.addArguments("--disable-gpu");
    options.addArguments("--remote-allow-origins=*");

    driver = new ChromeDriver(options);
    driver.manage().window().maximize();
}

    @Test(priority = 1)
    public void login() throws InterruptedException {
        driver.get("https://www.amazon.in/");
        driver.findElement(By.xpath("//a[contains(@href,'signin')]")).click();

        WebElement username = driver.findElement(By.id("ap_email"));
        username.sendKeys("nandhiniravi1402@gmail.com");
        driver.findElement(By.id("continue")).click();

        WebElement password = driver.findElement(By.id("ap_password"));
        password.sendKeys("Nandhu@01");
        driver.findElement(By.id("signInSubmit")).click();

        Thread.sleep(3000);
    }

    @Test(priority = 2)
    public void Searchelement() throws InterruptedException {
        WebElement search = driver.findElement(By.id("twotabsearchtextbox"));
        search.sendKeys("electric cycle");
        driver.findElement(By.id("nav-search-submit-button")).click();

        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[contains(text(), 'EMotorad X2 Unisex Mountain Electric Cycle')]")).click();

        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        Thread.sleep(3000);
    }

    @Test(priority = 3)
    public void AddCart() throws InterruptedException {
        driver.findElement(By.xpath("//span[@class='a-button a-button-dropdown']")).click();
        Thread.sleep(2000);

        driver.findElement(By.id("quantity_25")).click();
        Thread.sleep(2000);

        driver.findElement(By.id("add-to-cart-button")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//span[contains(text(), 'EMotorad X2 Unisex Mountain Electric Cycle')]")).click();
        Thread.sleep(3000);
    }

    @Test(priority = 4)
    public void GoCart() throws InterruptedException {
        driver.findElement(By.xpath("//a[contains(@href, '/cart') and @class='a-button-text']")).click();
        Thread.sleep(3000);

        driver.findElement(By.xpath("//input[@name='proceedToRetailCheckout']")).click();
        Thread.sleep(3000);
    }

    @AfterClass
    public void CloseWebsite() {
        driver.quit();
    }
}
