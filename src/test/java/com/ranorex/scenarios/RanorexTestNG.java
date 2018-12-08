package com.ranorex.scenarios;

import com.ranorex.pages.RanorexMainPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class RanorexTestNG {

    private WebDriver driver;
    private RanorexMainPage ranorexMainPage;

    @BeforeClass
    public void setupClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        ranorexMainPage = new RanorexMainPage(driver);
    }

//    @BeforeTest
//    public void setupTest() {
//        driver = new ChromeDriver();
//    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void test() throws Exception {
        driver.get("https://www.ranorex.com/web-testing-examples/vip/");
        ranorexMainPage.setFirstName("Nika");
        ranorexMainPage.setLastName("Renaud");
        ranorexMainPage.setGender("female");
        ranorexMainPage.setCategory("Sport");
        ranorexMainPage.getBtnAdd().click();
        Assert.assertEquals ("Vip count after addition return incorrect value!", "VIP count: 1", ranorexMainPage.getVipCount());
        Assert.assertEquals ("First row user doesn't have such first name!", "Nika", ranorexMainPage.getFirstNameFromTheTable());
    }

}
