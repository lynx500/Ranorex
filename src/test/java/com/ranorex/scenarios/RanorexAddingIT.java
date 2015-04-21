package com.ranorex.scenarios;

import com.ranorex.pages.RanorexMainPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class RanorexAddingIT {
    WebDriver driver;
    RanorexMainPage ranorexMainPage;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://ranorex.com/web-testing-examples/vip/");
        ranorexMainPage = new RanorexMainPage(driver);
    }

    @Test
    public void userCanAddPersonToRanorex() {
        ranorexMainPage.setFirstName("Nika");
        ranorexMainPage.setLastName("Renaud");
        ranorexMainPage.setGender("male");
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

//    @Test
//    public void dsfsdf() throws Exception {
//        Properties props = new Properties();
//        props.load(new FileInputStream(new File("src/test/resources/config.properties")));
//        String browser = props.getProperty("browser");
//        System.out.println(browser);
//    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
