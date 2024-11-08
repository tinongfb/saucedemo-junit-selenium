package io.alectra.main;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.alectra.utils.BrowserDriver;

class CheckoutUtilsTest {
	private static WebDriver driver;
	private static ManageCartUtils addToCart;
	private static List<String> itemsInCart;
	private static CheckoutUtils checkoutUtils;
	
	@BeforeAll
	public static void setUp() throws InterruptedException {
		driver = BrowserDriver.getBrowserDriver();
		addToCart = new ManageCartUtils(driver);
		addToCart.addToCart();
		itemsInCart = addToCart.getItemsInCart();
		checkoutUtils.shoppingUtils();
	}
	
	
	@Test
	@Order(1)
	public void continueShoppingTest() throws InterruptedException {
		//cart first, then checkout or go back
		assertTrue(driver.getCurrentUrl().contains("checkout-complete.html"), "Checkout completed page should be displayed");
		checkoutUtils.continueShopping();
		assertTrue(driver.getCurrentUrl().contains("inventory.html"), "Back to inventory page to continue shopping");	
	}
	
	@Test
	@Order(2)
	public void proceedCheckoutTest() throws InterruptedException {
		checkoutUtils.checkout();
		assertTrue(driver.getCurrentUrl().contains("checkout-step-one.html"), "In checkout-one page");
	}

	@Test
	@Order(3)
	public void checkoutDetailsFillUpTest() throws InterruptedException {
		checkoutUtils.checkoutDetailsFillUp();
		assertTrue(driver.findElement(By.id("first-name")) != null, "Fields are not empty");
	}
	
	@Test
	@Order(4)
	public void cancelCheckoutTest() throws InterruptedException {
		checkoutUtils.cancelCheckout();
		assertTrue(driver.getCurrentUrl().contains("cart.html") || driver.getCurrentUrl().contains("inventory.html"), "Cancelled during checkout");
	}
	
	
	
}
