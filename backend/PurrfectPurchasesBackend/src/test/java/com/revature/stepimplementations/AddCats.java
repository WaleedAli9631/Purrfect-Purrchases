package com.revature.stepimplementations;

import com.revature.runners.Runner;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

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
        Thread.sleep(1000);
        Runner.adminPage.AddCatSubmitButton.click();
        Thread.sleep(1000);
    }
    @Then("User should see alert confirmation")
    public void user_should_see_alert_confirmation() {
        String conf = "Cat has been added!";
        boolean isPresent = driver.switchTo().alert().getText().equals(conf);
        Assert.assertEquals(isPresent, true, "Error: Cat not confirmed to be added!");
    }
    @Then("User should see cat with {string},{string},{int},{string},{string},{int},and {string} in table")
    public void user_should_see_cat_with_and_in_table(String string, String string2, Integer int1, String string3, String string4, Integer int2, String string5) {

    }

}
