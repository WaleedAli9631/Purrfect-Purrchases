package runners;


//import com.beust.ah.A;
import pages.Admin;
import pages.Home;
import pages.Adoption;
import com.revature.pages.AccountPage;
import com.revature.pages.Admin;
import com.revature.pages.Home;
import com.revature.pages.Adoption;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

@CucumberOptions(features="src/test/resources/features/adoptionandselection/cat-selection-and-adoption.feature",
        glue="stepimplementations.adoptionandselection", tags = "@Test6")
public class Runner extends AbstractTestNGCucumberTests {
    public static WebDriver driver;
    public static Home homePage;
    public static WebDriverWait wait;
    public static Admin adminPage;
    public static Adoption adoptionPage;
    public static String webURL = "http://127.0.0.1:5500";
    //admin id: 55xPney8EhbEMAfhAVTRhhY4XCo1
    public static AccountPage accountPage;
    @BeforeMethod
    public void setup() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(Runner.driver, Duration.ofSeconds(2));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        homePage = new Home(driver);
        adminPage = new Admin(driver);
        adoptionPage = new Adoption(driver);
        accountPage = new AccountPage(driver);
        driver.manage().window().maximize();

    }

    @AfterMethod
    public void cleanup() {
        driver.quit();
    }
}