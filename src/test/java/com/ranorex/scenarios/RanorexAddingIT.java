package com.ranorex.scenarios;

import com.ranorex.pages.RanorexMainPage;
import com.ranorex.util.PropertyLoader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class RanorexAddingIT {
    WebDriver driver;
    RanorexMainPage ranorexMainPage;

    @Before
    public void setUp() throws Exception {
        String browser = PropertyLoader.getBrowserName();
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
        driver.get(PropertyLoader.getBaseURL());
        ranorexMainPage = new RanorexMainPage(driver);
    }

    @Test
    public void userCanAddPersonToRanorex() {
        ranorexMainPage.setFirstName("Nika");
        ranorexMainPage.setLastName("Renaud");
        ranorexMainPage.setGender("female");
        ranorexMainPage.setCategory("Sport");
        ranorexMainPage.clickAdd();
        assertEquals("Vip count after addition return incorrect value!", "VIP count: 1", ranorexMainPage.getVipCount());
        assertEquals("First row user doesn't have such first name!", "Nika", ranorexMainPage.getFirstNameFromTheTable());
    }

    @Test
    public void userCanDeleteUser() {
        ranorexMainPage.addUser("fff", "lll");
        ranorexMainPage.clickDelete();
        assertEquals("Vip count after addition return incorrect value!", "VIP count: 0", ranorexMainPage.getVipCount());
    }

    @Test
    public void userCanClearDatabase() {
        ranorexMainPage.addUser("ggg", "ttt");
        ranorexMainPage.addUser("ggg", "fff");
        ranorexMainPage.clickClear();
        assertEquals("Vip count after addition return incorrect value!", "VIP count: 0", ranorexMainPage.getVipCount());
    }

    @Test
    public void userCanDisconnectDatabase() {
        ranorexMainPage.clickDisconnect();
        assertEquals("Text on the connect button doesn't match!", "Connect...", ranorexMainPage.getTextConnectButton());
    }

    @Test
    public void checkPopUpWindowTextAfterClickingAddWithEmptyInput() {
        String init = driver.getWindowHandle();
        ranorexMainPage.clickAdd();
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
}
