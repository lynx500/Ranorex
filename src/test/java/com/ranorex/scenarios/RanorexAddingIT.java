package com.ranorex.scenarios;

import com.ranorex.pages.RanorexMainPage;
import com.ranorex.util.PropertyLoader;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class RanorexAddingIT {
    WebDriver driver;
    RanorexMainPage ranorexMainPage;

    @Before
    public void setUp() throws Exception {
        String browser = PropertyLoader.loadProperty("browser");
        if ("firefox".equals(browser)) {
            driver = new FirefoxDriver();
        } else if ("ie".equals(browser)) {
            driver = new InternetExplorerDriver();
        } else if ("chrome".equals(browser)) {
            driver = new ChromeDriver();
        } else {
            driver = new HtmlUnitDriver();
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(PropertyLoader.loadProperty("baseUrl"));
        ranorexMainPage = new RanorexMainPage(driver);
    }

    @Test
    public void userCanAddPersonToRanorex() {
        setFirstName("Nika");
        setLastName("Renaud");
        ranorexMainPage.setGender("female");
        ranorexMainPage.setCategory("Sport");
        ranorexMainPage.getBtnAdd().click();
        assertEquals("Vip count after addition return incorrect value!", "VIP count: 1", ranorexMainPage.getVipCount());
        assertEquals("First row user doesn't have such first name!", "Nika", ranorexMainPage.getFirstNameFromTheTable());
    }

    @Test
    @Parameters({"Miranda, Snow"})
    public void userCanDeleteUser(String firstName, String lastName) {
        addUser(firstName, lastName);
        ranorexMainPage.getBtnDelete().click();
        assertEquals("Vip count after addition return incorrect value!", "VIP count: 0", ranorexMainPage.getVipCount());
    }

    @Test
    @Parameters({"4556, $%^&*"})
    public void userCanClearDatabase(String firstName, String lastName) {
        addUser(firstName, lastName);
        ranorexMainPage.getBtnAdd().click();
        addUser(firstName, lastName);
        ranorexMainPage.getBtnAdd().click();
        ranorexMainPage.getBtnClear().click();
        assertEquals("Vip count after addition return incorrect value!", "VIP count: 0", ranorexMainPage.getVipCount());
    }

    @Test
    public void userCanDisconnectDatabase() {
        ranorexMainPage.getBtnDisconnect().click();
        assertEquals("Text on the connect button doesn't match!", "Connect...", ranorexMainPage.getTextConnectButton());
    }

    @Test
    public void checkPopUpWindowTextAfterClickingAddWithEmptyInput() {
        String init = driver.getWindowHandle();
        ranorexMainPage.getBtnAdd().click();
        Set<String> handles = driver.getWindowHandles();
        for(String handle : handles)
        {
            if(!init.equals(handle))
            {
                WebDriver popup = driver.switchTo().window(handle);
                assertEquals("Incorrect PopUp Window title!", "VIP Database", popup.getTitle());
                assertEquals("Incorrect text in popup window!", "Please specify 'First Name' value", popup.findElement(By.id("alertTextOK")).getText());
                driver.findElement(By.cssSelector("button")).click();
            }
        }
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    public void setFirstName(String strFirstName) {
        ranorexMainPage.getFieldFirstName().sendKeys(strFirstName);
    }

    public void setLastName(String strLastName) {
        ranorexMainPage.getFieldLastName().sendKeys(strLastName);
    }

    public void addUser(String firstName, String lastName) {
        setFirstName(firstName);
        setLastName(lastName);
    }
}
