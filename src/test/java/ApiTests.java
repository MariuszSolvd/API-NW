import com.solvd.utilis.ConfigReader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.greaterThan;

public class ApiTests {

    @BeforeClass
    public void setUp() {
        baseURI = ConfigReader.get("url");
    }

    @Test
    public void usersListShouldNotBeEmpty() {
        when().get("users")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }
}
