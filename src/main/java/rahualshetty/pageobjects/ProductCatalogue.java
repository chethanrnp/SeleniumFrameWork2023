package rahualshetty.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahualshetty.abstractcomponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {

	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//h5[@style='text-transform: uppercase;']")
	private List<WebElement> products;
	By productsBy = By.xpath("//h5[@style='text-transform: uppercase;']");
	By toastMess = By.xpath("//div[text()=' Product Added To Cart ']");

	@FindBy(xpath = "//button[@class='btn w-10 rounded']")
	private List<WebElement> addToCart;

	@FindBy(xpath = "//div[contains(@class,'multiple ng-star-inserted')]")
	private WebElement spinner;

	// waits and gets products list
	public List<WebElement> getProductList() {
		waitForElementsToAppear(productsBy);
		return products;
	}

	// click on desired product
	public void addProductToCart(String productName) {
		for (int i = 0; i < getProductList().size(); i++) {
			String pro = products.get(i).getText();
			if (pro.equalsIgnoreCase(productName)) {
				addToCart.get(i).click();
				break;
			}
		}
	}

	// verify the product is add and click on cart link
	public void veryMessAndClikCart() {
		waitForElementToAppear(toastMess);
		waitForElementToDisapper(spinner);
		goToCartPage();
	}
}
