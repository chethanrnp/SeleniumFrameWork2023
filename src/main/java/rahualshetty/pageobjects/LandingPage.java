package rahualshetty.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahualshetty.abstractcomponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

	WebDriver driver;

	@FindBy(id = "userEmail")
	private WebElement userEmailTb;

	@FindBy(id = "userPassword")
	private WebElement passwordTb;

	@FindBy(id = "login")
	private WebElement loginBtn;

	@FindBy(xpath = "//div[text()=' Incorrect email or password. ']")
	private WebElement errorMess;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	// login method
	public void landingApplication(String email, String psw) {
		this.userEmailTb.sendKeys(email);
		this.passwordTb.sendKeys(psw);
		this.loginBtn.click();
		// creating object of Product catalogue

	}

	// get error message
	public String getErrorMess() {
		waitFoWebrElementToAppear(errorMess);
		return errorMess.getText();
	}

	// open website method
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");

	}

}
