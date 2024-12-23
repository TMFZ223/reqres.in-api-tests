package reqres.in;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class GetUser {
    private static final Logger log = LoggerFactory.getLogger(GetUser.class);
    private Response response;
    private String requestUrl = "https://reqres.in/api/users/";
private  Map<String, Object> userData;

    public Map<String, Object> getUserData() {
        if (userData == null) {
            userData = response.jsonPath().getMap("data");
        }
        return userData;
    }

    // Отправка get запроса на эндпоинт
    @Step("отправить get запрос на эндпоинт {this.requestUrl} со следующими query параметрами: {queryParams}")
    public void sendGetRequest(Map<String, Object> queryParams) {
        response = RestAssured.given().queryParams(queryParams).get(requestUrl);
    }

    @Step("Получить актуальный статус код")
    public  int getActualStatusCode() {
        return  response.getStatusCode();
    }
    @Step("Логировать запрос и ответ")
    public void logRequestAndResponse() {
        if (response != null) {
            log.info("Получен статус код: {}", response.getStatusCode());
            log.info("Тело ответа: {}", response.getBody().asString());
        } else {
            log.info("Ответ не был получен");
        }
    }
}