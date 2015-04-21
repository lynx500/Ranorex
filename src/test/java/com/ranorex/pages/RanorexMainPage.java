package com.ranorex.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class RanorexMainPage {

    private WebDriver driver;

    @FindBy(id = "FirstName")
    WebElement firstName;

    @FindBy(id = "LastName")
    WebElement lastName;

    @FindBy(id = "Add")
    WebElement btnAdd;

    @FindBy(id = "Save")
    WebElement btnSave;

    @FindBy(id = "Delete")
    WebElement btnDelete;

    @FindBy(id = "count")
    WebElement vipCount;

    @FindBy(id = "Gender")
    List<WebElement> gender;

    @FindBy(id = "Clear")
    WebElement btnClear;

    public RanorexMainPage (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setFirstName(String strFirstName) {
        firstName.sendKeys(strFirstName);
    }

    public void setLastName(String strLastName) {
        lastName.sendKeys(strLastName);
    }

    public void clickAdd() {
        btnAdd.click();
    }

    public void clickDelete() {
        btnDelete.click();
    }

    public void clickSave() {
        btnSave.click();
    }

    public void clickClear() {
        btnClear.click();
    }

    public String getVipCount() {
        return vipCount.getText();
    }

    public void setGender(String strGender) {
        for (WebElement el : gender) {
            if (el.getAttribute("value").equals(strGender)) {
                el.click();
            }
        }
    }

    public void setCategory(String strCategory) {
        Select category = new Select(driver.findElement(By.id("Category")));
        category.selectByVisibleText(strCategory);
    }

    public String getFirstNameFromTheTable() {
        return driver.findElement(By.xpath("//table[@id='VIPs']/tbody/tr[2]/td[2]")).getText();
    }

    public void addUser(String firstName, String lastName) {
        setFirstName(firstName);
        setLastName(lastName);
    }
}