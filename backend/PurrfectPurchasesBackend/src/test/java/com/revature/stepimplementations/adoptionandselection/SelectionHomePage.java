package com.revature.stepimplementations.adoptionandselection;

import com.revature.runners.Runner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class SelectionHomePage {
    private String homeURL = Runner.webURL + "/PurrfectPurrchasesFrontEnd/index.html";
    private ArrayList<WebElement> cats;
    private WebElement clickedCat;
    int[] catsSelected = new int[2];

    @Given("customer is on Home page")
    public void customer_is_on_home_page() throws InterruptedException {
        Runner.driver.get(homeURL);
    }
    @Then("customer navigates to the Adoption page")
    public void customer_navigates_to_the_adoption_page() {
        Runner.wait.until(ExpectedConditions.visibilityOf(Runner.homePage.cartLink));
        Runner.homePage.cartLink.click();
        Runner.wait.until(ExpectedConditions.urlContains("adoption-checkout.html"));
        String URL = Runner.driver.getCurrentUrl();
        Assert.assertEquals(URL, "http://127.0.0.1:5501/PurrfectPurrchasesFrontEnd/adoption-checkout.html" );
    }
    @Then("customer navigates back to the Home page")
    public void customer_navigates_back_to_the_home_page() {
        Runner.wait.until(ExpectedConditions.visibilityOf(Runner.adoptionPage.homeLink));
        Runner.adoptionPage.homeLink.click();
        Runner.wait.until(ExpectedConditions.urlContains("index.html"));
        String URL = Runner.driver.getCurrentUrl();
        Assert.assertEquals(URL, "http://127.0.0.1:5501/PurrfectPurrchasesFrontEnd/index.html" );
    }
    @When("customer selects a cat")
    public void customer_selects_a_cat() throws InterruptedException {
        cats = (ArrayList<WebElement>)Runner.driver.findElements(By.xpath("//div[1]/div/div/div[2]/div/div[1]/div"));
        int rand = (int)((Math.random()*cats.size()));
        clickedCat = cats.get(rand);
        WebElement cartButton = cats.get(rand).findElement(By.className("cart-button"));
        cartButton.click();
        Runner.wait.until(ExpectedConditions.alertIsPresent());
        Runner.driver.switchTo().alert().accept();

    }
    @Then("customer will get a confirmation that the cat has been added to their cart")
    public void customer_will_get_a_confirmation_that_the_cat_has_been_added_to_their_cart() {
        Runner.wait.until(ExpectedConditions.alertIsPresent());
        String first = "You have added";
        String second = "to your cart";
        String alertText = Runner.driver.switchTo().alert().getText();
        Assert.assertTrue(alertText.contains(first) && alertText.contains(second));
        Runner.driver.switchTo().alert().accept();
    }
    @When("customer tries to add the same cat again to their cart")
    public void customer_tries_to_add_the_same_cat_again_to_their_cart() {
        Runner.wait.until(ExpectedConditions.alertIsPresent());
        Runner.driver.switchTo().alert().accept();
        clickedCat.findElement(By.className("cart-button")).click();
    }
    @Then("customer should see an alert saying they cannot add it to their cart again")
    public void customer_should_see_an_alert_saying_they_cannot_add_it_to_their_cart_again() {
        Runner.wait.until(ExpectedConditions.alertIsPresent());
        String alertText = Runner.driver.switchTo().alert().getText();
        Runner.driver.switchTo().alert().accept();
        String name = clickedCat.findElement(By.tagName("h2")).getText().split(" -")[0];
        String expectedAlertText = "You already have " + name + " in your cart!";
        Assert.assertTrue(alertText.equals(expectedAlertText));
    }
    @When("customer add two cats to their cart")
    public void customer_add_two_cats_to_their_cart() throws InterruptedException {
        cats = (ArrayList<WebElement>)Runner.driver.findElements(By.xpath("//div[1]/div/div/div[2]/div/div[1]/div"));
        catsSelected[0] = (int)((Math.random()*cats.size()));
        WebElement cartButton = cats.get(catsSelected[0]).findElement(By.className("cart-button"));
        cartButton.click();
        Runner.wait.until(ExpectedConditions.alertIsPresent());
        Runner.driver.switchTo().alert().accept();
        Runner.wait.until(ExpectedConditions.alertIsPresent());
        Runner.driver.switchTo().alert().accept();
        Boolean flag = true;
        while(flag) {
            catsSelected[1] = (int)((Math.random()*cats.size()));
            if (catsSelected[0] != catsSelected[1]) {
                flag = false;
            }
        }
        cartButton = cats.get(catsSelected[1]).findElement(By.className("cart-button"));
        cartButton.click();
        Runner.wait.until(ExpectedConditions.alertIsPresent());
        Runner.driver.switchTo().alert().accept();
        Runner.wait.until(ExpectedConditions.alertIsPresent());
        Runner.driver.switchTo().alert().accept();

    }
    @When("customer tries to add a third cat to their cart")
    public void customer_tries_to_add_a_third_cat_to_their_cart() {
        int rand = 0;
        Boolean flag = true;
        while(flag) {
            rand = (int)((Math.random()*cats.size()));
            if (catsSelected[0] != rand && catsSelected[1]!= rand) {
                flag = false;
            }
        }
        WebElement cartButton = cats.get(rand).findElement(By.className("cart-button"));
        cartButton.click();
    }
    @Then("customer gets an alert that they cannot add another cat")
    public void customer_gets_an_alert_that_they_cannot_add_another_cat() {
        Runner.wait.until(ExpectedConditions.alertIsPresent());
        String alertText = Runner.driver.switchTo().alert().getText();
        Runner.driver.switchTo().alert().accept();
        String expectedAlertText = "You already have 2 cats in your cart. Please navigate to the cart page and " +
                "remove one before adding another";
        Assert.assertTrue(alertText.equals(expectedAlertText));
    }

}
