package io.alectra.main;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.alectra.main.ManageCartUtils;
import io.alectra.utils.BrowserDriver;
import io.alectra.utils.Config;

@TestMethodOrder(OrderAnnotation.class)
public class ManageCartTest {
	private static WebDriver driver;
	private static ManageCartUtils addToCart;
	private static List<String> itemsInCart;
	private ManageCartUtils removeItems;

	@BeforeAll
	public static void setUp() throws InterruptedException {
		driver = BrowserDriver.getBrowserDriver();
		addToCart = new ManageCartUtils(driver);
		addToCart.addToCart();
		itemsInCart = addToCart.getItemsInCart();
	}

	@Test
	@Order(1)
	public void testAddToCart() throws InterruptedException {

		List<String> expectedItems = Arrays.asList("Sauce Labs Backpack", "Sauce Labs Bike Light"); // Adjust this list as needed
		//List<String> expectedItems = Arrays.asList("abc", "xyz");
		assertTrue(itemsInCart.containsAll(expectedItems), "Should contain the elements if items are in cart");
		System.out.println("Test passed: assertion-add is correct");
	}
	
	@Test
	@Order(2)
	void testRemoveItemsTest() throws InterruptedException {
		Thread.sleep(5000);
		removeItems = new ManageCartUtils(driver);
		removeItems.removeItems();
		
		itemsInCart = removeItems.getItemsInCart(); 
   		assertTrue(itemsInCart.isEmpty(), "Cart should be empty, this is failed if cart has item");
		System.out.println("Test passed: assertion-remove is correct");
	}
	
	
	@AfterAll
	public static void tearDown() throws InterruptedException {
		Thread.sleep(3000);
		if (driver != null) {
			driver.quit();
		}
	}

}
