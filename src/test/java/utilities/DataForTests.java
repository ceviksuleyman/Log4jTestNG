package utilities;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

public class DataForTests {

    @DataProvider(name = "DataForPost2")
    public Object[][] dataForPost2() {

        return new Object[][]{
                {"Albert", "Tester"},
                {"Einstein", "Developer"},
                {"Thomas", "Devops"},
                {"Edison", "QA"}};
    }

    @DataProvider(name = "DeleteData")
    public Object[][] dataForDelete() {

        return new Object[][]{{1}, {2}, {3}, {4}};
    }
}
