package com.revature.runners;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class Runner {
    public static WebDriverWait wait;
    public static Actions action;
    public static WebDriver driver;
    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        wait = new WebDriverWait(Runner.driver, Duration.ofSeconds(2));

        action = new Actions(driver);

//        loginPage = new LoginPage(driver);
//        homePage = new HomePage(driver);
//        matricesPage = new MatricesPage(driver);
//        testCasePage = new TestCasePage(driver);
//        testCaseEditor = new TestCaseEditor(driver);
//        defectReporterPage = new DefectReporterPage(driver);
    }

    @AfterMethod
    public void cleanup() {
        driver.quit();
    }
}
