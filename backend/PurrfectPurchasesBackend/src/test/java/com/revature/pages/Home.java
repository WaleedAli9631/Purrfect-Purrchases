package com.revature.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class Home {

    public Home(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }

    //Navigation Links
    @FindBy(id="homeLink")
    public WebElement HomeLink;

    @FindBy(id="adminPageLink")
    public WebElement AdminLink;

    //Content Information
    @FindBy(className="BreedDeclaration")
    public List<WebElement> BreedList;

    @FindBy(className="AgeDeclaration")
    public List<WebElement> AgeList;

    @FindBy(className="GenderDeclaration")
    public List<WebElement> GenderList;


    //Cat Filter Elements
    @FindBy(id="breedSelect")
    public WebElement BreedDropDown;
    public Select getBreedOptions() {
        return new Select(BreedDropDown);
    }
    public void setBreedOption(String value) {
        getBreedOptions().selectByVisibleText(value);
    }
    public String getBreedOption() {
        return getBreedOptions().getFirstSelectedOption().getText();
    }

    @FindBy(id="ageSelect")
    public WebElement AgeDropDown;

    public Select getAgeOptions() {
        return new Select(AgeDropDown);
    }
    public void setAgeOption(String value) {
        getAgeOptions().selectByVisibleText(value);
    }
    public String getAgeOption() {
        return getAgeOptions().getFirstSelectedOption().getText();
    }

    @FindBy(id="genderSelect")
    public WebElement GenderDropDown;

    public Select getGenderOptions() {
        return new Select(GenderDropDown);
    }
    public void setGenderOption(String value) {
        getGenderOptions().selectByVisibleText(value);
    }
    public String getGenderOption() {
        return getGenderOptions().getFirstSelectedOption().getText();
    }

    @FindBy(id="filterCatButton")
    public WebElement FilterButton;

    //Cat Pagination Elements
    @FindBy(id="prev-button")
    public WebElement PreviousButton;

    @FindBy(id="next-button")
    public WebElement NextButton;

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

}
