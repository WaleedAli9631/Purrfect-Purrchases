package com.revature.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Adoption {

    @FindBy(xpath = "//div/nav/div/ul/li[1]/a")
    public WebElement homeLink;
    @FindBy(xpath = "//div/nav/div/ul/li[2]/a")
    public WebElement logoutLink;
    @FindBy(xpath = "//div[@id=\"loginOrSignup\"]")
    public WebElement loginOrSignupDiv;
    @FindBy(xpath = "//div/div[1]/button[@id='log in']")
    public WebElement outerLoginButton;
    @FindBy(xpath = "//div/div[1]/div[1]")
    public WebElement loginCollapsible;
    @FindBy(xpath = "//div/div[1]/div[1]/form/div/input[1]")
    public WebElement loginEmail;
    @FindBy(xpath = "//div/div[1]/div[1]/form/div/input[2]")
    public WebElement loginPassword;
    @FindBy(xpath = "//div/div[1]/div[1]/form/button")
    public WebElement innerLoginButton;
    @FindBy(xpath = "//button[@id=\"signup\"]")
    public WebElement outerSignup;
    @FindBy(xpath = "//div/div[1]/div[2]")
    public WebElement signupCollapsible;
    @FindBy(xpath = "//*[@id=\"signup-fname\"]")
    public WebElement signupFname;
    @FindBy(xpath = "//*[@id=\"signup-lname\"]")
    public WebElement signupLname;
    @FindBy(xpath = "//*[@id=\"signup-email\"]")
    public WebElement signupEmail;
    @FindBy(xpath = "//*[@id=\"signup-password\"]")
    public WebElement signupPassword;
    @FindBy(xpath = "//*[@id=\"signup-password-confirm\"]")
    public WebElement signupPasswordConfirm;
    @FindBy(xpath = "//*[@id=\"signup-address\"]")
    public WebElement signupAddress;
    @FindBy(xpath = "//*[@id=\"signup-city\"]")
    public WebElement signupCity;
    @FindBy(xpath = "//*[@id=\"signup-state\"]")
    public WebElement signupState;
    @FindBy(xpath = "//div/div[1]/div[2]/form/button")
    public WebElement innerSignupButton;
    @FindBy(xpath = "//div[@id=\"adoption-cart\"]")
    public WebElement adoptionCartDiv;
    @FindBy(xpath = "//*[@id=\"cart-total\"]")
    public WebElement cartTotal;
    @FindBy(xpath = "//div[@id=\"Checkout\"]")
    public WebElement checkoutDiv;
    @FindBy(xpath = "//*[@id=\"Complete\"]")
    public WebElement completePurchaseButton;
    @FindBy(xpath = "//div/div[3]/div[@id='shipping-info']")
    public WebElement shippingInfo;



    public Adoption(WebDriver driver) {PageFactory.initElements(driver,this);}
}
