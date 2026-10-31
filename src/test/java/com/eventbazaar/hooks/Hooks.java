package com.eventbazaar.hooks;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import com.eventbazaar.utils.DriverFactory;
import org.testng.Reporter;

public class Hooks {
	@Before
	public void setUp() {
	    String browser = Reporter.getCurrentTestResult()
	                              .getTestContext()
	                              .getCurrentXmlTest()
	                              .getParameter("browser");
	    DriverFactory.initDriver(browser);
	}
    @After
    public void tearDown() { 
        DriverFactory.quitDriver(); 
    }
}