package rahualshetty.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahualshetty.abstractcomponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent {

	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='form-group']//input")
	private WebElement enterCountry;

	@FindBy(xpath = "//a[text()='Place Order ']")
	private WebElement placeOrderBtn;

	@FindBy(xpath = "//span[@class='ng-star-inserted']")
	private List<WebElement> selectCountry;

	By result = By.xpath("//section[@class='ta-results list-group ng-star-inserted']");

	// select the desired country
	public void selectCountry(String country) {
		Actions a = new Actions(driver);
		a.sendKeys(enterCountry, country).build().perform();
		waitForElementToAppear(result);
		for (WebElement lv : selectCountry) {
			if (lv.getText().equalsIgnoreCase(country)) {
				lv.click();
				break;
			}
		}
	}

	// click on submit
	public void submitOrder() {
		placeOrderBtn.click();
	}
}
