package com.revature.runners;
import com.revature.pages.*;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

@CucumberOptions(features="classpath:features", glue="com.revature.stepimplementations")
public class BasicRunner extends AbstractTestNGCucumberTests {
    public static WebDriver driver;
    public static Home homePage;

    public static Admin adminPage;

    public static String webURL = "http://127.0.0.1:5501";

    @BeforeMethod
    public void setup() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        homePage = new Home(driver);
        adminPage = new Admin(driver);
    }

    @AfterMethod
    public void cleanup() {driver.quit();}
}