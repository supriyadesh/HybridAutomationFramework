package Automation.Tests;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
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
import Automation.TestComponents.BaseTest;
import jdk.internal.org.jline.utils.Log;

public class ValidateSubmitOrderTest extends BaseTest{

	WebDriverWait wait;
	Actions actions;

	String productName = "Sauce Labs Backpack";
	String firstName = "Supriya";
	String lastName = "Deshpande";
	String postalCode = "412101";
	String titleProducts = "Products";
	String filePath = "/Users/Supriya/Automation/src/test/java/Automation/getOrderData/getOrderData.json";



	@Test(dataProvider = "getData")
	public void AddToCart(String username, String password) throws InterruptedException, IOException {
		// Add Product to cart
		try {

		
			ProductCatalogue productCatalogue = landingPage.LoginApplication(username, password);

			
			actions.sendKeys(Keys.RETURN).build().perform();


			List<WebElement> productList = productCatalogue.getProductList();
			productCatalogue.addProductToCart(productName);
			CartPage cartPage = productCatalogue.goToCartPage();
			Boolean match = cartPage.verifyProductTitleDisplay(productName);
			Assert.assertTrue(match);

			Thread.sleep(3000);
			landingPage.logOut();
		} catch (NoAlertPresentException ex) {
			// exception handling
			ex.printStackTrace();
//			 log.error("Error --> Timed out (10 seconds) while waiting for second window to be present");

		}
	}

	@Test(dataProvider = "getData")
	public void CheckoutProduct(String username, String password) throws InterruptedException {
		// Testcase 4 start from here
	
		
		ProductCatalogue productCatalogue = landingPage.LoginApplication(username, password);
	
		actions.sendKeys(Keys.RETURN).build().perform();

		List<WebElement> productList = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyProductTitleDisplay(productName);
		Assert.assertTrue(match);
		CheckOutPage checkoutPage = cartPage.goToCheckOutPage();
		confirmPage confirmationPage = checkoutPage.fillCheckOutInfo(firstName, lastName, postalCode);
		Assert.assertTrue(confirmationPage.VerifyOrderConfirmation().equalsIgnoreCase("Thank you for your order!"));

		Thread.sleep(3000);
//		landingPage.logOut();

	}

	@DataProvider
	public Object[][] getData() throws IOException {
		
		List<HashMap<String,String >>data = getJsonDataToMap(filePath);
		return new Object[][] { { data.get(0) } };
	}

}
