package rahualshetty.abstractcomponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahualshetty.pageobjects.OrderPage;

public class AbstractComponent {

	WebDriver driver;

	@FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
	private WebElement cartLnk;

	@FindBy(xpath = "//i[@class='fa fa-handshake-o']/..")
	private WebElement ordersBtn;

	// creating constructor for driver
	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Webdriver wait for elements to be visible
	public void waitForElementsToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(findBy));
	}

	// Webdriver wait for element to be visible
	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}

	// Webdriver wait for Webelement to be visible
	public void waitFoWebrElementToAppear(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	// clik on cart Button
	public void goToCartPage() {
		cartLnk.click();
	}

	// clik on order Button
	public void goToOrdersPage() {
		ordersBtn.click();
	}

	// Webdriver wait for element to be visible
	public void waitForElementToDisapper(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}

}
