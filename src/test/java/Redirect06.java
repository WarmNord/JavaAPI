import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class Redirect06 {

    @Test
    public void printRedirectUrl() {

        Response response = RestAssured
                .given()
                .redirects()
                .follow(false)
                .when()
                .get(" https://playground.learnqa.ru/api/long_redirect")
                .andReturn();

        String url = response.getHeader("Location");
        System.out.println(url);
    }
}
