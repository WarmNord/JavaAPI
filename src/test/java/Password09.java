import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Password09 {

    String loginValue = "super_admin";
    String auth_cookie = "auth_cookie";
    List<String> passwords = Arrays.asList("1234567", "123456", "12345", "1234", "12345678", "123456789",
            "1234567890", "password", "qwerty", "abc123", "football", "monkey", "111111", "letmein", "dragon",
            "baseball", "sunshine", "iloveyou", "trustno1", "princess", "adobe123", "123123", "welcome", "login",
            "admin", "qwerty123", "solo", "1q2w3e4r", "666666", "master", "photoshop", "1qaz2wsx", "qwertyuiop",
            "ashley", "mustang", "121212", "starwars", "654321", "bailey", "access", "flower", "555555", "shadow",
            "passw0rd", "lovely", "7777777", "michael", "!@#$%^&*\t", "jesus", "password1", "superman", "hello",
            "charlie", "888888", "696969", "qwertyuiop", "hottie", "freedom", "aa123456", "qazwsx", "ninja",
            "azerty", "loveme", "whatever", "donald", "batman", "zaq1zaq1", "Football", "000000", "123qwe");

    String url_get_secret_psw = "https://playground.learnqa.ru/ajax/api/get_secret_password_homework";
    String url_check_auth = "https://playground.learnqa.ru/ajax/api/check_auth_cookie";


    @Test
    public void findPassword() {

        for (int i = 0; i < passwords.size() - 1; i++) {

            Map<String, String> params = new HashMap<>();
            params.put("login", loginValue);
            params.put("password", passwords.get(i));


            Response reqGetPsw = RestAssured
                    .given()
                    .body(params)
                    .when()
                    .post(url_get_secret_psw)
                    .andReturn();

            String responseCookie = reqGetPsw.getCookie(auth_cookie);

            Map<String, String> cookies = new HashMap<>();

            if (reqGetPsw != null) {
                cookies.put("auth_cookie", responseCookie);
            }


            Response check_auth = RestAssured
                    .given()
                    .body(params)
                    .cookie(auth_cookie, responseCookie)
                    .when()
                    .post(url_check_auth)
                    .andReturn();

            String response = check_auth.asString();
            /** You are authorized */


            if (response.equals("You are authorized")) {
                System.out.println(response);
                System.out.println("Пароль: " + params.get("password"));
                break;
            }

        }

    }

}
