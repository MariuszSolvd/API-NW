package tests;

import com.solvd.utilis.ConfigReader;
import com.solvd.utilis.models.CreateUser;
import data.TestDataProvider;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

public class ApiTests {

    @BeforeClass
    public void setUp() {
        baseURI = ConfigReader.get("url");
    }

    @Test
    public void usersListShouldNotBeEmpty() {
        when()
            .get("users")
        .then()
            .statusCode(200)
            .body("size()", greaterThan(0));
    }

    @Test(dataProvider = "userIDs", dataProviderClass = TestDataProvider.class)
    public void getUserById(String id) {
        given()
            .pathParam("userID", id)
        .when()
            .get("users/{userID}")
        .then()
            .statusCode(200)
            .body("id", equalTo(Integer.parseInt(id)));
    }

    @Test(dataProvider = "userProvider", dataProviderClass = TestDataProvider.class)
    public void createUser(CreateUser user) {
        given()
            .auth().oauth2(ConfigReader.get("token"))
            .contentType(ContentType.JSON)
            .body(user)
        .when()
            .post("users")
        .then().log().body()
            .statusCode(201)
            .body("name", equalTo(user.name()))
            .body("email", equalTo(user.email()))
            .body("gender", equalTo(user.gender()))
            .body("status", equalTo(user.status()));
    }
}
