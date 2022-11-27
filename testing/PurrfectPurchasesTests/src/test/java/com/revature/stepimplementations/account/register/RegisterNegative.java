package com.revature.stepimplementations.account.register;

import io.cucumber.java.en.Then;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import runners.Runner;

public class RegisterNegative {
    @Then("User should see an alert indicating something went wrong")
    public void userShouldSeeAnAlertIndicatingSomethingWentWrong() {
        Runner.wait.until(ExpectedConditions.alertIsPresent());
    }

    @Then("User should see a tooltip saying to fill the required field")
    public void userShouldSeeATooltipSayingToFillTheRequiredField() {
        boolean tooltipDisplayed = false;
        if (Runner.homePage.signupEmail.getAttribute("required") != null) {
            tooltipDisplayed = true;
        }
        if (Runner.homePage.signupPassword.getAttribute("required") != null) {
            tooltipDisplayed = true;
        }
        if (Runner.homePage.signupPasswordConfirm.getAttribute("required") != null) {
            tooltipDisplayed = true;
        }
        if (Runner.homePage.signupFname.getAttribute("required") != null) {
            tooltipDisplayed = true;
        }
        if (Runner.homePage.signupLname.getAttribute("required") != null) {
            tooltipDisplayed = true;
        }
        if (Runner.homePage.signupAddress.getAttribute("required") != null) {
            tooltipDisplayed = true;
        }
        if (Runner.homePage.signupCity.getAttribute("required") != null) {
            tooltipDisplayed = true;
        }
        if (Runner.homePage.signupState.getAttribute("required") != null) {
            tooltipDisplayed = true;
        }
        Assert.assertTrue(tooltipDisplayed);
    }
}
