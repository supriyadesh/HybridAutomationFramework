package Automation.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Automation.AbstractComponents.AbstractComponent;

public class confirmPage extends AbstractComponent {

	WebDriver driver;

	public confirmPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//h2[@class='complete-header']")
	private WebElement confirmheader;
	
	@FindBy(xpath = "//button[@id='finish']")
	private WebElement finishBtn;

	public String VerifyOrderConfirmation() {
		waitforvisibilityOf(finishBtn).click();
		String confirmationMessage = waitforvisibilityOf(confirmheader).getText();
		return confirmationMessage;

	}

}
