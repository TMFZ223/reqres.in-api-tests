package reqres.in;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class PutUser {
    private Response response;
    private String requestUrl = "https://reqres.in/api/user/2";
    private Map<String, Object> userData; // объект зарегистрированного пользователя

    // Метод получения данных пользователя после регистрации
    public Map<String, Object> getUserData() {
        if (userData == null) {
            userData = response.jsonPath().getMap("$");
        }
        return userData;
    }

    @Step("Отправить put запрос на эндпоинт {this.requestUrl} со следующим Json телом:")
    public void sendPutRequest(Map<String, Object> requestBody) {
        response = RestAssured.given().header("Content-Type", "application/json").body(requestBody).put(requestUrl);
    }

    @Step("Получить актуальный статус код")
    public  int getActualStatusCode() {
        return  response.getStatusCode();
    }
}