package com.Test.Arunkumar.Framework;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;

import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Map;
import java.util.Random;

public class FrameworkUtils {

	protected WebDriver driver = DriverManager.getInstance().getDriver();

	/*
	 * Function to attach screen shot to Extent report
	 * 
	 * @param : Cucumber Scenario
	 * 
	 * @return : n/a
	 * 
	 * @Author : Arun
	 */

	public void addScreenshot(Scenario scenario) throws IOException {
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		byte[] fileContent = FileUtils.readFileToByteArray(screenshot);
		scenario.attach(fileContent, "image/png", "screenshot");

	}

	/*
	 * Function to add message to log and Extent report
	 * 
	 * @param : String message
	 * 
	 * @return : n/a
	 * 
	 * @Author : Arun
	 */

	public static void addStepLog(String message) {

		Calendar calendar = Calendar.getInstance();
		ExtentCucumberAdapter.addTestStepLog(message);
		Logger logger = LoggerFactory.getLogger(calendar.getTime() + "\t" + "Step");
		logger.info(message);

	}

	/*
	 * Function to add error message to log and Extent report
	 * 
	 * @param : String message
	 * 
	 * @return : n/a
	 * 
	 * @Author : Arun
	 */

	public static void addStepError(String message) {

		Calendar calendar = Calendar.getInstance();
		ExtentCucumberAdapter.addTestStepLog(message);
		Logger logger = LoggerFactory.getLogger(calendar.getTime() + "\t" + "Step");
		logger.error(message);
		Assert.fail(message);

	}

	/*
	 * Functions to add and transfer data between step definitions
	 * 
	 * @param : String message
	 * 
	 * @return : n/a
	 * 
	 * @Author : Arun
	 */

	public void setScenarioContext(Context key, Object value) {
		DriverManager.getInstance().setContext(key, value);
	}

	public String getScenarioContext(Context key) {
		return DriverManager.getInstance().getContext(key).toString().trim();
	}

	/*
	 * Function to make driver sleep / wait for particular time
	 * 
	 * @param : time in milliseconds
	 * 
	 * @return : n/a
	 * 
	 * @Author : Arun
	 */

