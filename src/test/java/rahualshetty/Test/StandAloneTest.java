package rahualshetty.Test;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahualshetty.pageobjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/client");
		String productName = "IPHONE 13 PRO";
		String country = "India";
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		// enters the email in textBox
		driver.findElement(By.id("userEmail")).sendKeys("chethan.supernova@gmail.com");
		// enters the password in the textbox
		driver.findElement(By.id("userPassword")).sendKeys("Chethan@17");
		// clicks on login link
		driver.findElement(By.id("login")).click();
		// waits until all the products are visible
		wait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.xpath("//h5[@style='text-transform: uppercase;']")));
		// fetches all the products from the webpage
		List<WebElement> products = driver.findElements(By.xpath("//h5[@style='text-transform: uppercase;']"));
		// click on desired products
		for (int i = 0; i < products.size(); i++) {
			String pro = products.get(i).getText();
			if (pro.equalsIgnoreCase(productName)) {
				driver.findElements(By.xpath("//button[@class='btn w-10 rounded']")).get(i).click();
				break;
			}
		}
		// wait until sucessfull alert appear
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()=' Product Added To Cart ']")));

		// waits until invisibility of loding
		wait.until(ExpectedConditions
				.invisibilityOf(driver.findElement(By.xpath("//div[contains(@class,'multiple ng-star-inserted')]"))));
		// click on cart button
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		// lodes all the component in the cart
		List<WebElement> cartProduct = driver.findElements(By.xpath("//p[@class='itemNumber']/..//h3"));
		for (WebElement lv : cartProduct) {
			if (lv.getText().equalsIgnoreCase(productName)) {
				Assert.assertTrue(true);
				break;
			} else {
				Assert.assertTrue(false);
			}
		}
		// click on checkOut button
		driver.findElement(By.xpath("//button[text()='Checkout']")).click();
		// creating a Actions class
		Actions a = new Actions(driver);
		// enterting data in auto suggestive dropdown
		a.sendKeys(driver.findElement(By.xpath("//div[@class='form-group']//input")), "india").build().perform();
		// waits until drop down is visible
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//section[@class='ta-results list-group ng-star-inserted']")));
		// selectin desired country in autosuggestive drop down
		List<WebElement> countries = driver.findElements(By.xpath("//span[@class='ng-star-inserted']"));
		for (WebElement lv : countries) {
			if (lv.getText().equalsIgnoreCase(country)) {
				lv.click();
				break;
			}
		}
		// click on place order button
		driver.findElement(By.xpath("//a[text()='Place Order ']")).click();
		// validate whether order is placed
		String confirmMess = driver.findElement(By.xpath("//h1[@class='hero-primary']")).getText();
		Assert.assertTrue(confirmMess.equalsIgnoreCase(" Thankyou for the order. "));

	}
}
