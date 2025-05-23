package Automation.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import Automation.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver;

	public CartPage(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='cart_item_label']//a")
	private List<WebElement> productTitles;
	
	
	@FindBy(xpath = "//button[@name='checkout']")
	private WebElement checkOutBtn;
	
	public Boolean verifyProductTitleDisplay(String productName) {
		
		Boolean match = productTitles.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		 return match;
			
	}

	public CheckOutPage goToCheckOutPage() {
		waitforvisibilityOf(checkOutBtn).click();
		return  new CheckOutPage(driver);
	
	}
	

}
