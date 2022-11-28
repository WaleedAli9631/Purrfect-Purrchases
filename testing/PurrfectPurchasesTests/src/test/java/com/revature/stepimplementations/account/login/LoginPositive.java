package com.revature.stepimplementations.account.login;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import runners.Runner;

import java.time.Duration;

public class LoginPositive {
    @When("User clicks the login link")
    public void userClicksLogin() throws InterruptedException {
        Runner.wait.until(ExpectedConditions.visibilityOf(Runner.homePage.loginLink));
        Runner.homePage.loginLink.click();
    }

    @When("User clicks the login button")
    public void userClicksTheLoginButton() {
        Runner.wait.until(ExpectedConditions.visibilityOf(Runner.homePage.loginButton));

        Runner.homePage.loginButton.click();
    }

    @When("User inputs {string} into the password field")
    public void userInputsPasswordIntoThePasswordField(String s1) {
        Runner.wait.until(ExpectedConditions.visibilityOf(Runner.homePage.loginPassword));

        Runner.homePage.loginPassword.sendKeys(s1);
    }

    @Then("User should see the account page link")
    public void userShouldSeeTheAccountPageLink() {

        Runner.wait.until(ExpectedConditions.visibilityOf(Runner.homePage.accountLink));

    }

    @Then("User should see the logout button")
    public void userShouldSeeTheLogoutButton() {
        Runner.wait.until(ExpectedConditions.visibilityOf(Runner.homePage.logoutLink));
    }


    @When("User inputs {string} into the email field")
    public void userInputsUsernameIntoTheUsernameField(String email) {
        Runner.wait.until(ExpectedConditions.visibilityOf(Runner.homePage.loginEmail));

        Runner.homePage.loginEmail.sendKeys(email);
    }

    @Then("If user is an admin they should see the admin link {string} {string}")
    public void ifUserIsAnAdminTheyShouldSeeTheAdminLink(String email, String password) {
        if (email.equals("admin@admin.com")) {
            Assert.assertTrue(Runner.homePage.AdminLink.isDisplayed());

        }else {
            Assert.assertFalse(Runner.homePage.AdminLink.isDisplayed());
        }
    }
}
