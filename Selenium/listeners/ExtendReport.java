package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtendReport implements ITestListener {


    @Override
    public void onTestStart(ITestResult result) {
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Attach message to Report");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Take screenshot and attach to Report");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Attach skip message to Report");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Action anything before Class Test");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Action anything after Class Test");

    }

}
