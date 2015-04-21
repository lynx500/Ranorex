//package com.ranorex.base;
//
//import org.junit.After;
//import org.junit.Before;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//
//import java.util.concurrent.TimeUnit;
//
//public class TestBase {
//    private static WebDriver driver;
//
//    @Before
//    public void setUp() {
//        driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        driver.get("http://ranorex.com/web-testing-examples/vip/");
//    }
//
//    @After
//    public void tearDown() {
//        driver.quit();
//    }
//}
