package rahualshetty.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahualshetty.TestComponent.BaseTest;
import rahualshetty.pageobjects.CartPage;
import rahualshetty.pageobjects.CheckOutPage;
import rahualshetty.pageobjects.ConfirmationPage;
import rahualshetty.pageobjects.OrderPage;
import rahualshetty.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) throws Throwable {

		String country = "India";
		// login to the application
		landingPage.landingApplication(input.get("usn"), input.get("psw"));
		// creating object of Product catalogue
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		// gets and wait until for the products
		productCatalogue.getProductList();
		// add product to cart
		productCatalogue.addProductToCart(input.get("product"));
		// verify and click on cartButton
		productCatalogue.veryMessAndClikCart();
		// creating the object of cart page
		CartPage cartPage = new CartPage(driver);
		// check whether product is added and return boolean value
		boolean match = cartPage.verifyProductDisplay(input.get("product"));
		// validate whether product is added
		Assert.assertTrue(match);
		// click on check out button
		cartPage.goToCheckOut();
		// creating object of checkoutpage
		CheckOutPage checkOutPage = new CheckOutPage(driver);
		// select the desired country
		checkOutPage.selectCountry(country);
		// click on place order
		checkOutPage.submitOrder();
		// creating object of confirmation page
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		// gets the text of confirmation message
		String confirmMessage = confirmationPage.verifyConfirmationMessage();
		// validating confirmation message using assert
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
	}

	@Test(dataProvider = "getData")
	public void orderHistoryTest(HashMap<String, String> input) {

		// login to the application
		landingPage.landingApplication(input.get("usn"), input.get("psw"));
		// click on orders button
		landingPage.goToOrdersPage();
		// creating object of order page
		OrderPage orderPage = new OrderPage(driver);
		// check whether order is placed
		boolean match = orderPage.verifyOrderDisplay(input.get("product"));
		// verify using asset
		Assert.assertTrue(match);

	}

	// using hashmap
	@DataProvider
	public Object[][] getData() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("usn", "chethan.supernova@gmail.com");
		map.put("psw", "Chethan@17");
		map.put("product", "IPHONE 13 PRO");

		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("usn", "chethan.supernova@gmail.com");
		map1.put("psw", "Chethan@17");
		map1.put("product", "IPHONE 13 PRO");

		return new Object[][] { { map } };
	}

	// using data provider
	@DataProvider
	public Object[][] getData2() {
		return new Object[][] { { "chethan.supernova@gmail.com", "Chethan@17", "IPHONE 13 PRO" },
				{ "chethan.supernova@gmail.com", "Chethan@17", "IPHONE 13 PRO" } };
	}

}
