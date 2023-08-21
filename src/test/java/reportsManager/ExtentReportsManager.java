package reportsManager;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportsManager {

    private static ExtentReports extentReports;
    private static ExtentTest extentTest;

    private static final String filepath = "./reports/";

    public static void init(String filename) {

        extentReports = new ExtentReports();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(filepath + filename);
        sparkReporter.config().setTheme(Theme.DARK);
        extentReports.attachReporter(sparkReporter);
    }

    public static void setTestName(String testName) {

        extentTest = extentReports.createTest(testName);
    }

    public static void setInfo() {


    }

    public static void setTestPassed(String message) {

        extentTest.log(Status.PASS, message);
    }

    public static void setTestSkipped(String message) {

        extentTest.log(Status.SKIP, message);
    }

    public static void setTestFailed(String message) {

        extentTest.log(Status.FAIL, message);
    }

    public static void flush() {

        extentReports.flush();
    }
}
