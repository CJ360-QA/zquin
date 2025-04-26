package utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import baseClass.BaseClass;

public class Listener implements ITestListener{
	
	public ExtentSparkReporter spark;
	public ExtentReports extent;
	public ExtentTest test;
	
	String filePath;

	@Override
	public void onStart(ITestContext context) {
		String timestamp=new SimpleDateFormat("dd.MM.yyyy.hh.mm.ss").format(new Date());
		filePath="myreport"+timestamp+".html";
		spark=new ExtentSparkReporter(".\\reports\\"+filePath);
		spark.config().setDocumentTitle("Qa test reports");
		spark.config().setReportName("functional test");
		spark.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(spark);
		extent.setSystemInfo("Env", "QA");
		extent.setSystemInfo("browser", "chrome");
		extent.setSystemInfo("OS", "windows10");
	}
	@Override
	public void onTestSuccess(ITestResult result) {
		test=extent.createTest(result.getName());
		test.log(Status.PASS,"this is passed:"+ result.getName());
	}

	@Override
	public void onTestFailure(ITestResult result) {

		test=extent.createTest(result.getName());
		test.log(Status.FAIL,"this is failed:" + result.getName());
		test.log(Status.INFO,"reason for fail:" + result.getThrowable());
	
		try {
			String imgPath=new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		test=extent.createTest(result.getName());
		test.log(Status.INFO,"reason for skipping"+ result.getName());
	}

	

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}
	
	

}
