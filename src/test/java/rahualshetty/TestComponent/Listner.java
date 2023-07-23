package rahualshetty.TestComponent;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import rahualshetty.Resources.ExtentReporterNG;

public class Listner extends BaseTest implements ITestListener {
	ExtentTest test;

	// creating object of extent report
	ExtentReporterNG ext = new ExtentReporterNG();
	ExtentReports extent = ext.getReportObject();
	// creating object due to syncronisation issue while running paralle
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {
		// watches the exiction before start of each test case by passing test case name
		test = extent.createTest(result.getMethod().getMethodName());
		// this sets all test case id seperate
		extentTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// on test case pass it will show
		test.log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// gets the error message of failed test case and extent test helps to get
		// failed test case ID
		extentTest.get().fail(result.getThrowable());
		// giving life to driver
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Throwable e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// getting the path of screen shot
		String filePath = null;
		try {
			filePath = getScreenSot(result.getMethod().getMethodName(), driver);
		} catch (Throwable e) {

			e.printStackTrace();
		}
		// taking screen shot and attaching it to report with test case name
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {

	}

	@Override
	public void onFinish(ITestContext context) {
		// stops watching and generates html report
		extent.flush();
	}

}
