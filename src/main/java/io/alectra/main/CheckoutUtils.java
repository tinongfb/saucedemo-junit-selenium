package io.alectra.main;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.alectra.utils.Config;

public class CheckoutUtils {
	private WebDriver driver;
	private WebDriverWait wait;

	public CheckoutUtils(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Config.getWaitTimeout());
	}

	public void shoppingUtils() throws InterruptedException {
		driver.get(Config.get(Config.Key.URL));

		LoginUtils login = new LoginUtils(driver);
		login.login(Config.getUsernames().get(0), Config.get(Config.Key.PASSWORD));

		ManageCartUtils manageCartUtils = new ManageCartUtils(driver);
		manageCartUtils.addToCart();
		driver.findElement(By.id("shopping_cart_container")).click();

		System.out.println("In cart, proceed to next step");
		Thread.sleep(2000);
	}

	public void continueShopping() throws InterruptedException {
		if (driver.getCurrentUrl().contains("cart.html")) {
			driver.findElement(By.id("continue-shopping")).click();
			System.out.println("Continuing shopping...");
		} else {
//	        try {
//				Thread.sleep(2000);
//				if (driver != null) {
//					driver.quit();
//				}
//	            shoppingUtils(); // Call shoppingUtils if not on the cart page
//	        } catch (InterruptedException e) {
//	            e.printStackTrace();
//	        }

			driver.close(); // close incorrect browser instance
			shoppingUtils();

		}
		Thread.sleep(2000);
	}

	public void checkout() throws InterruptedException {
		if (driver.getCurrentUrl().contains("cart.html")) {
			driver.findElement(By.id("checkout")).click();
			System.out.println("Checking out...");
		} 
//		else {
//			driver.close(); // close incorrect browser instance
//			shoppingUtils(); // Call shoppingUtils if not on the cart page
//		}
		Thread.sleep(2000);
	}

	public void checkoutDetailsFillUp() throws InterruptedException {
		if (driver.getCurrentUrl().contains("checkout-step-one.html")) {
			if ((driver.findElement(By.id("first-name")) == null) && (driver.findElement(By.id("last-name")) == null)
					&& (driver.findElement(By.id("postal-code")) == null)) {
				driver.findElement(By.id("first-name")).sendKeys("test");
				driver.findElement(By.id("last-name")).sendKeys("test");
				driver.findElement(By.id("postal-code")).sendKeys("test");
				
				System.out.println("Proceeding to checkout or cancel");
				Thread.sleep(3000);
			}
		}
	}
	
	public void cancelCheckout() {
		//this action should be available for checkout-step-one and checkout-step-two
		try {
			WebElement cancel = driver.findElement(By.id("cancel")); 
			if (cancel != null) {
				cancel.click();
			} 
		} catch (NoSuchElementException e) {
			System.out.println("Element DNE.");
		}
	}
	
	public void checkoutDetailsProceed() throws InterruptedException {
		checkoutDetailsFillUp();
		if ((driver.findElement(By.id("first-name")) != null) && (driver.findElement(By.id("last-name")) != null)
				&& (driver.findElement(By.id("postal-code")) != null)) {
			driver.findElement(By.id("continue")).click();
		}
	}
	
	public void finishCheckout() {
		if (driver.getCurrentUrl().contains("checkout-step-two.html")) {
			driver.findElement(By.id("finish")).click();	//ID "back-to-products" should be present or URL should be "checkout-complete.html"
		}
	}
	
	public float computeTaxTotal(float totalPrice) {
		float taxTotal = totalPrice*(0.08f);
		System.out.println("The toal tax is = " +taxTotal);
		return taxTotal;
	}

	public float computePriceTotal(float itemTotalPrice) {
		List<Float> prices = new ArrayList<>();
		List<WebElement> itemsPrice = driver.findElements(By.className("inventory_item_price"));
		
		for (WebElement priceElement : itemsPrice) {
			String priceText = priceElement.getText();
			priceText = priceText.replaceAll("[^\\d.]", "").trim();
			try {
	            // Parse the price and add it to the list
	            float price = Float.parseFloat(priceText);
	            prices.add(price);  // Add the parsed price to the list
	        } catch (NumberFormatException e) {
	            System.out.println("Invalid price format: " + priceText);
	        }
		}
	    float totalPrice = 0;
	    for (Float price : prices) {
	    	totalPrice += price;  // Sum all prices
	    }
		System.out.println("The total price is = " +totalPrice);
		return totalPrice;
	}
	
}
