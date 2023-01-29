package utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners implements ITestListener { // ! implements ITestListener


    @Override
    public void onStart(ITestContext context) {

        System.out.println("onStart - Tum testlerden ONCE 1 sefer cagirilir => " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {

        System.out.println("onFinish - Tum testlerden SONRA 1 sefer cagrilir = > " + context.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {

        //Before Method'a benzer
        System.out.println("onTestStart - Method sayisi kadar, Her bir @Test annotation'dan ONCE 1 sefer calisir => " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        System.out.println("onTestSuccess - PASS olan Method sayisi kadar, ve pass olan methodlardan SONRA bir kez calisir => " + result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        System.out.println("onTestSkipped - Skipped edilen Method sayisi kadar,ve skipped edilen Method'lardan SONRA bir kez calisir => " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {

        System.out.println("onTestFailure - FAILED edilen Method sayisi kadar,ve Failed edilen Method'lardan SONRA bir kez calisir => " + result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }
}
