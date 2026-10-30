package com.eventbazaar.hooks;

import com.eventbazaar.utils.DriverFactory;

import io.cucumber.java.Before;
import io.cucumber.java.After;

public class Hooks {
	
	  @Before
	    public void setUp() {

	        System.out.println("Starting browser...");

	        DriverFactory.initializeDriver();
	    }
	  
	  @After
	    public void tearDown() {

	        System.out.println("Closing browser...");

	        DriverFactory.closeDriver();
	    }

}
