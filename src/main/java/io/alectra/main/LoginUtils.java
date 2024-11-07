package io.alectra.main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginUtils {
	private WebDriver driver;
	private WebDriverWait wait;

	public LoginUtils(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 15); // Set a x-second timeout
	}

	public void login(String username, String password) throws InterruptedException {
		// Find the username and password input fields
		WebElement usernameField = driver.findElement(By.id("user-name")); // replace with actual username field ID
		WebElement passwordField = driver.findElement(By.id("password")); // replace with actual password field ID

		if (usernameField != null) {
			// Input the username and password
			usernameField.clear();
			usernameField.sendKeys(username);
			passwordField.clear();
			passwordField.sendKeys(password);

			// Find and click the login button
			
			WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("login-button"))); // replace
																													// with
																													// actual
																													// login
																													// button
																													// ID
			loginButton.click();
			
			
		} else {
        	Thread.sleep(2000);
        	System.out.println("Refreshing page");
            driver.manage().deleteAllCookies();
            driver.navigate().refresh();
		}
		

	}
}
