package com.revature.stepimplementations.catmanage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import runners.Runner;

import static runners.Runner.driver;

public class SearchCats {
    @Given("User clicks Login Link")
    public void user_clicks_login_link() {
        Runner.homePage.loginLink.click();
    }

    @Given("User signs in as Admin")
    public void user_signs_in_as_admin() {
        Runner.homePage.loginEmail.sendKeys("admin@admin.com");
        Runner.homePage.loginPassword.sendKeys("admin123");
        Runner.homePage.loginButton.click();
    }

    @Given("User clicks on Admin link")
    public void user_clicks_on_admin_link() throws InterruptedException {
        Runner.homePage.AdminLink.click();
        //Thread.sleep(2000);
    }

    @When("User clicks Search Cat button")
    public void user_clicks_search_cat_button() throws InterruptedException {
        Runner.adminPage.searchCatButton.click();
        //Thread.sleep(1000);
    }

    @When("User inputs {int} into search field")
    public void user_inputs_into_search_field(Integer int1) {
        Runner.adminPage.searchCatID.sendKeys(String.valueOf(int1));
    }

    @When("User clicks Search button")
    public void user_clicks_search_button() throws InterruptedException {
        Runner.adminPage.searchCatButtonSubmit.click();
        Thread.sleep(300); //needed
    }

    @Then("User should see {int} information in table or be alerted")
    public void user_should_see_information_in_table_or_be_alerted(Integer int1) {
        boolean isPresent = true;
        try {
            Runner.wait.until(ExpectedConditions.alertIsPresent());
            String conf = "We could not find a cat with that ID";
            isPresent = driver.switchTo().alert().getText().equals(conf);
            Assert.assertEquals(isPresent, false, "Error: That ID could not be found!");
        } catch (Exception e) {
            isPresent = driver.findElements(By.xpath("//tr/td[position() = 1 and text()='" + int1 + "']")).size() > 0;
            Assert.assertEquals(isPresent, true, "Error: Cat search yielded no results!");
        }
    }
}
