package Automation.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Automation.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent{

	WebDriver driver;
	
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "first-name")
	private WebElement firstname;
	
	@FindBy(id = "last-name")
	private WebElement lastname;
	
	@FindBy(id = "postal-code")
	private WebElement postalcode;
	
	@FindBy(xpath = "//input[@id='continue']")
	private WebElement continueBtn;
	
	
	
	public confirmPage fillCheckOutInfo(String firstName,String lastName,String postalCode) {
		
		waitforvisibilityOf(firstname).sendKeys(firstName);
		waitforvisibilityOf(lastname).sendKeys(lastName);
		waitforvisibilityOf(postalcode).sendKeys(postalCode);
		waitforvisibilityOf(continueBtn).click();
		
		return new confirmPage(driver);

		
	}
	

	
	
}
