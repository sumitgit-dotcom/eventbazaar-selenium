package com.eventbazaar.stepdefinitions;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.eventbazaar.utils.DriverFactory;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
public class HomePageSteps {
	
	WebDriver driver;
	 @Given("I launch the EventBazaar homepage")
	    public void launchEventBazaarHomepage() {

	        driver = DriverFactory.getDriver();

	        driver.get("https://eventbazaar.com/");

	        System.out.println("EventBazaar homepage opened");
	    }

	 
	  @When("the homepage is loaded")
	    public void homepageIsLoaded() {

	        String currentUrl = driver.getCurrentUrl();

	        System.out.println("Current URL: " + currentUrl);

	        Assert.assertTrue(
	            currentUrl.contains("eventbazaar.com"),
	            "EventBazaar homepage URL is incorrect"
	        );
	    }

	  
	  @Then("the EventBazaar homepage should be displayed")
	    public void verifyHomepage() {

	        String title = driver.getTitle();

	        System.out.println("Homepage Title: " + title);

	        Assert.assertNotNull(
	            title,
	            "Homepage title should not be null"
	        );
	        
	        
	        Assert.assertFalse(
	                title.trim().isEmpty(),
	                "Homepage title should not be empty"
	            );

	            System.out.println("Homepage loaded successfully!");
	        }

}
