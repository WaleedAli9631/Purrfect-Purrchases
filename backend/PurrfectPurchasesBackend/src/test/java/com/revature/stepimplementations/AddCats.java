package com.revature.stepimplementations;

import com.revature.runners.Runner;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.sql.SQLOutput;

import static com.revature.runners.Runner.driver;

public class AddCats {
    @When("User clicks Add Cat button")
    public void user_clicks_add_cat_button() throws InterruptedException {
        Runner.adminPage.OpenAddCatModalButton.click();
        Thread.sleep(500);
    }
    @When("User inputs {string} into Name field")
    public void user_inputs_into_name_field(String string) throws InterruptedException {
        Runner.adminPage.AddCatNameInput.sendKeys(string);
    }
    @When("User inputs {string} into Breed field")
    public void user_inputs_into_breed_field(String string) {
        Runner.adminPage.setBreedOption(string);

    }
    @When("User inputs {int} into Age field")
    public void user_inputs_into_age_field(Integer int1) {
        Runner.adminPage.setAgeOption(String.valueOf(int1));
    }
    @When("User inputs {string} into Gender field")
    public void user_inputs_into_gender_field(String string) {
        Runner.adminPage.setGenderOption(string);
    }
    @When("User inputs {string} into Color field")
    public void user_inputs_into_color_field(String string) {
        Runner.adminPage.AddCatColorInput.sendKeys(string);
    }
    @When("User inputs {int} into Cost field")
    public void user_inputs_into_cost_field(Integer int1) {
        Runner.adminPage.AddCatCostsInput.sendKeys(String.valueOf(int1));
    }
    @When("User inputs {string} into ImageURL field")
    public void user_inputs_into_image_url_field(String string) {
        Runner.adminPage.AddCatImageInput.sendKeys(string);
    }

    @When("User clicks Add Cat submit button")
    public void user_clicks_add_cat_submit_button() throws InterruptedException {
        Runner.adminPage.AddCatSubmitButton.click();
        Thread.sleep(500);
    }
    @Then("User should see alert confirmation")
    public void user_should_see_alert_confirmation() throws InterruptedException {
        String conf = "Cat has been added!";
        Thread.sleep(500);
        boolean isPresent = driver.switchTo().alert().getText().equals(conf);
        if (isPresent) driver.switchTo().alert().accept();
        Thread.sleep(1000);
        Assert.assertEquals(isPresent, true, "Error: Cat not confirmed to be added!");
    }
    @Then("User should see cat with {string},{string},{int},{string},{string},{int},and {string} in table")
    public void user_should_see_cat_with_and_in_table(String string, String string2, Integer int1, String string3, String string4, Integer int2, String string5) {
        Assert.assertEquals(Runner.adminPage.lastRowTd.get(1).getText().equals(string), true, "Error: Cat name not added to table!");
        Assert.assertEquals(Runner.adminPage.lastRowTd.get(2).getText().equals(string2), true, "Error: Cat breed not added to table!");
        Assert.assertEquals(Runner.adminPage.lastRowTd.get(3).getText().equals(string3), true, "Error: Cat gender not added to table!");
        Assert.assertEquals(Runner.adminPage.lastRowTd.get(4).getText().equals(String.valueOf(int1)), true, "Error: Cat age not added to table!");
        Assert.assertEquals(Runner.adminPage.lastRowTd.get(5).getText().equals(String.valueOf(int2)), true, "Error: Cat costs not added to table!");
        Assert.assertEquals(Runner.adminPage.lastRowTd.get(6).getText().equals(string5), true, "Error: Cat URL not added to table!");
    }

    @When("User logs out")
    public void user_logs_out() {

    }

    @Then("User should see cat with {string},{string},{int},{string},{string},{int},and {string} in homepage")
    public void user_should_see_cat_with_and_in_homepage(String string, String string2, Integer int1, String string3, String string4, Integer int2, String string5) throws InterruptedException {
        while (Runner.homePage.NextButton.size() > 0) {
            if (Runner.homePage.NextButton.size() > 0) Runner.homePage.NextButton.get(0).click();
            Thread.sleep(300);
        }
        System.out.println(string);
        System.out.println(Runner.homePage.lastCatName);
        System.out.println(string2);
        System.out.println(Runner.homePage.lastCatBreed);
        System.out.println(int1);
        System.out.println(Runner.homePage.lastCatAge);
        System.out.println(string3);
        System.out.println(Runner.homePage.lastCatGender);
    }

}
