import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Tokens08 {

    @Test
    public void doDoDo() throws InterruptedException {

        String token;
        int seconds;
        String url = "https://playground.learnqa.ru/ajax/api/longtime_job";
        String status;

        /** 01  create task */
        JsonPath createTask = RestAssured
                .given()
                .get(url)
                .jsonPath();

        token = createTask.get("token");
        seconds = createTask.get("seconds");


        /** 2) делал один запрос с token ДО того, как задача готова, убеждался в правильности поля status */
        JsonPath reqBeforeTaskDone = RestAssured
                .given()
                .queryParams("token", token)
                .get(url)
                .jsonPath();

        status = reqBeforeTaskDone.get("status");
        System.out.println();
        Assertions.assertEquals("Job is NOT ready", status);

        /** 3) ждал нужное количество секунд с помощью функции Thread.sleep()  */
        Thread.sleep(seconds * 1000);

        /** 4) делал бы один запрос c token ПОСЛЕ того, как задача готова, убеждался в правильности поля status и наличии поля result */
        JsonPath reqAfterTaskDone = RestAssured
                .given()
                .queryParams("token", token)
                .get(url)
                .jsonPath();

        status = reqAfterTaskDone.get("status");
        String result = reqAfterTaskDone.get("result");

        Assertions.assertEquals("Job is ready", status);
        Assertions.assertEquals("42", result);

    }
}
