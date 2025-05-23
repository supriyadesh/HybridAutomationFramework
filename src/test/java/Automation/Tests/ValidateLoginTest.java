package Automation.Tests;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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

public class ValidateLoginTest extends BaseTest {

	WebDriverWait wait;
	Actions actions;

	String productName = "Sauce Labs Backpack";
	String firstName = "Supriya";
	String lastName = "Deshpande";
	String postalCode = "412101";
	String titleProducts = "Products";
	String filePath = "/Users/Supriya/Automation/src/test/java/Automation/getOrderData/getOrderData.json";

	@Test(dataProvider = "getData")
	public void ValidLogin(HashMap<String, String> input) throws InterruptedException, IOException {

		landingPage.LoginApplication(input.get("username"), input.get("password"));
		Assert.assertTrue(landingPage.verifyValidLogin().equalsIgnoreCase(titleProducts));
		landingPage.logOut();

	}

	@Test()
	public void InvalidLogin() throws InterruptedException, IOException {

		landingPage.LoginApplication("locked_out_user", "secret_sauce");
		Assert.assertTrue(landingPage.verifyInValidLogin().equalsIgnoreCase(
				"Sorry, this user has been locked out."));

	}

	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap(filePath);
		return new Object[][] { { data.get(0) } };
	}

}
