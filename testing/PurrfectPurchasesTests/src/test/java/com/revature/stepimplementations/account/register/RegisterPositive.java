package com.revature.stepimplementations.account.register;

import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.ExpectedConditions;
import runners.Runner;

import java.util.Random;

public class RegisterPositive {
    @When("User clicks the register link")
    public void userClicksTheRegisterLink() {
        Runner.wait.until(ExpectedConditions.visibilityOf(Runner.homePage.signUpLink));

        Runner.homePage.signUpLink.click();
    }

    @When("User inputs a random email into the registration email field")
    public void userInputsEmailIntoTheRegistrationEmailField() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 6;
        String emailString;
        Random random = new Random();

        emailString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        Runner.wait.until(ExpectedConditions.visibilityOf(Runner.homePage.signupEmail));

        Runner.homePage.signupEmail.sendKeys(emailString+"@example.com");
    }

    @When("User inputs {string} into the registration password field")
    public void userInputsPasswordIntoTheRegistrationPasswordField(String arg1) {
        Runner.wait.until(ExpectedConditions.visibilityOf(Runner.homePage.signupPassword));

        Runner.homePage.signupPassword.sendKeys(arg1);

    }

    @When("User inputs {string} into the registration password confirmation field")
    public void userInputsPasswordIntoTheRegistrationPasswordConfirmationField(String arg1) {
        Runner.wait.until(ExpectedConditions.visibilityOf(Runner.homePage.signupPasswordConfirm));

        Runner.homePage.signupPasswordConfirm.sendKeys(arg1);

    }

    @When("User inputs {string} into the registration first name field")
    public void userInputsFnameIntoTheRegistrationFirstNameField(String arg1) {
        Runner.wait.until(ExpectedConditions.visibilityOf(Runner.homePage.signupFname));

        Runner.homePage.signupFname.sendKeys(arg1);

    }

    @When("User inputs {string} into the registration password last name field")
    public void userInputsLnameIntoTheRegistrationPasswordLastNameField(String arg1) {
        Runner.wait.until(ExpectedConditions.visibilityOf(Runner.homePage.signupLname));

        Runner.homePage.signupLname.sendKeys(arg1);

    }

    @When("User inputs {string} into the registration street address field")
    public void userInputsStreetAddressIntoTheRegistrationStreetAddressField(String arg1) {
        Runner.wait.until(ExpectedConditions.visibilityOf(Runner.homePage.signupAddress));

        Runner.homePage.signupAddress.sendKeys(arg1);

    }

    @When("User inputs {string} into the registration city field")
    public void userInputsCityIntoTheRegistrationCityField(String arg1) {
        Runner.wait.until(ExpectedConditions.visibilityOf(Runner.homePage.signupCity));

        Runner.homePage.signupCity.sendKeys(arg1);

    }

    @When("User inputs {string} into the registration state field")
    public void userInputsStateIntoTheRegistrationStateField(String arg1) {
        Runner.wait.until(ExpectedConditions.visibilityOf(Runner.homePage.signupState));

        Runner.homePage.signupState.sendKeys(arg1);

    }

    @When("User clicks the register button")
    public void userClicksTheRegisterButton() {
        Runner.wait.until(ExpectedConditions.visibilityOf(Runner.homePage.signupSubmit));

        Runner.homePage.signupSubmit.click();
    }
}
