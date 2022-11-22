package com.revature.stepimplementations;

import com.revature.runners.Runner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

import static com.revature.runners.Runner.driver;

public class SearchCats {

    @Given("User clicks on Admin link")
    public void user_clicks_on_admin_link() throws InterruptedException {
        Runner.homePage.AdminLink.click();
        Thread.sleep(2000);
    }
    @When("User clicks Search Cat button")
    public void user_clicks_search_cat_button() throws InterruptedException {
        Runner.adminPage.searchCatButton.click();
        Thread.sleep(1000);
    }

    @When("User inputs {int} into search field")
    public void user_inputs_into_search_field(Integer int1) {
        Runner.adminPage.searchCatID.sendKeys(String.valueOf(int1));
    }

    @When("User clicks Search button")
    public void user_clicks_search_button() throws InterruptedException {
        Runner.adminPage.searchCatButtonSubmit.click();
        Thread.sleep(300);
    }

    @Then("User should see {int} information in table or be alerted")
    public void user_should_see_information_in_table_or_be_alerted(Integer int1) {
        Boolean isPresent = driver.findElements(By.xpath("//td[1][text()='"+int1 + "']")).size()>0;
        Assert.assertEquals(isPresent, true, "Error: Cat search yielded no results!");
    }
}
