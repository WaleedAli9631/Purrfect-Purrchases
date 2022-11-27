package runners;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.Admin;
import pages.Home;

import java.time.Duration;

@CucumberOptions(features="classpath:features", glue="com.revature.stepimplementations", tags = "@register11")
public class Runner extends AbstractTestNGCucumberTests {
    public static WebDriver driver;
    public static Home homePage;
    public static WebDriverWait wait;
    public static Admin adminPage;
    public static String webURL = "http://127.0.0.1:5500/frontend/PurrfectPurrchasesFrontEnd/index.html";

    @BeforeMethod
    public void setup() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(Runner.driver,Duration.ofSeconds(2));
        homePage = new Home(driver);
        adminPage = new Admin(driver);
        driver.manage().window().maximize();

    }

    @AfterMethod
    public void cleanup() {driver.quit();}
}