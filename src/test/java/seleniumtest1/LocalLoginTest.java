package seleniumtest1;
import java.io.File;
import org.openqa.selenium.chrome.ChromeOptions; 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LocalLoginTest {

    WebDriver driver;

    // 1. Setup method
    @BeforeMethod
    public void setUp() {

        // 1. Create ChromeOptions
        ChromeOptions options = new ChromeOptions();

        // 2. Enable headless mode
        options.addArguments("--headless=new");

        // 3. Disable sandbox
        options.addArguments("--no-sandbox");

        // 4. Disable shared memory issues
        options.addArguments("--disable-dev-shm-usage");

        // 5. Set browser window size
        options.addArguments("--window-size=1920,1080");

        // 6. Launch Chrome browser
        driver = new ChromeDriver(options);

        // 7. Get HTML file path
        String page = new File("webpage/login.html")
                .getAbsoluteFile()
                .toURI()
                .toString();

        // 8. Open the webpage
        driver.get(page);
    }

    // 2. Valid Login Test
    @Test
    public void validLoginTest() {

        driver.findElement(By.id("username")).sendKeys("student");

        driver.findElement(By.id("password")).sendKeys("12345");

        driver.findElement(By.id("loginButton")).click();

        String message = driver.findElement(By.id("message")).getText();

        System.out.println("Message: " + message);

        Assert.assertEquals(message, "Login Successful");
    }

    // 3. Invalid Login Test
    @Test
    public void invalidLoginTest() {

        driver.findElement(By.id("username")).sendKeys("wrong");

        driver.findElement(By.id("password")).sendKeys("wrong");

        driver.findElement(By.id("loginButton")).click();

        String message = driver.findElement(By.id("message")).getText();

        System.out.println("Message: " + message);

        Assert.assertEquals(message, "Invalid Username or Password");
    }

    // 4. Background Color Test
    @Test
    public void checkBackgroundColor() {

        String bgColor = driver.findElement(By.id("loginBox"))
                .getCssValue("background-color");

        System.out.println("Background Color: " + bgColor);

        Assert.assertEquals(bgColor, "rgba(173, 216, 230, 1)");
    }

    // 5. Teardown method
    @AfterMethod
    public void tearDown() {

        driver.quit();
    }
}