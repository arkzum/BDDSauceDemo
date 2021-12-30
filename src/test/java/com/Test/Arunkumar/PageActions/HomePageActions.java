package com.Test.Arunkumar.PageActions;

import java.util.List;

import org.openqa.selenium.WebElement;

import com.Test.Arunkumar.PageLocators.HomePageLocators;

public class HomePageActions extends HomePageLocators {

	public void enterUser(String value) {
		enterValue(txtUser, value);
	}

	public void enterPassword(String value) {
		enterValue(txtPassword, value);
	}

	public void clickSubmit() {
		click(btnSignin);
	}

	public void removecart() {
		addStepLog("Cart is already Empty");
		if (driver.findElements(cartICON).size() != 0) {
			click(cartICON);
			List<WebElement> Removebtncount = driver.findElements(btnRemove);
			for (int i = 0; i < Removebtncount.size(); i++) {
				scrollByLocatorWebElement(Removebtncount.get(i), "Remove Button");
				mouseHoverToWebElement(Removebtncount.get(i), "Remove Button");
				Removebtncount.get(i).click();
				waitForPageLoad(3);
			}
			click(btnContinueshopping);
		} else
			addStepLog("Cart is already Empty");
	}

	public void clickHamburger() {
		click(iconHeaderMenu);
	}

	public void clickLogOut() {
		click(lnkLogout);
	}
}
