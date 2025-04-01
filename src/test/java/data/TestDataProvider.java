package data;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

    @DataProvider
    public Object[][] userIDs() {
        return new Object[][]{
                {"7807882"},
                {"7807888"},
                {"7807880"},
                {"7807884"}
        };
    }
}
