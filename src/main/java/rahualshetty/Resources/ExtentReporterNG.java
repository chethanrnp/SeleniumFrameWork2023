package rahualshetty.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	public ExtentReports getReportObject() {
		// path where the report has to be stored
		String path = System.getProperty("user.dir") + "/reports/index.html";
		// creating object and passing the path responsible for creating html file
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		// set report name
		reporter.config().setReportName("Web Automation Results");
		// sets document name
		reporter.config().setDocumentTitle("Test Result");
		// creating object responsible for generating report
		ExtentReports extent = new ExtentReports();
		// attaching the config
		extent.attachReporter(reporter);
		// giving the name of the tester
		extent.setSystemInfo("Tester", "chethan");
		return extent;
	}
}
