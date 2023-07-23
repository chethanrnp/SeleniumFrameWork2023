package rahualshetty.Test;

import org.testng.Assert;
import org.testng.annotations.Test;

import rahualshetty.TestComponent.BaseTest;
import rahualshetty.TestComponent.Retry;
import rahualshetty.pageobjects.CartPage;
import rahualshetty.pageobjects.ProductCatalogue;

public class ErrorValidationTest extends BaseTest {

	@Test(groups = { "errorHandling" }, retryAnalyzer = Retry.class)
	public void loginErrorValidation() throws Throwable {
		// login to the application
		landingPage.landingApplication("chethan.supernova@gmail.com", "Chethan@1");
		// validation of error message using Assert
		Assert.assertEquals(landingPage.getErrorMess(), "Incorrect email or password.");
	}

	@Test
	public void productErrorValidation() throws Throwable {
		String productName = "iphone 13 pro";
		String expected = "iphone 13 p";
		// login to the application
		landingPage.landingApplication("chethan.supernova@gmail.com", "Chethan@17");
		// creating object of Product catalogue
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		// gets and wait until for the products
		productCatalogue.getProductList();
		// add product to cart
		productCatalogue.addProductToCart(productName);
		// verify and click on cartButton
		productCatalogue.veryMessAndClikCart();
		// creating the object of cart page
		CartPage cartPage = new CartPage(driver);
		// check whether product is added and return boolean value
		boolean match = cartPage.verifyProductDisplay(expected);
		// validate whether product is not added
		Assert.assertFalse(match);

	}
}
