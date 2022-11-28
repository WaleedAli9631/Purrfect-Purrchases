package com.revature.stepimplementations.navigation;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.testng.Assert;
import runners.Runner;

import java.time.Duration;

public class Navigation {
    @When("User clicks off screen of the modal")
    public void userOffScreenOfTheModal() {
        Runner.driver.findElement(By.xpath("//body")).click();
    }

    @Then("The user should not see the modal")
    public void theUserShouldNotSeeTheModal() throws InterruptedException {
        Thread.sleep(Duration.ofSeconds(1));
        Assert.assertFalse(Runner.driver.findElements(By.id("login-email")).size()>0 || Runner.driver.findElements(By.id("signupEmail")).size()>0);
    }

    @When("User clicks the cart link")
    public void userClicksTheCartLink() {
    }

    @When("User goes back")
    public void userGoesBack() {
    }

    @Then("The user should be on the home page")
    public void theUserShouldBeOnTheHomePage() {
    }

    @When("User clicks the account link")
    public void userClicksTheAccountLink() {
    }
}
