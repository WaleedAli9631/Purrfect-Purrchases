package com.revature.stepimplementations.navigation;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import runners.Runner;

import java.time.Duration;

public class Navigation {
    @When("User clicks off screen of the modal")
    public void userOffScreenOfTheModal() throws InterruptedException {
        Thread.sleep(Duration.ofSeconds(1));

        if(Runner.homePage.closeOutLoginModalButton.isDisplayed()){
            Runner.homePage.closeOutLoginModalButton.click();
        }
        if(Runner.homePage.closeOutSignupModalButton.isDisplayed()){
            Runner.homePage.closeOutSignupModalButton.click();
        }
    }

    @Then("The user should not see the modal")
    public void theUserShouldNotSeeTheModal() throws InterruptedException {
        Thread.sleep(Duration.ofSeconds(1));
        Assert.assertFalse(Runner.homePage.loginPassword.isDisplayed() || Runner.homePage.signupAddress.isDisplayed());
    }

    @When("User clicks the cart link")
    public void userClicksTheCartLink() {
        Runner.wait.until(ExpectedConditions.visibilityOf(Runner.homePage.cartLink));
        Runner.homePage.cartLink.click();
    }

    @When("User goes back")
    public void userGoesBack() {
        Runner.driver.navigate().back();
    }

    @Then("The user should be on the home page")
    public void theUserShouldBeOnTheHomePage() {
        Runner.wait.until(ExpectedConditions.visibilityOf(Runner.homePage.cartLink));
    }

    @When("User clicks the account link")
    public void userClicksTheAccountLink() {
        Runner.wait.until(ExpectedConditions.visibilityOf(Runner.homePage.accountLink));
        Runner.homePage.accountLink.click();
    }
}
