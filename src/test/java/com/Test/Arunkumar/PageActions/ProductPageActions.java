package com.Test.Arunkumar.PageActions;

import java.util.List;

import org.openqa.selenium.WebElement;

import com.Test.Arunkumar.PageLocators.ProductPagLocators;

public class ProductPageActions extends ProductPagLocators {

	public void SortbyPrice() {
		dropdownSelect(sortcontainer, "value", "hilo", "Sort by Price");
	}

	public void addToCartFromPlp() {
		List<WebElement> LstPLP = driver.findElements(lstPLP);
		for (int i = 1; i <= LstPLP.size(); i++) {
			if (i == 2 || i == LstPLP.size() - 1) {
				click(btnAddtocart(i));
			}
		}
	}

	public void clickCartICON() {
		click(cartICON);
	}

	public void clickCheckout() {
		click(btnCheckout);
	}

	public void enterFirstName(String firstName) {
		enterValue(txtFirstName, firstName);
	}

	public void enterLastName(String lastName) {
		enterValue(txtLastName, lastName);
	}

	public void enterPostCode(String postcode) {
		enterValue(txtPostCode, postcode);
	}

	public void clickProceed() {
		click(btnContinue);
	}

	public void confirmOrder() {
		click(btnFinish);
	}

	public void checkOrderConfirmation() {
		validateText(driver.findElement(lblOrderConfirmation).getText(), "THANK YOU FOR YOUR ORDER");
	}
}
