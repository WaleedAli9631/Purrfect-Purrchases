package com.revature.stepimplementations;

import com.revature.runners.Runner;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.sql.SQLOutput;

import static com.revature.runners.Runner.driver;

public class EditCats {

    @When("User edits cat with {int}")
    public void userEditsCatWithCatID(Integer int1) throws InterruptedException {
        WebElement catID = driver.findElement(By.xpath("//tr/td[text()='" + int1 + "']/following-sibling::td[7]//img"));
        catID.click();
        Thread.sleep(500);
    }

    @When("User inputs {string} into Edit Name field")
    public void user_inputs_into_edit_name_field(String string) {
        Runner.adminPage.EditCatNameInput.clear();
        Runner.adminPage.EditCatNameInput.sendKeys(string);
    }

    @When("User inputs {string} into Edit Breed field")
    public void user_inputs_into_edit_breed_field(String string) {
        Runner.adminPage.setEditBreedOption(string);
    }

    @When("User inputs {int} into Edit Age field")
    public void user_inputs_into_edit_age_field(Integer int1) {
        Runner.adminPage.EditCatAgeInput.sendKeys(String.valueOf(int1));
    }

    @When("User inputs {string} into Edit Gender field")
    public void user_inputs_into_edit_gender_field(String string) {
        Runner.adminPage.EditCatGenderInput.sendKeys(string);
    }

    @When("User inputs {string} into Edit Color field")
    public void user_inputs_into_edit_color_field(String string) {
        Runner.adminPage.EditCatColorInput.clear();
        Runner.adminPage.EditCatColorInput.sendKeys(string);
    }

    @When("User inputs {int} into Edit Cost field")
    public void user_inputs_into_edit_cost_field(Integer int1) {
        Runner.adminPage.EditCatCostsInput.clear();
        Runner.adminPage.EditCatCostsInput.sendKeys(String.valueOf(int1));
    }

    @When("User inputs {string} into Edit ImageURL field")
    public void user_inputs_into_edit_image_url_field(String string) {
        Runner.adminPage.EditCatImageInput.clear();
        Runner.adminPage.EditCatImageInput.sendKeys(string);
    }

    @When("User clicks Edit Cat submit button")
    public void user_clicks_edit_cat_submit_button() throws InterruptedException {
        Runner.adminPage.EditCatSubmitButton.click();
        Thread.sleep(500);
    }

    @Then("User should see alert edit confirmation")
    public void user_should_see_alert_edit_confirmation() throws InterruptedException {
        String conf = "Cat has been edited!";
        Thread.sleep(500);
        boolean isPresent = driver.switchTo().alert().getText().equals(conf);
        if (isPresent) driver.switchTo().alert().accept();
        Thread.sleep(1000);
        Assert.assertEquals(isPresent, true, "Error: Cat not confirmed to be added!");
    }

    @Then("User should see cat {int} with {string},{string},{int},{string},{string},{int},and {string} in table")
    public void user_should_see_cat_with_and_in_table(Integer int1, String string, String string2, Integer int2, String string3, String string4, Integer int3, String string5) throws InterruptedException {
        String catName = driver.findElement(By.xpath("//tr/td[text()='" + int1 + "']/following-sibling::td[1]")).getText();
        String catBreed = driver.findElement(By.xpath("//tr/td[text()='" + int1 + "']/following-sibling::td[2]")).getText();
        String catGender = driver.findElement(By.xpath("//tr/td[text()='" + int1 + "']/following-sibling::td[3]")).getText();
        String catAge = driver.findElement(By.xpath("//tr/td[text()='" + int1 + "']/following-sibling::td[4]")).getText();
        String catCosts = driver.findElement(By.xpath("//tr/td[text()='" + int1 + "']/following-sibling::td[5]")).getText();
        String catURL = driver.findElement(By.xpath("//tr/td[text()='" + int1 + "']/following-sibling::td[6]")).getText();
        Assert.assertEquals(catName.equals(string), true, "Error: Cat name not added to table!");
        Assert.assertEquals(catBreed.equals(string2), true, "Error: Cat breed not added to table!");
        Assert.assertEquals(catGender.equals(string3), true, "Error: Cat gender not added to table!");
        Assert.assertEquals(catAge.equals(String.valueOf(int2)), true, "Error: Cat age not added to table!");
        Assert.assertEquals(catCosts.equals(String.valueOf(int3)), true, "Error: Cat costs not added to table!");
        Assert.assertEquals(catURL.equals(string5), true, "Error: Cat URL not added to table!");
        Thread.sleep(1000);
    }
}
