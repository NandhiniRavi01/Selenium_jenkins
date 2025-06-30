package selenium_jenkins;

import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.time.Instant;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Login_Function {

    ChromeDriver driver;
    File tempProfile;

    @BeforeClass
    public void setUp() throws IOException {
        ChromeOptions options = new ChromeOptions();

        // ✅ Create unique temp profile directory
        String timestamp = String.valueOf(Instant.now().toEpochMilli());
        tempProfile = new File(System.getProperty("java.io.tmpdir"), "chrome-profile-" + timestamp);
        tempProfile.mkdirs(); // Ensure directory is created

        options.addArguments("--user-data-dir=" + tempProfile.getAbsolutePath());
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        // 🔄 Optional: enable headless mode for Jenkins with no GUI
        // options.addArguments("--headless=new", "--disable-gpu");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void login() throws InterruptedException {
        driver.get("https://www.amazon.in/");
        driver.findElement(By.xpath("//a[contains(@href,'signin')]")).click();
        Thread.sleep(2000);

        WebElement username = driver.findElement(By.id("ap_email"));
        username.sendKeys("nandhiniravi1402@gmail.com");

        driver.findElement(By.id("continue")).click();
        Thread.sleep(1000);

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
    }

    @Test(priority = 4)
    public void GoCart() throws InterruptedException {
        driver.findElement(By.id("nav-cart")).click();
        Thread.sleep(3000);
        driver.findElement(By.name("proceedToRetailCheckout")).click();
        Thread.sleep(3000);
    }

    @AfterClass
    public void CloseWebsite() throws IOException {
        if (driver != null) {
            driver.quit();
        }

        // 🧹 Clean up temp profile directory
        deleteDir(tempProfile);
    }

    // 🧽 Recursively delete the temporary directory
    private void deleteDir(File dir) {
        if (dir == null || !dir.exists()) return;
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                deleteDir(file);
            } else {
                file.delete();
            }
        }
        dir.delete();
    }
}
