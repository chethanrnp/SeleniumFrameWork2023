package rahualshetty.TestComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;

import javax.imageio.stream.FileImageInputStream;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahualshetty.pageobjects.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingPage;

	// opens the browser and give life to browser
	public WebDriver initializeDriver() throws Throwable {
		// creating a object of property file to read the file
		Properties prop = new Properties();
		// helps in reading the file
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + ".\\src\\main\\java\\rahualshetty\\Resources\\GlobalData.properties");
		prop.load(fis);
		// gets the value of the browser
		String browserName = prop.getProperty("browser");
		// opens in chrome browser
		if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		// opens in the firefox browser
		else if (browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		// return the driver
		return driver;
	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws Throwable {
		driver = initializeDriver();
		// creating object of landind page
		landingPage = new LandingPage(driver);
		// opens the website
		landingPage.goTo();
		// return landing page
		return landingPage;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		// close the window
		driver.close();
	}

	public String getScreenSot(String testCansName,WebDriver driver) throws Throwable {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(System.getProperty("user.dir") + "\\reports\\" + testCansName + ".png"));
		return System.getProperty("user.dir") + "\\reports\\" + testCansName + ".png";
	}
}
