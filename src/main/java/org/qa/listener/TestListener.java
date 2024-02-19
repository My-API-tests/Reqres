package org.qa.listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
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

    }

    @Override
    public void onFinish(ITestContext iTestContext) {


    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

        saveTextLog(getTestMethodName(iTestResult) + "- passed");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

        saveTextLog(getTestMethodName(iTestResult) + "- skipped");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {

        saveTextLog(getTestMethodName(iTestResult) + "- failed");
    }
}
