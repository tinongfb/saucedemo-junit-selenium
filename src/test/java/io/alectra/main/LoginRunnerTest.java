package io.alectra.main;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.alectra.utils.Config;
import io.alectra.main.LoginUtils;
import io.alectra.utils.BrowserDriver;

public class LoginRunnerTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		 WebDriver driver = BrowserDriver.getBrowserDriver();
		 driver.get(Config.get(Config.Key.URL));

	        // Retrieve the list of usernames and password from config
	        List<String> usernames = Config.getUsernames();
	        String password = Config.get(Config.Key.PASSWORD);

	        LoginUtils loginTest = new LoginUtils(driver);

	        for (String username : usernames) {
	            try {
	                System.out.println("Testing login for username: " + username);
	                loginTest.login(username, password);
	                
	                // Validate the login success/failure
	                if (isLoginSuccessful(driver)) {
	                    System.out.println("Login successful for: " + username);
	                } else {
	                    System.out.println("Login failed for: " + username);
	                }
	            } catch (Exception e) {
	                System.out.println("An error occurred while testing username " + username + ": " + e.getMessage());
	            } finally {
	                // Reset the session before the next login
	                resetSession(driver);
	            }
	        }
	        // Ensure the browser is closed regardless of test outcome
	        
			if (driver != null) {
				System.out.println("All accounts tested, closing browser");
				Thread.sleep(2000);
				driver.quit();
			}
	    }

//	        for (String username : usernames) {
//	            System.out.println("Testing login for username: " + username);
//	            loginTest.login(username, password);
//	            
//	            // Validate the login success/failure
//	            if (isLoginSuccessful(driver)) {
//	                System.out.println("Login successful for: " + username);
//	            } else {
//	                System.out.println("Login failed for: " + username);
//	            }
//
//	            // Reset the session before the next login
//	            resetSession(driver);
//	        }
//	        // Close the browser
//	        Thread.sleep(2000);
//	        driver.quit();
//	    }
	
    private static boolean isLoginSuccessful(WebDriver driver) throws InterruptedException {
       String currentURL = driver.getCurrentUrl();
      
       // Log the current URL for verification purposes
       System.out.println("Current URL: " + currentURL);
       Thread.sleep(2000);
       
       // Check if the URL contains "inventory.html" (indicating a successful login)
       boolean loginSuccess = currentURL.contains("inventory.html");
       
       // Verify the URLs match as expected
       if (loginSuccess) {
           System.out.println("Login successful, current URL matches expected path.");
       } else {
           System.out.println("Login unsuccessful, current URL does not match expected path.");
       }
       
       return loginSuccess;
    }
	
    private static void resetSession(WebDriver driver) throws InterruptedException {
        // Implement logout logic or reset session
        // If there's a logout button, click it; otherwise, clear cookies and reload the page
        try {
            // Attempt to log out if a logout button is available
            WebElement logoutButton = driver.findElement(By.id("logout_sidebar_link")); // Adjust ID as needed
            logoutButton.click();
        } catch (Exception e) {
            System.out.println("Logout button not found, resetting session by clearing cookies.");
        } finally {
            // Clear cookies to reset session
        	Thread.sleep(2000);
            driver.manage().deleteAllCookies();
            driver.navigate().refresh(); // Refresh the page to load the login state again
        }
    }

}
