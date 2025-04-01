package data;

import com.solvd.utilis.models.CreateUser;
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

    @DataProvider
    public Object[][] userProvider() {
        return new Object[][] {
                {new CreateUser("Arnold", "arnold@mail.com", "male", "active")},
                {new CreateUser("Amanda", "amanda@mail.com", "female", "active")}
        };
    }
}
