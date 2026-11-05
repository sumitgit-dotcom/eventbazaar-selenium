package com.eventbazaar.hooks;

import com.eventbazaar.utils.DriverFactory;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;

public class Hooks {

    public static WebDriver driver;

    @Before
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));
        
        // This will now compile successfully because DriverFactory accepts 2 arguments
        driver = DriverFactory.initDriver(browser);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}