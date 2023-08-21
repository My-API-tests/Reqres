package listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import reportsManager.ExtentReportsManager;

public class TestListener implements ITestListener {

    @Override
    public void onStart(ITestContext iTestContext) {

        ExtentReportsManager.init(iTestContext.getSuite().getName());
    }

    @Override
    public void onFinish(ITestContext iTestContext) {

        ExtentReportsManager.flush();
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

        ExtentReportsManager.setTestPassed("Test passed: " + iTestResult.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

        ExtentReportsManager.setTestSkipped("Test skipped: " + iTestResult.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {

        ExtentReportsManager.setTestFailed("Test failed: " + iTestResult.getMethod().getMethodName() + " " + iTestResult.getThrowable().getMessage());
    }
}
