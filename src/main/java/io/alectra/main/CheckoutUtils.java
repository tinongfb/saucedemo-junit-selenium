package io.alectra.main;

import java.util.List;

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
		} else {
			driver.close(); // close incorrect browser instance
			shoppingUtils(); // Call shoppingUtils if not on the cart page
		}
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
	
	public void checkoutDetailsCancel() {
		if (driver.getCurrentUrl().contains("checkout-step-one.html")) {
			driver.findElement(By.id("cancel")).click();	//URL should be back to cart.html
		}
	}
	
	public void checkoutDetailsProceed() throws InterruptedException {
		checkoutDetailsFillUp();
		if ((driver.findElement(By.id("first-name")) != null) && (driver.findElement(By.id("last-name")) != null)
				&& (driver.findElement(By.id("postal-code")) != null)) {
			driver.findElement(By.id("continue")).click();
		} else {
			
		}
	}
	
	

}
