package Automation.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Automation.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

	WebDriver driver;

	public LandingPage(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@id='user-name']")
	private WebElement userName;

	@FindBy(xpath = "//input[@id='password']")
	private WebElement passWord;

	@FindBy(xpath = "//input[@id='login-button']")
	private WebElement loginBtn;

	@FindBy(xpath = "//span[@class='title']")
	private WebElement titleProduct;
	
	@FindBy(xpath = "//h3[@data-test='error']")
	private WebElement errorHeader;
	

	@FindBy(xpath = "//div[@class='bm-burger-button']")
	private WebElement openMenu;
	
	@FindBy(xpath = "//a[@id='logout_sidebar_link']")
	private WebElement logoutBtn;
	
	
	public ProductCatalogue LoginApplication(String username, String password) {

		userName.sendKeys(username);
		passWord.sendKeys(password);
		loginBtn.click();
		return new ProductCatalogue(driver);
		
	}

	public void goTo() {
		driver.get("https://www.saucedemo.com/");
	}
	
	public String verifyValidLogin() {
		String productTitle = waitforvisibilityOf(titleProduct).getText();
		return productTitle;
	}
	
	public String verifyInValidLogin() {
		String errorHeaderMessage = waitforvisibilityOf(errorHeader).getText();
		System.out.print(errorHeaderMessage);
		return errorHeaderMessage;
	}
	
	public void logOut() {
		waitforvisibilityOf(openMenu).click();
		waitforvisibilityOf(logoutBtn).click();
		
	}

}
