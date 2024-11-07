package io.alectra.main;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.alectra.utils.BrowserDriver;
import io.alectra.utils.Config;

public class ManageCartUtils {
	private WebDriver driver;
	private WebDriverWait wait;

	public ManageCartUtils(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Config.getWaitTimeout());
	}

	public void addToCart() throws InterruptedException {
		driver.get(Config.get(Config.Key.URL));

		// Login
		String password = Config.get(Config.Key.PASSWORD);
		String username = Config.getUsernames().get(0);

		LoginUtils loginTest = new LoginUtils(driver);
		loginTest.login(username, password);

		// System.out.println("Adding to cart");
		List<WebElement> addToCartBtns  = driver.findElements(By.xpath("//*[contains(@id, 'add-to-cart')]"));
		for (WebElement add : addToCartBtns ) {
			wait.until(ExpectedConditions.elementToBeClickable(add)).click();
		}
	}

	public List<String> getItemsInCart() throws InterruptedException {
		Thread.sleep(2000);
        List<String> itemsInCart = new ArrayList<>();
        List<WebElement> cartItems = driver.findElements(By.xpath("//*[contains(@id, 'item_')]")); // Adjust selector as needed

        for (WebElement item : cartItems) {
            itemsInCart.add(item.getText());
        }
		
			// System.out.println("Viewing cart content(s)");
		driver.findElement(By.id("shopping_cart_container")).click();
		Thread.sleep(2000);

		//WebElement inventory = driver.findElement(By.xpath("//*[contains(@id, 'remove')]"));
		// System.out.println("");
		
		return itemsInCart;
	}
	
	public void removeItems() throws InterruptedException {
		String currentUrl = driver.getCurrentUrl();
		String expectedUrl = "saucedemo.com/cart.html";
		if (currentUrl.contains(expectedUrl)) {
			Thread.sleep(2000);
			System.out.println("Showing cart page, removing cart items");
			try {
				List<WebElement> cartRemoveItems = driver.findElements(By.xpath("//*[contains(@id, 'remove-')]"));
				//*[@id="remove-sauce-labs-backpack"]
				for (WebElement cartRemoveItem : cartRemoveItems) {
					cartRemoveItem.click();		
				}
			} catch (Exception e) {
				System.out.println("No item in cart");
			}
		}	
		

	}
	
	

}
