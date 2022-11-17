package com.revature.stepimplementations;

import com.revature.runners.Runner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class FilterCats {
    @Given("User is on home page")
    public void user_is_on_home_page() throws InterruptedException {
        Runner.driver.get(Runner.webURL);
    }
    @When("User selects {string} for breed dropdown")
    public void user_selects_for_breed_dropdown(String string) {
        Runner.homePage.setBreedOption(string);
    }
    @When("User selects {string} for gender dropdown")
    public void user_selects_for_gender_dropdown(String string) {
        Runner.homePage.setGenderOption(string);
    }
    @When("User selects {string} for age dropdown")
    public void user_selects_for_age_dropdown(String string) {
        Runner.homePage.setAgeOption(string);
    }
    @When("User clicks filter dropdown")
    public void user_clicks_filter_dropdown() throws InterruptedException {
        Runner.homePage.FilterButton.click();
        Thread.sleep(500);
    }
    @Then("User should only see cats with {string},{string}, and {string}")
    public void user_should_only_see_cats_with_and(String string, String string2, String string3) {
        boolean isPresent = true;
        if (!string.equals("ALL") && !string.equals("Breed")) {
            for (WebElement elem : Runner.homePage.BreedList) {
                System.out.println(elem.getText());
                System.out.println(string);
                System.out.println("------------");
                if (!elem.getText().equals(string)) isPresent = false;
            }
        }
        if (!string2.equals("ALL") && !string.equals("Gender")) {
            for (WebElement elem : Runner.homePage.GenderList) {
                System.out.println(elem.getText());
                System.out.println(string2);
                System.out.println("------------");
                if (!elem.getText().equals(string2)) isPresent = false;
            }
        }
        if (!string3.equals("ALL") && !string.equals("Age")) {
            for (WebElement elem : Runner.homePage.AgeList) {
                System.out.println(elem.getText());
                System.out.println(string3);
                System.out.println("------------");
                if (!elem.getText().equals(string3)) isPresent = false;
            }
        }
        Assert.assertEquals(isPresent, true, "Error: Unexpected cat appears in list!");
    }

}
