package seleniumtest1;
import java.io.File;

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

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        String page = new File("webpage/login.html")
                .getAbsoluteFile()
                .toURI()
                .toString();

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