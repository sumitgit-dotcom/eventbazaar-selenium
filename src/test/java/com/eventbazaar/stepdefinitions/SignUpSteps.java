package com.eventbazaar.stepdefinitions;

import com.eventbazaar.utils.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import org.openqa.selenium.NoSuchElementException;


import java.time.Duration;

public class SignUpSteps {

    private WebDriver driver = DriverFactory.getDriver();
    private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

    @Given("I am on the EventBazaar homepage")
    public void i_am_on_the_event_bazaar_homepage() {
        driver.get("https://eventbazaar.com/");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
    }

    @When("I click on the Login button")
    public void iClickOnTheLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[normalize-space()='Login']"))).click();
    }

    @And("I click on the Sign Up link")
    public void iClickOnTheSignUpLink() {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[normalize-space()='Sign Up']"))).click();
    }

    @And("I enter company name {string}")
    public void iEnterCompanyName(String companyName) {
        WebElement companyInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@id='signup-company_name']")));
        companyInput.clear();
        companyInput.sendKeys(companyName);
    }

    @And("I select business category {string}")
    public void iSelectBusinessCategory(String category) {
        // 1. Click the trigger to open the dropdown
        WebElement categoryTrigger = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@id='signup-business_category_dropdown']//input[@id='NA']")));
        categoryTrigger.click();

        // 2. Wait for the option to be VISIBLE (not necessarily clickable yet)
        By optionLocator = By.xpath("//*[normalize-space(text())='" + category + "']");
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(optionLocator));

        // 3. Scroll it into view
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", option);

        // 4. Try normal click, fall back to JS click
        try {
            option.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);
        }

        // 5. Verify selection: the input value should now equal the category
        wait.until(ExpectedConditions.attributeToBe(
                By.xpath("//div[@id='signup-business_category_dropdown']//input"), "value", category));
    }

    @And("I select business sub category {string}")
    public void iSelectBusinessSubCategory(String subCategory) throws InterruptedException {
        Thread.sleep(3000); // give React time to render sub-category after category selection

        System.out.println("========== VISIBLE DROPDOWN CANDIDATES ==========");
        List<WebElement> candidates = driver.findElements(
                By.xpath("//div[contains(@class,'control') or @role='combobox' or contains(@class,'select') "
                       + "or contains(@class,'dropdown') or contains(@class,'value-container')]"));
        for (WebElement e : candidates) {
            try {
                if (e.isDisplayed()) {
                    System.out.println("  class=" + e.getAttribute("class")
                            + " | id=" + e.getAttribute("id")
                            + " | text=[" + e.getText().replaceAll("\\s+", " ").trim() + "]");
                }
            } catch (Exception ignored) { }
        }
        System.out.println("=================================================");

        // Pick the LAST visible dropdown control — since business category is first, sub-category comes after
        WebElement trigger = null;
        for (int i = candidates.size() - 1; i >= 0; i--) {
            WebElement e = candidates.get(i);
            if (e.isDisplayed() && !e.getAttribute("id").contains("business_category")) {
                trigger = e;
                break;
            }
        }

        if (trigger == null) {
            throw new RuntimeException("No visible dropdown found after category selection. "
                    + "Check the CANDIDATES output above — sub-category UI may not be rendering at all.");
        }

        System.out.println("DEBUG: Clicking trigger: class=" + trigger.getAttribute("class")
                + " | text=[" + trigger.getText().replaceAll("\\s+"," ").trim() + "]");

        org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", trigger);
        actions.moveToElement(trigger).click().perform();

        // Wait for option
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[normalize-space(text())='" + subCategory + "']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", option);
        try { actions.moveToElement(option).click().perform(); }
        catch (Exception e) { ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option); }

        System.out.println("DEBUG: Selected sub-category: " + subCategory);
    }
    

    @And("I click on the Next button")
    public void iClickOnTheNextButton() {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[normalize-space()='Next']"))).click();
    }

    @Then("I should be navigated to the next step of sign up")
    public void iShouldBeNavigatedToTheNextStepOfSignUp() {
        wait.until(ExpectedConditions.urlContains("signup"));
    }
}