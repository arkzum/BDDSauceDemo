package com.Test.Arunkumar.PageLocators;

import com.Test.Arunkumar.Framework.FrameworkUtils;
import org.openqa.selenium.By;

public class HomePageLocators extends FrameworkUtils {

	public final By txtUser = By.id("user-name");
	public final By txtPassword = By.id("password");
	public final By btnSignin = By.id("login-button");
	public final By cartICON = By.xpath("//div[@class='shopping_cart_container']/a/span[@class='shopping_cart_badge']");
	public final By btnRemove = By.cssSelector(".btn btn_secondary btn_small cart_button");
	public final By btnContinueshopping = By.id("continue-shopping");
	public final By iconHeaderMenu = By.id("react-burger-menu-btn");
	public final By lnkLogout = By.id("logout_sidebar_link");
}