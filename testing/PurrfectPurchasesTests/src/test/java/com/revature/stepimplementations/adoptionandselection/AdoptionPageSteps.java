package com.revature.stepimplementations.adoptionandselection;

import runners.Runner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.html5.SessionStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class AdoptionPageSteps {
    private String adoptionURL = Runner.webURL + "/PurrfectPurrchasesFrontEnd/adoption-checkout.html";
    WebStorage webStorage = (WebStorage) new Augmenter().augment(Runner.driver);
    SessionStorage sessionStorage = webStorage.getSessionStorage();


    @Given("customer is on Adoption page")
    public void customer_is_on_adoption_page() throws InterruptedException {
        Runner.driver.get(adoptionURL);
    }
    @Given("customer was not logged in")
    public void customer_was_not_logged_in() {
        String userId = sessionStorage.getItem("user_id");
    }
    @When("customer logs in")
    public void customer_logs_in() throws InterruptedException {
        Runner.wait.until(ExpectedConditions.visibilityOf(Runner.adoptionPage.loginOrSignupDiv));
        Runner.adoptionPage.outerLoginButton.click();
        Runner.wait.until(ExpectedConditions.visibilityOf(Runner.adoptionPage.innerLoginButton));
        Runner.adoptionPage.loginEmail.sendKeys("fake1@revature.com");
        Runner.adoptionPage.loginPassword.sendKeys("revature");
        Runner.adoptionPage.innerLoginButton.click();
        Runner.wait.until(ExpectedConditions.alertIsPresent());
        Runner.driver.switchTo().alert().accept();
    }
    @Then("login and register buttons disappear from page")
    public void login_and_register_buttons_disappear_from_page() throws InterruptedException {
        Runner.wait.until(ExpectedConditions.invisibilityOf(Runner.adoptionPage.loginOrSignupDiv));
        boolean collapsibleClosed = Runner.adoptionPage.loginOrSignupDiv.getAttribute("style").equals("display: none;");
        Assert.assertTrue(collapsibleClosed);
    }
    @Then("logout button becomes visible")
    public void logout_button_becomes_visible() {
        boolean logoutVisibile = Runner.adoptionPage.logoutLink.isDisplayed();
        Assert.assertTrue(logoutVisibile);
    }
    @Then("shipping information appears")
    public void shipping_information_appears() throws InterruptedException {

        boolean flag = true;
        try {
            Runner.wait.until(ExpectedConditions.visibilityOf(Runner.driver.findElement(By
                    .xpath("//div/div[3]/div[@id='shipping-info']"))));
        }
        catch(NoSuchElementException e) {
            flag = false;
        }
        Assert.assertTrue(flag);
    }

    @Given("customer is logged in")
    public void customer_is_logged_in() {
        Runner.wait.until(ExpectedConditions.visibilityOf(Runner.adoptionPage.loginOrSignupDiv));
        Runner.adoptionPage.outerLoginButton.click();
        Runner.wait.until(ExpectedConditions.visibilityOf(Runner.adoptionPage.innerLoginButton));
        Runner.adoptionPage.loginEmail.sendKeys("fake1@revature.com");
        Runner.adoptionPage.loginPassword.sendKeys("revature");
        Runner.adoptionPage.innerLoginButton.click();
        Runner.wait.until(ExpectedConditions.alertIsPresent());
        Runner.driver.switchTo().alert().accept();
    }
    @When("customer clicks log out")
    public void customer_clicks_log_out() {
        Runner.wait.until(ExpectedConditions.visibilityOf(Runner.adoptionPage.logoutLink));
        Runner.adoptionPage.logoutLink.click();
        Runner.wait.until(ExpectedConditions.alertIsPresent());
        Runner.driver.switchTo().alert().accept();
    }
    @Then("log in and register buttons become visible")
    public void log_in_and_register_buttons_become_visible() {
        boolean flag = true;
        try{
            Runner.wait.until(ExpectedConditions.visibilityOf(Runner.adoptionPage.loginOrSignupDiv));
        }
        catch (NoSuchElementException e){
            flag = false;
        }
        Assert.assertTrue(flag);
    }
    @Then("cart area should be empty")
    public void cart_area_should_be_empty() {
        boolean flag = false;
        try{
            Runner.wait.until(ExpectedConditions.visibilityOf(Runner.driver.findElement(By.className("cat-in-cart"))));
            flag = true;
        }
        catch (NoSuchElementException e){}
        Assert.assertFalse(flag);
    }
    @Then("shipping information disappears")
    public void shipping_information_disappears() throws InterruptedException {
        boolean flag = false;
        try{
            Runner.wait.until(ExpectedConditions.invisibilityOf(Runner.driver.findElement(By
                    .xpath("//div/div[3]/div[@id='shipping-info']"))));
            flag = true;
        }
        catch (TimeoutException e){}
        Assert.assertFalse(flag);
    }
    @When("there is no cat in the cart on the Adoption page")
    public void there_is_no_cat_in_the_cart_on_the_adoption_page() {
        boolean flag = false;
        try{
            Runner.wait.until(ExpectedConditions.visibilityOf(Runner.driver.findElement(By.className("cat-in-cart"))));
            flag = true;
        }
        catch (NoSuchElementException e){}
        Assert.assertFalse(flag);
    }
    @When("the user clicks on the PURCHASE NOW button")
    public void the_user_clicks_on_the_purchase_now_button() {
        Runner.adoptionPage.completePurchaseButton.click();
    }

    @Then("user should get an alert that there is no cat in their cart")
    public void user_should_get_an_alert_that_there_is_no_cat_in_their_cart() {
        Runner.wait.until(ExpectedConditions.alertIsPresent());
        String alertText = Runner.driver.switchTo().alert().getText();
        Runner.driver.switchTo().alert().accept();
        String expectedAlertText = "You have no cats in your cart! Add some before trying to checkout!";
        Assert.assertEquals(alertText,expectedAlertText);
    }

}
