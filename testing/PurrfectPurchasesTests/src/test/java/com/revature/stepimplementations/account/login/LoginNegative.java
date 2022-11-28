package com.revature.stepimplementations.account.login;

import io.cucumber.java.en.Then;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import runners.Runner;

public class LoginNegative {
    @Then("User should see an alert saying wrong information")
    public void userShouldSeeAnAlertSayingWrongInformation() {
        Runner.wait.until(ExpectedConditions.alertIsPresent());
        String actualText = Runner.driver.switchTo().alert().getText();
        Assert.assertEquals(actualText, "Wrong login email or password");
    }
}
