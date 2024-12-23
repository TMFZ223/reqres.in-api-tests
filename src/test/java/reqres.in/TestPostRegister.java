package reqres.in;

import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestPostRegister {
    private  PostRegister postRegister;
    private  CheckMethods checkMethods;
    private Map<String, Object> body = new HashMap<>(); // Тело запроса
    private int expectedStatusCode; // ожидаемый статус код
    private List<String> expectedFields = new ArrayList<>(); // Список ожидаемых полей
    {
        expectedFields.add("id");
        expectedFields.add("token");
    }

    public  TestPostRegister() {
        this.postRegister = new PostRegister();
        this.checkMethods = new CheckMethods();
        body.put("email", "eve.holt@reqres.in");
        body.put("password", "pistol");
    }

    @Test
    @Description("Позитивный тест регистрации пользователя")
    public void positiveTestRegister() {
        postRegister.sendPostRequest(body);
        expectedStatusCode = 200;
        checkMethods.checkStatusCode(expectedStatusCode, postRegister.getActualStatusCode());
checkMethods.checkFields(postRegister.getUserData(), expectedFields);
    }

    @ParameterizedTest
    @CsvSource({"'email', 'Missing email or username'", "'password', 'Missing password'"})
    @Description("Негативный тест регистрации пользователя с пропуском одного из полей")
    public  void  testNegativeRegister(String field, String expectedErrorText) {
        body.remove(field);
        postRegister.sendPostRequest(body);
        expectedStatusCode = 400;
        checkMethods.checkStatusCode(expectedStatusCode, postRegister.getActualStatusCode());
        checkMethods.checkErrorText(postRegister.getErrorText(), expectedErrorText);
    }
}