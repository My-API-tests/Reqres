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

        ExtentReportsManager.setInfo();
        ExtentReportsManager.flush();
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

        ExtentReportsManager.setTestPassed(iTestResult.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

        ExtentReportsManager.setTestSkipped(iTestResult.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {

        ExtentReportsManager.setTestFailed(iTestResult.getMethod().getMethodName() + " " + iTestResult.getThrowable().getMessage());
    }
}
