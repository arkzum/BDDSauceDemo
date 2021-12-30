package com.Test.Arunkumar.StepDefinitions;

import com.Test.Arunkumar.BusinessComponents.ProductPageComponents;
import com.Test.Arunkumar.PageActions.ProductPageActions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProductPageStepDef extends ProductPageActions {

	ProductPageComponents prdPage = new ProductPageComponents();

	@When("I Select and add a products to basket")
	public void i_select_and_add_a_products_to_basket() {
		SortbyPrice();
		addToCartFromPlp();
		clickCartICON();
		waitForPageLoad(3);
		clickCheckout();
	}

	@Then("^I enter checkout user (.+) and (.+) details and (.+) and submit$")
	public void i_enter_checkout_user_firstname_and_lastname_details_and_postcode_and_submit(String firstName,
			String lastName, String postcode) {
		prdPage.checkoutUserDetails(firstName, lastName, postcode);
		clickProceed();
	}

	@And("I navigate to Order Confirmation page from checkout review page")
	public void i_navigate_to_order_confirmation_page_from_checkout_review_page() {
		confirmOrder();
		checkOrderConfirmation();
	}

}
