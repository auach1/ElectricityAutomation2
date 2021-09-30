package extentReports;

import org.testng.IReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

//Extent Report Manager
//Test

public class ExtentManager implements IReporter {
    public static final ExtentReports extentReports = new ExtentReports();

    public synchronized static ExtentReports createExtentReports() {
        ExtentSparkReporter reporter = new ExtentSparkReporter(System.getProperty("user.dir") + "\\reports\\index.html");
        reporter.config().setReportName("Sample Extent Report");
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Project", "Automation Test");
        extentReports.setSystemInfo("Author", "Aurovind Acharya");
        return extentReports;
    }
}