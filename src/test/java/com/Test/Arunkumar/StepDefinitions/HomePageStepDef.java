package com.Test.Arunkumar.StepDefinitions;

import com.Test.Arunkumar.BusinessComponents.HomePageComponents;
import com.Test.Arunkumar.PageActions.HomePageActions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

public class HomePageStepDef extends HomePageActions {

	HomePageComponents general = new HomePageComponents();

	@Given("^I launch the application (.+)$")
	public void i_launch_the_application(String application) throws InterruptedException {
		general.launchApplication(application);
	}

	@And("^I login to the application using (.+) and (.+)$")
	public void login_to_the_application(String userName, String password) {
		general.login(userName, password);
	}

	@And("^I clear the basket if present$")
	public void empty_basket() {
		removecart();
	}

	@And("^I logout from the application$")
	public void logout() {
		general.logout();
	}

}
