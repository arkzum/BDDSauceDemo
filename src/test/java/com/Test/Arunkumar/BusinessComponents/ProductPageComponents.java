package com.Test.Arunkumar.BusinessComponents;

import com.Test.Arunkumar.PageActions.ProductPageActions;

public class ProductPageComponents extends ProductPageActions {

	public void checkoutUserDetails(String firstName, String lastName, String postcode) {
		enterFirstName(firstName);
		enterLastName(lastName);
		enterPostCode(postcode);
	}
}
