package tests;

import com.solvd.utilis.ConfigReader;
import com.solvd.utilis.models.CreateUser;
import data.TestDataProvider;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

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
        Response response = when()
            .get("users");

        List<String> ids = response.jsonPath().getList("id", String.class);
        ids.forEach(TestDataProvider::saveID);

        response.then()
            .statusCode(200)
            .body("size()", greaterThan(0));
    }

    @Test(dataProvider = "userIDs", dataProviderClass = TestDataProvider.class, dependsOnMethods = "usersListShouldNotBeEmpty")
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
        .then()
            .statusCode(201)
            .body("name", equalTo(user.name()))
            .body("email", equalTo(user.email()))
            .body("gender", equalTo(user.gender()))
            .body("status", equalTo(user.status()));
    }

    @Test(dataProvider = "userIDs", dataProviderClass = TestDataProvider.class)
    public void changeStatusForUser(String id) {
        given()
                .auth().oauth2(ConfigReader.get("token"))
                .pathParam("userId", id)
                .body("{\"status\": \"inactive\"}")
        .when()
                .put("users/{userId")
        .then()
                .statusCode(201)
                .body("id", equalTo(Integer.parseInt(id)))
                .body("status", equalTo("inactive"));
    }
}
