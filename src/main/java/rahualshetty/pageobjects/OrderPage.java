package rahualshetty.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import rahualshetty.abstractcomponents.AbstractComponent;

public class OrderPage extends AbstractComponent {

	WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//td[text()='iphone 13 pro']/../..//tr[*]//td[2]")
	private List<WebElement> productsName;

	// verify whether the product is added
	public boolean verifyOrderDisplay(String productName) {
		for (WebElement lv : productsName) {
			if (lv.getText().equalsIgnoreCase(productName)) {
				return true;
			}
		}
		return false;
	}

}
