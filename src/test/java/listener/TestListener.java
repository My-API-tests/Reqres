package listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import reportsManager.ExtentReportsManager;
import io.qameta.allure.Attachment;

public class TestListener implements ITestListener {

    private static String getTestMethodName(ITestResult iTestResult) {

        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {

        return message;
    }

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

        saveTextLog(getTestMethodName(iTestResult) + "- passed");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

        ExtentReportsManager.setTestSkipped(iTestResult.getMethod().getMethodName());

        saveTextLog(getTestMethodName(iTestResult) + "- skipped");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {

        ExtentReportsManager.setTestFailed(iTestResult.getMethod().getMethodName() + " " + iTestResult.getThrowable().getMessage());

        saveTextLog(getTestMethodName(iTestResult) + "- failed");
    }
}
