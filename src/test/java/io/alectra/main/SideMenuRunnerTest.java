package io.alectra.main;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.alectra.main.LoginUtils;
import io.alectra.utils.BrowserDriver;
import io.alectra.utils.Config;

public class SideMenuRunnerTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver = BrowserDriver.getBrowserDriver();
		WebDriverWait wait = new WebDriverWait(driver, Config.getWaitTimeout());
		driver.get(Config.get(Config.Key.URL));
		
		//Login
        String password = Config.get(Config.Key.PASSWORD);
        String username = Config.getUsernames().get(0);
        
        LoginUtils loginTest = new LoginUtils(driver);
		loginTest.login(username, password);
		
		//find sidemenu button
		WebElement burgerMenuBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("react-burger-menu-btn")));
		burgerMenuBtn.click();
		
		System.out.println("Side Menu open");
		Thread.sleep(3000);
		String[] buttonIds = {
				"inventory_sidebar_link",
				"about_sidebar_link",
				"logout_sidebar_link",
				"reset_sidebar_link"
		};
		
		for (String buttonId : buttonIds) {
			try {
				WebElement sideBtn = driver.findElement(By.id(buttonId));
				if (sideBtn.isDisplayed()) {
					System.out.println("Button with ID " + buttonId + " is present.");
				} else {
					System.out.println("Button with ID " + buttonId + " is not displayed.");
				}
			} catch (NoSuchElementException e) {
				System.out.println(buttonId + " is not present.");
			}
		}
		//close the browser
		System.out.println("Closing the browser.");
		Thread.sleep(3000);
		driver.quit();
		
	}
}