	public void waitFor(long milliSeconds) {
		try {
			Thread.sleep(milliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	/*
	 * Function to make the driver wait until element visible
	 * 
	 * @param : By , timeout in seconds
	 * 
	 * @return : n/a
	 * 
	 * @Author : Arun
	 */

	public void waitUntilElementVisible(By by, long timeOutInSeconds) {
		(new WebDriverWait(driver, timeOutInSeconds)).until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	/*
	 * Function to make the driver wait until element clickable
	 * 
	 * @param : By , timeout in seconds
	 * 
	 * @return : n/a
	 * 
	 * @Author : Arun
	 */

	public void waitUntilElementClickable(By by, long timeOutInSeconds) {
		(new WebDriverWait(driver, timeOutInSeconds)).until(ExpectedConditions.elementToBeClickable(by));
	}

	/*
	 * Function to wait until the page loads completely
	 * 
	 * @param : timeOutInSeconds
	 * 
	 * @return : n/a
	 * 
	 * @Author : Arun
	 */
	public void waitForPageLoad(long timeOutInSeconds) {

		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			wait.until(pageLoadCondition);
		} catch (Exception e) {
			addStepError("Timeout waiting for Page Load Request to complete.<br><b>Exception : </b>" + e);
		}

	}

	/*
	 * Function to enter value in text field
	 * 
	 * @param : by , String value to be entered
	 * 
	 * @return : n/a
	 * 
	 * @Author : Arun
	 */

	public void enterValue(By by, String Value) {
		try {
			waitUntilElementVisible(by, 90);
			WebElement field = driver.findElement(by);
			if (field.isDisplayed()) {
				try {
					field.clear();
				} catch (Exception e) {
					addStepLog("Warning - Element must be user-editable in order to clear it");
				}
				field.sendKeys(Value);
				addStepLog(Value + " entered in " + by);
			}

		} catch (NoSuchElementException e) {
			addStepError("Unable to find the element -- " + by + " The exception message is : " + e.getMessage());
		} catch (Exception e) {
			addStepError("Operation Failed, Exception: " + e.getMessage());
		}
	}

	/*
	 * Function to click on a webelement
	 * 
	 * @param : By
	 * 
	 * @return : n/a
	 * 
	 * @Author : Arun
	 */

	public void click(By by) {
		try {
			waitUntilElementClickable(by, 20);
			driver.findElement(by).click();
		} catch (Exception e) {
			addStepError("unable to click the element :" + by + " caused by exception : " + e);
		}
		waitForPageLoad(20);
	}

	/*
	 * Function to click on a webelement using javscript
	 * 
	 * @param : By
	 * 
	 * @return : n/a
	 * 
	 * @Author : Arun
	 */

	public void clickUsingJS(By by, String name) {
		try {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", driver.findElement(by));
			addStepLog(name + " is clicked");
		} catch (NoSuchElementException e) {
			addStepError(name + " is not clicked" + e.getMessage());
		}
	}

	/*
	 * Function : To scroll the page based on webElement
	 * 
	 * @param : WebElement and name
	 * 
	 * @return : n/a
	 * 
	 * @Author : Arun
	 */
	protected void scrollByLocatorWebElement(WebElement element, String name) {
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
			waitFor(500);
			addStepLog("Page Scrolled down to " + name + " successfully!");
		} catch (NoSuchElementException e) {
			addStepLog("Unable to find the element -- " + element + " <br><b> The exception message is :</b> "
					+ e.getMessage());
			Assert.fail("Exception: " + e.getMessage());
		} catch (Exception e) {
			addStepError("<p>Operation Failed<br><b>Exception:</b>" + e);
		}
	}

	/*
	 * Function : To MouseHover based on webElement
	 * 
	 * @param : WebElement and name
	 * 
	 * @return : n/a
	 * 
	 * @Author : Arun
	 */

	protected void mouseHoverToWebElement(WebElement ele, String msgdesc) {
		try {
			int attempts = 0;
			while (attempts < 2) {
				try {
					mouseMoveToWebElement(ele);
					break;
				} catch (StaleElementReferenceException e) {
					System.out.println("Stale Element Exception and retrying");
				}
				attempts++;
			}

		} catch (NoSuchElementException e) {
			addStepLog("Unable to find the element -- " + ele + ". <br><b> The exception message is :</b> "
					+ e.getMessage());
			Assert.fail("No such element exception: " + e.getMessage());
		} catch (Exception e) {
			addStepError("<p>Operation Failed<br><b>Exception:</b>" + e);
		}
	}

	public void mouseMoveToWebElement(WebElement ele) {
		Actions actions = new Actions(driver);
		actions.moveToElement(ele).build().perform();

	}

	/*
	 * 
	 * Function : To select a value from dropdown Parameters : by, method,
	 * selectText, dropDownName Return Type : String Owner : Arun
	 */
	protected void dropdownSelect(By by, String method, String selectText, String dropDownName) {
		try {
			waitUntilElementClickable(by, 60);
			Select se = new Select(driver.findElement(by));
			switch (method) {
			case "index":
				se.selectByIndex(Integer.parseInt(selectText));
				addStepLog("Value " + selectText + " from " + dropDownName + " is selected successfully");
				break;
			case "value":
				se.selectByValue(selectText);
				addStepLog("Value " + selectText + " from " + dropDownName + " is selected successfully");
				break;
			case "visibletext":
				se.selectByVisibleText(selectText);
				addStepLog("Value " + selectText + " from " + dropDownName + " is selected successfully");
				break;
			case "SendKeys":
				driver.findElement(by).sendKeys(selectText);
				addStepLog("Value " + selectText + " from " + dropDownName + " is selected successfully");
				break;
			}
		} catch (NoSuchElementException e) {
			addStepError("Unable to find the element -- " + by + ". <br><b> The exception message is :</b> "
					+ e.getMessage());
		} catch (Exception e) {
			addStepError("<p>Operation Failed<br><b>Exception:</b>" + e);
		}
	}

	/*
	 * Function : To Validate the Text content against expected string Value
	 * Parameters : by, Value and msgDesc Return Type : String Owner : Arun
	 */
	protected void validateTextByLocator(By by, String value, String msgDesc) {
		WebElement element = driver.findElement(by);
		String text = element.getText().toString();
		try {
			if (text.equalsIgnoreCase(value)) {
				addStepLog(msgDesc + " displayed as expected!<br><b>Expected Text: </b><br>" + value
						+ "<br><b>Actual Text: </b><br>" + text);
			} else {
				addStepLog(msgDesc + " not displayed as expected!<br><b>Expected Text : </b><br>" + value
						+ "<br><b>Actual Text   : </b><br>" + text);
				Assert.assertEquals(text, value);
			}

		} catch (NoSuchElementException e) {
			addStepLog("Unable to find the element -- " + by + ". <br><b> The exception message is :</b> "
					+ e.getMessage());
		} catch (Exception e) {
			addStepLog("<p>Operation Failed<br><b>Exception:</b>" + e);
		}
	}

	/*
	 * Function to Validate if Element present
	 * @param : By
	 * @return : n/a
	 * @Author : Arun
	 */

	protected void validateText(String actual, String expected) {

		if (actual.equals(expected))
			addStepLog("Actual and Expected text match : " + actual);
		else
			addStepError("Actual Text : " + actual + " is not same as expected : " + expected);

	}

}
