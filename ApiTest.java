import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ApiTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void testGetUser() {
        Response response = RestAssured.get("/users/1");
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getInt("id"), 1);
    }

    @Test
    public void testCreateUser() {
        String requestBody = "{ \"name\": \"John Doe\", \"email\": \"john.doe@example.com\" }";
        Response response = RestAssured.given()
                                       .header("Content-Type", "application/json")
                                       .body(requestBody)
                                       .post("/users");
        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertTrue(response.jsonPath().getString("name").equals("John Doe"));
    }
}
