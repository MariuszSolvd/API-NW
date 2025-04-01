package data;

import com.solvd.utilis.models.CreateUser;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.List;

public class TestDataProvider {

    private static final List<String> IDS = new ArrayList<>();

    public static void saveID(String id) {
        IDS.add(id);
    }

    @DataProvider
    public Object[][] userIDs() {
        return new Object[][]{
                {IDS.get(0)},
                {IDS.get(1)},
                {IDS.get(2)},
                {IDS.get(3)}
        };
    }

    @DataProvider
    public Object[][] userProvider() {
        return new Object[][] {
                {new CreateUser("Arnold", "aarnold@mail.com", "male", "active")},
                {new CreateUser("Amanda", "aamanda@mail.com", "female", "active")},
                {new CreateUser("Tommy", "tomtom@mail.com", "male", "active")},
                {new CreateUser("Maria", "maria1989@mail.com", "female", "active")}
        };
    }
}
