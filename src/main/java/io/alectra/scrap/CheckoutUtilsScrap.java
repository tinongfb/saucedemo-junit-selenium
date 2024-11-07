package io.alectra.scrap;

import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.alectra.main.LoginUtils;
import io.alectra.main.ManageCartUtils;
import io.alectra.utils.Config;

public class CheckoutUtilsScrap {
	private WebDriver driver;
	private WebDriverWait wait;

	public CheckoutUtilsScrap(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Config.getWaitTimeout());
	}

	public void shoppingUtils() throws InterruptedException {
		driver.get(Config.get(Config.Key.URL));
		Scanner scanner = new Scanner(System.in);

		// Login
		String password = Config.get(Config.Key.PASSWORD);
		String username = Config.getUsernames().get(0);
		LoginUtils login = new LoginUtils(driver);
		login.login(username, password);

		ManageCartUtils manageCartUtils = new ManageCartUtils(driver);
		manageCartUtils.addToCart();

		driver.findElement(By.id("shopping_cart_container")).click();
		Thread.sleep(2000);
		
		
		// checkout or continue shopping
				String option;

				if (driver.getCurrentUrl().contains("cart.html")) {
					do {
						System.out.println("Select option to 1-continue shopping or 2-checkout (press number)");
						option = scanner.nextLine();

						if (option.equals("1") || option.equals("2")) {
							switch (option) {
							case "1":
								System.out.println("Continuing shopping...");
								Thread.sleep(3000);
//								System.out.println("Going back to cart");
//								driver.findElement(By.id("shopping_cart_container")).click();
								break;
							case "2":
								System.out.println("Checking out...");
								if (driver.getCurrentUrl().contains("checkout-step-one.html")) {

									do {

										System.out.println("Select option to 1-cancel or 2-continue (enter number then ENTER)");
										option = scanner.nextLine();

										if (option.equals("1") || option.equals("2")) {
											switch (option) {
											case "1":
												System.out.println("Cancelling...");
												Thread.sleep(2000);
												driver.findElement(By.id("cancel")).click();
												break;
											case "2":
												System.out.println("Checking out...");
												driver.findElement(By.id("first-name")).sendKeys("testf");
												driver.findElement(By.id("last-name")).sendKeys("testl");
												driver.findElement(By.id("postal-code")).sendKeys("testp");
												Thread.sleep(2000);
												driver.findElement(By.id("continue")).click();
												break;
											}
										} else {
											System.out.println("Invalid option, please select again.");
										}
									} while (!option.equals("1") && !option.equals("2")); // Continue until a valid option is
																							// selected

								} else {
									if (driver.getCurrentUrl().contains("cart.html")) {
										driver.findElement(By.id("checkout")).click();
									} else {
										Thread.sleep(2000);
										if (driver != null) {
											System.out.println("error navigating, closing browser");
											Thread.sleep(2000);
											driver.quit();
										}

									}

								}

								break;
							}
						} else {
							System.out.println("Invalid option, please select again.");
						}
					} while (!option.equals("1") && !option.equals("2")); // Continue until a valid option is selected
				} else {
					driver.findElement(By.id("shopping_cart_container")).click();
				}

				scanner.close();

	}


}
