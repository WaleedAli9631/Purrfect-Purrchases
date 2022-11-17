package com.revature.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class Admin {

    public Admin(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    //Edit Cat Form Modal
    @FindBy(id="editcat-id")
    public WebElement EditCatIDInput;

    @FindBy(id="editcat-name")
    public WebElement EditCatNameInput;

    @FindBy(id="editcat-breed")
    public WebElement EditCatBreedInput;

    @FindBy(id="editcat-age")
    public WebElement EditCatAgeInput;

    @FindBy(id="editcat-gender")
    public WebElement EditCatGenderInput;

    @FindBy(id="editcat-color")
    public WebElement EditCatColorInput;

    @FindBy(id="editcat-costs")
    public WebElement EditCatCostsInput;

    @FindBy(id="editcat-image")
    public WebElement EditCatImageInput;

    @FindBy(id="editCatButton")
    public WebElement EditCatSubmitButton;

    //Add Cat Form Modal

    @FindBy(id="addCatButton")
    public WebElement OpenAddCatModalButton;

    @FindBy(id="addcat-name")
    public WebElement AddCatNameInput;

    @FindBy(id="addcat-breed")
    public WebElement AddCatBreedInput;

    @FindBy(id="addcat-age")
    public WebElement AddCatAgeInput;

    @FindBy(id="addcat-gender")
    public WebElement AddCatGenderInput;

    @FindBy(id="addcat-color")
    public WebElement AddCatColorInput;

    @FindBy(id="addcat-costs")
    public WebElement AddCatCostsInput;

    @FindBy(id="addcat-image")
    public WebElement AddCatImageInput;

    @FindBy(id="addCatButton2")
    public WebElement AddCatSubmitButton;

}