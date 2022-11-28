package stepimplementations.adoptionandselection;

import runners.Runner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.html5.SessionStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Random;

public class CatSelectionAndAdoption {
    private String homeURL = Runner.webURL + "/PurrfectPurrchasesFrontEnd/index.html";
    private ArrayList<WebElement> cats;
    private WebElement clickedCat;
    private String clickedCatName;
    private String clickedCatId;
    private ArrayList<WebElement> catsInCart;
    WebStorage webStorage = (WebStorage) new Augmenter().augment(Runner.driver);
    SessionStorage sessionStorage = webStorage.getSessionStorage();

    @Given("customer selects cat and navigates to the Adoption page")
    public void customer_selects_cat_and_navigates_to_the_adoption_page() {
        Runner.driver.get(homeURL);
        cats = (ArrayList<WebElement>)Runner.driver.findElements(By.xpath("//div[@id='catsquares']//div[@class = 'col']"));
        int rand = (int)((Math.random()*cats.size()));
        clickedCat = cats.get(rand);
        clickedCatId = clickedCat.getAttribute("id");
        clickedCatName = clickedCat.findElement(By.tagName("h2")).getText().split(" -")[0];
        WebElement cartButton = clickedCat.findElement(By.className("cart-button"));
        cartButton.click();
        Runner.wait.until(ExpectedConditions.alertIsPresent());
        Runner.driver.switchTo().alert().accept();
        Runner.wait.until(ExpectedConditions.alertIsPresent());
        Runner.driver.switchTo().alert().accept();
        Runner.homePage.cartLink.click();
    }
    @Then("customer sees cat they selected on the Adoption page")
    public void customer_sees_cat_they_selected_on_the_adoption_page() {
        catsInCart = (ArrayList<WebElement>)Runner.driver.findElements(By.className("cat-in-cart"));
        boolean flag = true;
        try{
            WebElement catInCartName = catsInCart.get(0).findElement(By.id(clickedCatName));
        }
        catch (NoSuchElementException e){
            flag = false;
        }
        Assert.assertTrue(flag);
    }
    @Then("customer sees REMOVE button and clicks it")
    public void customer_sees_remove_button_and_clicks_it() {
        catsInCart = (ArrayList<WebElement>)Runner.driver.findElements(By.className("cat-in-cart"));
        boolean flag = true;
        try{
            WebElement removeButton = catsInCart.get(0).findElement(By.className("remove-cat-from-cart"));
            removeButton.click();
            Runner.wait.until(ExpectedConditions.alertIsPresent());
            Runner.driver.switchTo().alert().accept();
        }
        catch (NoSuchElementException e){
            flag = false;
        }
        Assert.assertTrue(flag);
    }
    @Then("the cat should disappear from the Adoption page and cart total should update")
    public void the_cat_should_disappear_from_the_adoption_page_and_cart_total_should_update() {
        boolean flag = false;
        try{
            Runner.wait.until(ExpectedConditions.visibilityOf(Runner.driver.findElement(By.className("cat-in-cart"))));
            flag = true;
        }
        catch (Exception e){e.printStackTrace();}

        Assert.assertTrue(Runner.adoptionPage.cartTotal.getText().contains("$0"));
        Assert.assertFalse(flag);
    }
    @Given("customer is not logged in")
    public void customer_is_not_logged_in() {

    }
    @When("customer sees PURCHASE NOW button and clicks it")
    public void customer_sees_purchase_now_button_and_clicks_it() throws InterruptedException {
        Runner.wait.until(ExpectedConditions.visibilityOf(Runner.driver.findElement(By.className("cat-in-cart"))));
        Runner.adoptionPage.completePurchaseButton.click();
    }
    @Then("an alert should appear letting the user know they must log in or register first")
    public void an_alert_should_appear_letting_the_user_know_they_must_log_in_or_register_first() {
        Runner.wait.until(ExpectedConditions.alertIsPresent());
        String alertText = Runner.driver.switchTo().alert().getText();
        Runner.driver.switchTo().alert().accept();
        String expectedAlertText = "You must sign in or sign up first if you want to purchase a cat";
        Assert.assertEquals(alertText,expectedAlertText);
    }
    @When("customer signs up as a new user")
    public void customer_signs_up_as_a_new_user() {
        String[] signupInfo = new String[7];
        for(int i = 0; i < signupInfo.length; i++) {
            String possibleChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
            StringBuilder randString = new StringBuilder();
            Random rnd = new Random();
            while (randString.length() < 6) { // length of the random string.
                int index = (int) (rnd.nextFloat() * possibleChars.length());
                randString.append(possibleChars.charAt(index));
            }
            signupInfo[i] = randString.toString();
        }
        Runner.adoptionPage.outerSignup.click();
        Runner.adoptionPage.signupFname.sendKeys(signupInfo[0]);
        Runner.adoptionPage.signupLname.sendKeys(signupInfo[1]);
        Runner.adoptionPage.signupEmail.sendKeys(signupInfo[2] + "@revature.com");
        Runner.adoptionPage.signupPassword.sendKeys(signupInfo[3]);
        Runner.adoptionPage.signupPasswordConfirm.sendKeys(signupInfo[3]);
        Runner.adoptionPage.signupAddress.sendKeys(signupInfo[4]);
        Runner.adoptionPage.signupCity.sendKeys(signupInfo[5]);
        Runner.adoptionPage.signupState.sendKeys(signupInfo[6]);
        Runner.adoptionPage.innerSignupButton.click();
        Runner.wait.until(ExpectedConditions.alertIsPresent());
        Runner.driver.switchTo().alert().accept();
    }
    @Then("cat they selected is visible in the cart")
    public void cat_they_selected_is_visible_in_the_cart() {
        Runner.wait.until(ExpectedConditions.visibilityOf(Runner.driver.findElement(By.className("cat-in-cart"))));
        boolean flag = true;
        try{
            WebElement c = Runner.driver.findElement(By.id(clickedCatName));
        }
        catch (NoSuchElementException e){
            flag = false;
        }
        Assert.assertTrue(flag);
    }
    @When("customer gets confirmation alert and confirms they want to buy the cat\\(s)")
    public void customer_gets_confirmation_alert_and_confirms_they_want_to_buy_the_cat_s() throws InterruptedException {
        Runner.wait.until(ExpectedConditions.alertIsPresent());
        Runner.driver.switchTo().alert().accept();
    }
    @Then("customer gets order complete confirmation")
    public void customer_gets_order_complete_confirmation() throws InterruptedException {
        Runner.wait.until(ExpectedConditions.alertIsPresent());
        String alertText = Runner.driver.switchTo().alert().getText();
        Runner.driver.switchTo().alert().accept();
        String expectedAlertText = "Congratulations on your adoption of " + clickedCatName;
        Assert.assertEquals(alertText,expectedAlertText);
    }
    @Then("adopted cat is not viewable on Home page")
    public void adopted_cat_is_not_viewable_on_home_page() {
        Runner.adoptionPage.homeLink.click();
        cats = (ArrayList<WebElement>)Runner.driver.findElements(By.xpath("//div[@id='catsquares']//div[@class = 'col']"));
        System.out.println(cats);
        boolean flag = true;
        int i = 0;
        while(flag && i < cats.size()) {
            String catId = cats.get(i).getAttribute("id");
            if(catId.equals(clickedCatId)) {
                flag = false;
            }
            i++;
        }
        Assert.assertTrue(flag);
    }
    @Given("customer1 adds cat to cart")
    public void customer1_adds_cat_to_cart() throws InterruptedException {
        Runner.driver.get(homeURL);
        Runner.wait.until(ExpectedConditions.visibilityOf(Runner.homePage.loginLink));
        Runner.homePage.loginLink.click();
        Runner.homePage.loginModalEmail.sendKeys("fake1@revature.com");
        Runner.homePage.loginModalPassword.sendKeys("revature");
        Runner.homePage.loginModalLoginButton.click();
        //Runner.wait.until(ExpectedConditions.elementToBeClickable(Runner.homePage.closeOutLoginModalButton));
        Runner.homePage.closeOutLoginModalButton.click();
        //Runner.wait.until(ExpectedConditions.invisibilityOf(Runner.homePage.modalLogin));
        cats = (ArrayList<WebElement>)Runner.driver.findElements(By.xpath("//div[@id='catsquares']//div[@class = 'col']"));
        int rand = (int)((Math.random()*cats.size()));
        clickedCat = cats.get(rand);
        Runner.wait.until(ExpectedConditions.visibilityOf(clickedCat.findElement(By.tagName("h2"))));
        clickedCatName = clickedCat.findElement(By.tagName("h2")).getText().split(" -")[0];
        WebElement cartButton = clickedCat.findElement(By.className("cart-button"));
        cartButton.click();
        Runner.wait.until(ExpectedConditions.alertIsPresent());
        Runner.driver.switchTo().alert().accept();
        Runner.wait.until(ExpectedConditions.alertIsPresent());
        Runner.driver.switchTo().alert().accept();
        Runner.homePage.logoutLink.click();
    }
    @When("customer2 adopts cat that customer1 added")
    public void customer2_adopts_cat_that_customer1_added() throws InterruptedException {
        Runner.wait.until(ExpectedConditions.visibilityOf(Runner.homePage.loginLink));
        Runner.homePage.loginLink.click();
        Runner.homePage.loginModalEmail.sendKeys("fake2@revature.com");
        Runner.homePage.loginModalPassword.sendKeys("revature");
        Runner.homePage.loginModalLoginButton.click();
        //Runner.wait.until(ExpectedConditions.elementToBeClickable(Runner.homePage.closeOutLoginModalButton));
        Runner.homePage.closeOutLoginModalButton.click();
        WebElement cartButton = clickedCat.findElement(By.className("cart-button"));
        cartButton.click();
        Runner.wait.until(ExpectedConditions.alertIsPresent());
        Runner.driver.switchTo().alert().accept();
        Runner.wait.until(ExpectedConditions.alertIsPresent());
        Runner.driver.switchTo().alert().accept();
        Runner.homePage.cartLink.click();
        Runner.wait.until(ExpectedConditions.visibilityOf(Runner.driver.findElement(By.className("cat-in-cart"))));
        Runner.adoptionPage.completePurchaseButton.click();
        Runner.wait.until(ExpectedConditions.alertIsPresent());
        Runner.driver.switchTo().alert().accept();
        Runner.wait.until(ExpectedConditions.alertIsPresent());
        Runner.driver.switchTo().alert().accept();
        Runner.wait.until(ExpectedConditions.visibilityOf(Runner.adoptionPage.logoutLink));
        Runner.adoptionPage.logoutLink.click();
        Runner.driver.switchTo().alert().accept();
    }
    @When("customer1 logs back in and is on adoption")
    public void customer1_logs_back_in_and_is_on_adoption() {
        Runner.wait.until(ExpectedConditions.visibilityOf(Runner.adoptionPage.loginOrSignupDiv));
        Runner.adoptionPage.outerLoginButton.click();
        Runner.adoptionPage.loginEmail.sendKeys("fake1@revature.com");
        Runner.adoptionPage.loginPassword.sendKeys("revature");
        Runner.adoptionPage.innerLoginButton.click();
        Runner.wait.until(ExpectedConditions.alertIsPresent());
        Runner.driver.switchTo().alert().accept();
        Runner.wait.until(ExpectedConditions.invisibilityOf(Runner.adoptionPage.loginOrSignupDiv));
    }
    @Then("customer1 should receive an alert the cat was removed from their cart")
    public void customer1_should_receive_an_alert_the_cat_was_removed_from_their_cart() {
        Runner.wait.until(ExpectedConditions.alertIsPresent());
        String alertText = Runner.driver.switchTo().alert().getText();
        Runner.driver.switchTo().alert().accept();
        String expectedAlertText = "Sorry, but it appears " + clickedCatName + " was adopted already. " +
                "They have been deleted from your cart.";
        Assert.assertEquals(alertText,expectedAlertText);
    }
}
