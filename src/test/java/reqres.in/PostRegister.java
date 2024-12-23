package reqres.in;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class PostRegister {
    private static final Logger log = LoggerFactory.getLogger(PostRegister.class);
    private Response response;
    private String requestUrl = "https://reqres.in/api/register";
    private Map<String, Object> userData; // объект зарегистрированного пользователя
    private String error; // Ошибка при попытке регистрации

    // Метод получения данных пользователя после регистрации
    public Map<String, Object> getUserData() {
        if (userData == null) {
            userData = response.jsonPath().getMap("$");
        }
        return userData;
    }

    // Получить текст ошибки
    public String getErrorText() {
        if (error == null) {
            error = response.jsonPath().getString("error");
        }
        return error;
    }

    @Step("Отправить post запрос на эндпоинт {this.requestUrl} со следующим Json телом:")
    public void sendPostRequest(Map<String, Object> requestBody) {
        response = RestAssured.given().header("Content-Type", "application/json").body(requestBody).post(requestUrl);
    }

    @Step("Получить актуальный статус код")
    public  int getActualStatusCode() {
        return  response.getStatusCode();
    }
}