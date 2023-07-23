package rahualshetty.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import rahualshetty.abstractcomponents.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//p[@class='itemNumber']/..//h3")
	private List<WebElement> productTitle;

	@FindBy(xpath = "//button[text()='Checkout']")
	private WebElement checkOutBtn;

	// verify whether the product is added
	public boolean verifyProductDisplay(String productName) {
		for (WebElement lv : productTitle) {
			if (lv.getText().equalsIgnoreCase(productName)) {
				return true;
			}
		}
		return false;
	}

	// click on check out button
	public void goToCheckOut() {
		checkOutBtn.click();
	}

}
