
package com.eventbazaar.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverFactory {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static WebDriver initDriver(String browser) {
        if (browser == null) {
            throw new IllegalStateException("<parameter name=\"browser\"> is missing in testng.xml");
        }

        WebDriver webDriver;
        if ("chrome".equalsIgnoreCase(browser)) {
            webDriver = new ChromeDriver(); // Added Chrome
        } else if ("firefox".equalsIgnoreCase(browser)) {
            webDriver = new FirefoxDriver();
        } else if ("edge".equalsIgnoreCase(browser)) {
            webDriver = new EdgeDriver();
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        
        // Optional: Maximize window for better visibility
        webDriver.manage().window().maximize();
        
        driver.set(webDriver);
		return webDriver;
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove(); 
        }
    }
}
