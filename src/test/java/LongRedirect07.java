import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class LongRedirect07 {

    @Test
    public void longRedirect() {

        int count = 0;
        int statusCode = 0;
        String url = " https://playground.learnqa.ru/api/long_redirect";

        while (statusCode != 200) {

            Response response = RestAssured
                    .given()
                    .redirects()
                    .follow(false)
                    .when()
                    .get(url)
                    .andReturn();

            statusCode = response.getStatusCode();
            url = response.getHeader("Location");

            if (statusCode != 200) {
                System.out.println(url);
                count++;
            }
        }

        System.out.println("Количество редиректов: " + count);

    }
}
