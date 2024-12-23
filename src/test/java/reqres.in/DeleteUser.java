package reqres.in;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DeleteUser {
    private Response response;
    private String requestUrl = "https://reqres.in/api/users/";

    @Step("Отправить delete запрос на эндпоинт {this.requestUrl}")
    public  void  sendDeleteRequest(Object id) {
      String fullUrl = requestUrl + id;
        response = RestAssured.given().delete(fullUrl);
    }

    @Step("Получить актуальный статус код")
    public  int getActualStatusCode() {
        return  response.getStatusCode();
    }
}