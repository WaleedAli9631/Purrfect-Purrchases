package com.revature.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {
    public AccountPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }

    @FindBy(id="fname")
    public WebElement fname;
    @FindBy(id="lname")
    public WebElement lname;
    @FindBy(id="street-address")
    public WebElement streetAddress;
    @FindBy(id="city")
    public WebElement city;
    @FindBy(id="state")
    public WebElement state;
    @FindBy(id="btnEdit")
    public WebElement editButton;
    @FindBy(id="btnReadOnly")
    public WebElement saveButton;
    @FindBy(xpath = "//nav/div/ul/li[@id='cartLink']/a")
    public WebElement cartLink;

}
