package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import static listeners.ExtentTestManager.getTest;

public class ExtentReportListener extends TestListenerAdapter implements ITestListener {

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    public static final ExtentReports extentReports = new ExtentReports();
    public synchronized static ExtentReports createExtentReports() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("./extent-reports/extent-report.html");
        reporter.config().setReportName("Sample Extent Report");
        reporter.config().setDocumentTitle("Text-Automation");
        reporter.config().setTheme(Theme.DARK);
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Environment", "Test");
        extentReports.setReportUsesManualConfiguration(true);
        return extentReports;
    }

    public void addTestResult(ITestResult result)
    {

        ExtentTest test=createExtentReports().createTest(result.getName());
       // extentManager.setTest(test);
      //  extentManager.OnTestRun(result);
    }

    /*public void OnTestRun(ITestResult result){
        if(result.getStatus()==ITestResult.FAILURE){
            test.assignCategory(result.getInstanceName());
            test.log(Status.FAIL,"Classname:"+""+result.getTestClass());
            test.log(Status.FAIL, MarkupHelper.createLabel("FAILED test case name is:"+""+result.getName(),ExtentColor.RED));
            Reporter.log("Failed Report"+"",true);
            test.log(Status.FAIL,MarkupHelper.createLabel("Testcase FAILED due to below issues:"+"",ExtentColor.RED));
            test.fail(result.getThrowable());
            test.log(Status.FAIL,"ParamsPassed:");
            for(inti=0;i<result.getParameters().length;i++){
                test.log(Status.FAIL,result.getParameters()[i].toString());
            }
        }
        else if(result.getStatus()==ITestResult.SUCCESS){
            test.log(Status.PASS,"TestClass:"+""+result.getTestClass().getName());
        }
        else if(result.getStatus()==ITestResult.SKIP){
            test.log(Status.SKIP,"TestCaseSKIPPEDis"+""+result.getName());
        }
    }*/
    @Override
    public void onStart(ITestContext iTestContext) {
       // Log.info("I am in onStart method " + iTestContext.getName());
      //  iTestContext.setAttribute("WebDriver", this.driver);
    }
    @Override
    public void onFinish(ITestContext iTestContext) {
        //Log.de("I am in onFinish method " + iTestContext.getName());
        //Do tier down operations for ExtentReports reporting!
        ExtentManager.extentReports.flush();
    }
    @Override
    public void onTestStart(ITestResult iTestResult) {
       // Log.info(getTestMethodName(iTestResult) + " test is starting.");
    }
    @Override
    public void onTestSuccess(ITestResult result) {
       // Log.info(getTestMethodName(iTestResult) + " test is succeed.");
        //ExtentReports log operation for passed tests.
        getTest().log(Status.PASS, "Test passed");
        addTestResult(result);
    }
    @Override
    public void onTestFailure(ITestResult iTestResult) {
        addTestResult(iTestResult);
      //  Log.info(getTestMethodName(iTestResult) + " test is failed.");
        //Get driver from BaseTest and assign to local webdriver variable.
        Object testClass = iTestResult.getInstance();
     //   WebDriver driver = ((BaseTest) testClass).getDriver();
        //Take base64Screenshot screenshot for extent reports
       // String base64Screenshot =
       //         "data:image/png;base64," + ((TakesScreenshot) Objects.requireNonNull(driver)).getScreenshotAs(OutputType.BASE64);
        //ExtentReports log and screenshot operations for failed tests.
        //getTest().log(Status.FAIL, "Test Failed",
          //      getTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
    }
    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        //Log.info(getTestMethodName(iTestResult) + " test is skipped.");
        //ExtentReports log operation for skipped tests.
        getTest().log(Status.SKIP, "Test Skipped");
    }
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
       // Log.info("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
    }
}
