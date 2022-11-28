package com.revature.stepimplementations.account.accountpage;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import runners.Runner;

public class AccountPagePositive {
    String localFname;
    String localLname;
    String localStreetAddress;
    String localCity;
    String localState;

    @When("User clicks the edit button")
    public void userClicksTheEditButton() {
        Runner.wait.until(ExpectedConditions.visibilityOf(Runner.accountPage.editButton));
        Runner.accountPage.editButton.click();
    }

    @When("User types in {string} into fname field")
    public void userTypesInFnameIntoFnameField(String arg1) {
        localFname = arg1;

        Runner.wait.until(ExpectedConditions.visibilityOf(Runner.accountPage.fname));
        Runner.accountPage.fname.click();
        Runner.accountPage.fname.clear();
        Runner.accountPage.fname.sendKeys(arg1);
    }

    @When("User types in {string} into lname field")
    public void userTypesInLnameIntoLnameField(String arg1) {
        localLname = arg1;

        Runner.wait.until(ExpectedConditions.visibilityOf(Runner.accountPage.lname));
        Runner.accountPage.lname.click();

        Runner.accountPage.lname.clear();
        Runner.accountPage.lname.sendKeys(arg1);
    }

    @When("User types in {string} into streetAddress field")
    public void userTypesInStreetAddressIntoStreetAddressField(String arg1) {
        localStreetAddress = arg1;

        Runner.wait.until(ExpectedConditions.visibilityOf(Runner.accountPage.streetAddress));
        Runner.accountPage.streetAddress.click();

        Runner.accountPage.streetAddress.clear();
        Runner.accountPage.streetAddress.sendKeys(arg1);
    }

    @When("User types in {string} into city field")
    public void userTypesInCityIntoCityField(String arg1) {
        localCity = arg1;

        Runner.wait.until(ExpectedConditions.visibilityOf(Runner.accountPage.city));
        Runner.accountPage.city.click();

        Runner.accountPage.city.clear();
        Runner.accountPage.city.sendKeys(arg1);
    }

    @When("User types in {string} into state field")
    public void userTypesInStateIntoStateField(String arg1) {
        localState = arg1;

        Runner.wait.until(ExpectedConditions.visibilityOf(Runner.accountPage.state));
        Runner.accountPage.state.click();

        Runner.accountPage.state.clear();
        Runner.accountPage.state.sendKeys(arg1);
    }

    @When("User clicks the save button")
    public void userClicksTheSaveButton() {
        Runner.wait.until(ExpectedConditions.visibilityOf(Runner.accountPage.saveButton));
        Runner.accountPage.saveButton.click();
    }

    @Then("User should see the changes reflected")
    public void userShouldSeeTheChangesReflected() {
        Assert.assertEquals(Runner.accountPage.fname.getAttribute("value"),localFname);
        Assert.assertEquals(Runner.accountPage.lname.getAttribute("value"),localLname);
        Assert.assertEquals(Runner.accountPage.streetAddress.getAttribute("value"),localStreetAddress);
        Assert.assertEquals(Runner.accountPage.city.getAttribute("value"),localCity);
        Assert.assertEquals(Runner.accountPage.state.getAttribute("value"),localState);

    }
}
