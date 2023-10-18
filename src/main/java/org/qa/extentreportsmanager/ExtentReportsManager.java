package org.qa.extentreportsmanager;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportsManager {

    private static ExtentReports extentReports;
    private static ExtentTest extentTest;

    private static final String filepath = "./src/reports/";

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

        extentReports.setSystemInfo("Available processors (core)", String.valueOf(Runtime.getRuntime().availableProcessors()));
        extentReports.setSystemInfo("Maximum memory", String.valueOf(Runtime.getRuntime().maxMemory()));
        extentReports.setSystemInfo("Total memory", String.valueOf(Runtime.getRuntime().totalMemory()));
        extentReports.setSystemInfo("Free memory", String.valueOf(Runtime.getRuntime().freeMemory()));
        extentReports.setSystemInfo("Operating system", System.getProperty("os.name") + " " + System.getProperty("os.arch"));
        extentReports.setSystemInfo("System property", System.getProperty("user.dir"));
        extentReports.setSystemInfo("Java runtime version", System.getProperty("java.runtime.version"));
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
