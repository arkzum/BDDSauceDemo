package com.Test.Arunkumar.PageLocators;

import com.Test.Arunkumar.Framework.FrameworkUtils;
import org.openqa.selenium.By;

public class ProductPagLocators extends FrameworkUtils {

	public final By sortcontainer = By.cssSelector("[class='product_sort_container']");
	public final By lstPLP = By.cssSelector("[class='inventory_item']");
	public final By lstProductName = By.cssSelector("[class='inventory_item_name']");
	public final By lstProductPrice = By.cssSelector("[class='inventory_item_price']");

	public final By btnAddtocart(int index) {
		return By.xpath("(//div[@class='inventory_item']//following-sibling::button)[" + index + "]");
	}

	public final By cartICON = By.xpath("//div[@class='shopping_cart_container']/a/span[@class='shopping_cart_badge']");
	public final By btnCheckout = By.id("checkout");

	public final By txtFirstName = By.id("first-name");
	public final By txtLastName = By.id("last-name");
	public final By txtPostCode = By.id("postal-code");
	public final By btnContinue = By.id("continue");
	public final By btnFinish = By.id("finish");
	public final By lblOrderConfirmation = By.xpath("//h2[@class='complete-header']");
}
