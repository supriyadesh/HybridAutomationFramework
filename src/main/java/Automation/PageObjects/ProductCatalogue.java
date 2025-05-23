package Automation.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import Automation.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {

	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='inventory_list']/div[@class='inventory_item']")
	private List<WebElement> ProductList;

	

	By productBy = By.xpath("//div[@class='inventory_list']/div[@class='inventory_item']");
	By addToCart = By.xpath("//div[@class='pricebar']//button");

	public List<WebElement> getProductList() {
		waitForAllElementsToAppear(productBy);
		return ProductList;

	}

	public WebElement getProductName(String productName) {
		WebElement prod = getProductList().stream().filter(product -> product
				.findElement(By.cssSelector("a div[class='inventory_item_name ']")).getText().equals(productName))
				.findFirst().orElse(null);
		return prod;
	}

	public void addProductToCart(String productName) {
		WebElement prod = getProductName(productName);
		prod.findElement(addToCart).click();
		

	}

}
