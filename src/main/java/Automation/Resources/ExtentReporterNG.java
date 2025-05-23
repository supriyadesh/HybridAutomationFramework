package Automation.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	
	public static ExtentReports getReportObject() {
//		String path = System.getProperty("user.dir" + "/reports/index.html");
		String path = "/Users/Supriya/Automation/reports/index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Supriya Deshpande");
		reporter.config().setDocumentTitle("Test Results");
		
		ExtentReports extents = new ExtentReports();
		extents.attachReporter(reporter);
		extents.setSystemInfo("Tester", "Supriya D");
		extents.createTest(path);
		return extents;
		
		
	}
}
