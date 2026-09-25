package com.qaautomation.parabank.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentReportListener implements ITestListener {

	private static final ExtentReports EXTENT_REPORTS = ExtentReportManager.getInstance();

	private static final ThreadLocal<ExtentTest> CURRENT_TEST = new ThreadLocal<>();

	@Override
	public void onTestStart(ITestResult result) {
		String className = result.getTestClass().getRealClass().getSimpleName();

		String methodName = result.getMethod().getMethodName();

		ExtentTest extentTest = EXTENT_REPORTS.createTest(className + " - " + methodName);

		String description = result.getMethod().getDescription();

		if (description != null && !description.isBlank()) {
			extentTest.info(description);
		}

		CURRENT_TEST.set(extentTest);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		getCurrentTest().pass("Test passed.");

	}

	@Override
	public void onTestFailure(ITestResult result) {
		ExtentTest extentTest = getCurrentTest();

		if (result.getThrowable() != null) {
			extentTest.fail(result.getThrowable());
		} else {
			extentTest.fail("Test failed.");
		}

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		ExtentTest extentTest = getCurrentTest();

		if (result.getThrowable() != null) {
			extentTest.skip(result.getThrowable());
		} else {
			extentTest.skip("Test skipped.");
		}

	}

	@Override
	public void onFinish(ITestContext context) {
		EXTENT_REPORTS.flush();
	}

	private ExtentTest getCurrentTest() {
		ExtentTest extentTest = CURRENT_TEST.get();

		if (extentTest == null) {
			throw new IllegalStateException("No ExtentReports test is available for the current thread.");
		}

		return extentTest;
	}

	public static void attachScreenshot(String screenshotBase64) {
		ExtentTest extentTest = CURRENT_TEST.get();

		if (extentTest != null) {
			String imageHtml = "<img src=\"data:image/png;base64," + screenshotBase64 + "\" style=\"max-width: 100%; "
					+ "height: auto; " + "border: 1px solid #cccccc;\" />";

			extentTest.info(imageHtml);
		}
	}
}