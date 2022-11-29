package com.revature.stepimplementations.catmanage;


import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import runners.Runner;


import static runners.Runner.driver;

public class EditCats {

    @When("User edits cat with {int}")
    public void userEditsCatWithCatID(Integer int1) throws InterruptedException {
        try {
            WebElement catID = driver.findElement(By.xpath("//tr/td[position() = 1 and text()='" + int1 + "']/following-sibling::td[7]//img"));
            Runner.wait.until(ExpectedConditions.visibilityOf(catID));
            catID.click();
        } catch (Exception e) {
            Assert.assertEquals(false, true, "Error: No cat with that ID on the table!");
        }
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
        boolean isPresent = false;
        try {
            Runner.wait.until(ExpectedConditions.alertIsPresent());
            String conf = "Cat has been edited!";
            isPresent = driver.switchTo().alert().getText().equals(conf);
            if (isPresent) driver.switchTo().alert().accept();
        } catch (Exception e) {
            Assert.assertEquals(isPresent, true, "Error: No alert given due to cat submission error!");
        }
    }

    @Then("User should see cat {int} with {string},{string},{int},{string},{string},{int},and {string} in table")
    public void user_should_see_cat_with_and_in_table(Integer int1, String string, String string2, Integer int2, String string3, String string4, Integer int3, String string5) throws InterruptedException {
        String catName = driver.findElement(By.xpath("//tr/td[position() = 1 and text()='" + int1 + "']/following-sibling::td[1]")).getText();
        String catBreed = driver.findElement(By.xpath("//tr/td[position() = 1 and text()='" + int1 + "']/following-sibling::td[2]")).getText();
        String catGender = driver.findElement(By.xpath("//tr/td[position() = 1 and text()='" + int1 + "']/following-sibling::td[3]")).getText();
        String catAge = driver.findElement(By.xpath("//tr/td[position() = 1 and text()='" + int1 + "']/following-sibling::td[4]")).getText();
        String catCosts = driver.findElement(By.xpath("//tr/td[position() = 1 and text()='" + int1 + "']/following-sibling::td[5]")).getText();
        String catURL = driver.findElement(By.xpath("//tr/td[position() = 1 and text()='" + int1 + "']/following-sibling::td[6]")).getText();
        Assert.assertEquals(catName.equals(string), true, "Error: Cat name not added to table!");
        Assert.assertEquals(catBreed.equals(string2), true, "Error: Cat breed not added to table!");
        Assert.assertEquals(catGender.equals(string3), true, "Error: Cat gender not added to table!");
        Assert.assertEquals(catAge.equals(String.valueOf(int2)), true, "Error: Cat age not added to table!");
        Assert.assertEquals(catCosts.equals(String.valueOf(int3)), true, "Error: Cat costs not added to table!");
        Assert.assertEquals(catURL.equals(string5), true, "Error: Cat URL not added to table!");
        Thread.sleep(1000);
    }
}
