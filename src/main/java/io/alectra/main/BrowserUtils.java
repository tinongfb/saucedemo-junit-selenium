package io.alectra.main;

import org.openqa.selenium.WebDriver;

import io.alectra.utils.BrowserDriver;
import io.alectra.utils.Config;

public class BrowserUtils {
	public static void main(String[] args) {
		 // Initialize the WebDriver
        WebDriver driver = BrowserDriver.getBrowserDriver();

        // Get the URL from the config and open it
        String url = Config.get(Config.Key.URL);
        driver.get(url);

        // Print confirmation
        System.out.println("Opened URL: " + url);

        // Close the driver after a short wait
        try {
            Thread.sleep(180000); // Wait 5 seconds for demo purposes
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            BrowserDriver.quitDriver();
        }
	}
}
