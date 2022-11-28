package com.revature.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class Admin {

    public Admin(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    private String deleteID;

    public String getDeleteID() {
        return deleteID;
    }

    public void setDeleteID(String deleteID) {
        this.deleteID = deleteID;
    }

    //Edit Cat Form Modal
    @FindBy(id="editcat-id")
    public WebElement EditCatIDInput;

    @FindBy(id="editcat-name")
    public WebElement EditCatNameInput;

    @FindBy(id="editcat-breed")
    public WebElement EditCatBreedInput;
    public Select getEditBreedOptions() {
        return new Select(EditCatBreedInput);
    }
    public void setEditBreedOption(String value) {
        getEditBreedOptions().selectByVisibleText(value);
    }
    public String getEditBreedOption() {
        return getEditBreedOptions().getFirstSelectedOption().getText();
    }




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
    public Select getBreedOptions() {
        return new Select(AddCatBreedInput);
    }
    public void setBreedOption(String value) {
        getBreedOptions().selectByVisibleText(value);
    }
    public String getBreedOption() {
        return getBreedOptions().getFirstSelectedOption().getText();
    }



    //@FindBy(id="addcat-age")
    //public Select AddCatAgeInput;

    @FindBy(id="addcat-age")
    public WebElement AddCatAgeInput;
    public Select getAgeOptions() {
        return new Select(AddCatAgeInput);
    }
    public void setAgeOption(String value) {
        getAgeOptions().selectByVisibleText(value);
    }
    public String getAgeOption() {
        return getAgeOptions().getFirstSelectedOption().getText();
    }



    //@FindBy(id="addcat-gender")
    //public Select AddCatGenderInput;
    @FindBy(id="addcat-gender")
    public WebElement AddCatGenderInput;
    public Select getGenderOptions() {
        return new Select(AddCatGenderInput);
    }
    public void setGenderOption(String value) {
        getGenderOptions().selectByVisibleText(value);
    }
    public String getGenderOption() {
        return getGenderOptions().getFirstSelectedOption().getText();
    }


    @FindBy(id="addcat-color")
    public WebElement AddCatColorInput;

    @FindBy(id="addcat-costs")
    public WebElement AddCatCostsInput;

    @FindBy(id="addcat-image")
    public WebElement AddCatImageInput;

    @FindBy(id="addCatButton2")
    public WebElement AddCatSubmitButton;

    @FindBy(id="searchCatButton")
    public WebElement searchCatButton;

    @FindBy(id="searchcat-id")
    public WebElement searchCatID;

    @FindBy(id="searchCatButton2")
    public WebElement searchCatButtonSubmit;

    @FindBy(xpath="//tr[last()]/td")
    public List<WebElement> lastRowTd;

}