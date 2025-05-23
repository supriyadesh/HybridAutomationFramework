package Automation.AbstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Automation.PageObjects.CartPage;

public class AbstractComponent {

	WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//a[@class='shopping_cart_link']")
	WebElement shoppingCartLink;
	
	

	public void waitForAllElementsToAppear(By productBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3000));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productBy));
	
	}
	
	public WebElement waitforvisibilityOf(WebElement shoppingCartLink) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3000));
		return wait.until(ExpectedConditions.visibilityOf(shoppingCartLink));
	
	}
	
	public CartPage goToCartPage() {
		waitforvisibilityOf(shoppingCartLink).click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
	
}
