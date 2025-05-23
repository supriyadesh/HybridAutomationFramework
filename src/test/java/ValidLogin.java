
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Automation.PageObjects.CartPage;
import Automation.PageObjects.CheckOutPage;
import Automation.PageObjects.LandingPage;
import Automation.PageObjects.ProductCatalogue;
import Automation.PageObjects.confirmPage;

import jdk.internal.org.jline.utils.Log;

public class ValidLogin {

	WebDriverWait wait;
	Actions actions;

	String productName = "Sauce Labs Backpack";
	String firstName = "Supriya";
	String lastName = "Deshpande";
	String postalCode = "412101";

//	@Test(dataProvider = "getData")
	public void ValidLogin(String username, String password) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "/Users/Supriya/Automation/driver/chromedriver");

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		LandingPage landingPage = new LandingPage(driver);
		landingPage.goTo();
		landingPage.LoginApplication(username, password);
		driver.close();

	}

//	@Test(dataProvider = "getData")
	public void InvalidLogin(String username, String password) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "/Users/Supriya/Automation/driver/chromedriver");

		WebDriver driver = new ChromeDriver();
		LandingPage landingPage = new LandingPage(driver);
		landingPage.goTo();
		landingPage.LoginApplication(username, password);
		driver.close();

	}

//	@Test(dataProvider = "getData")
	public void AddToCart(String username, String password) throws InterruptedException {
		// Add Product to cart
		try {

			System.setProperty("webdriver.chrome.driver", "/Users/Supriya/Automation/driver/chromedriver");
			WebDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
			LandingPage landingPage = new LandingPage(driver);
			wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			actions = new Actions(driver);
			landingPage.goTo();

			ProductCatalogue productCatalogue = landingPage.LoginApplication(username, password);
			Thread.sleep(3000);
//			actions.sendKeys(Keys.RETURN).perform();

			List<WebElement> productList = productCatalogue.getProductList();
			productCatalogue.addProductToCart(productName);
			CartPage cartPage = productCatalogue.goToCartPage();
			Boolean match = cartPage.verifyProductTitleDisplay(productName);
			Assert.assertTrue(match);

			Thread.sleep(3000);
			driver.close();
		} catch (NoAlertPresentException ex) {
			// exception handling
			ex.printStackTrace();
//			 log.error("Error --> Timed out (10 seconds) while waiting for second window to be present");

		}
	}

//	@Test(dependsOnMethods = { "AddToCart" })
	@Test(dataProvider = "getData")
	public void CheckoutProduct(String username, String password) throws InterruptedException {
		// Testcase 4 start from here
		System.setProperty("webdriver.chrome.driver", "/Users/Supriya/Automation/driver/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		LandingPage landingPage = new LandingPage(driver);

		actions = new Actions(driver);
		landingPage.goTo();

		ProductCatalogue productCatalogue = landingPage.LoginApplication(username, password);
		Thread.sleep(3000);
		actions.sendKeys(Keys.RETURN).perform();

		List<WebElement> productList = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyProductTitleDisplay(productName);
		Assert.assertTrue(match);
		CheckOutPage checkoutPage = cartPage.goToCheckOutPage();
		confirmPage confirmationPage = checkoutPage.fillCheckOutInfo(firstName, lastName, postalCode);
		Assert.assertTrue(confirmationPage.VerifyOrderConfirmation().equalsIgnoreCase("Thank you for your order!"));

		Thread.sleep(3000);
		driver.close();

	}

	@DataProvider
	public Object[][] getData() {

		Object[][] data = new Object[2][2];

		data[0][0] = "standard_user";
		data[0][1] = "secret_sauce";

		data[1][0] = "standard_user";
		data[1][1] = "secret_sauce";

		return data;
	}

}
