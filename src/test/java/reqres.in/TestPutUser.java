package reqres.in;

import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestPutUser {
    private PutUser putUser;
    private CheckMethods checkMethods;
    private Map<String, Object> body = new HashMap<>(); // Тело запроса
    private int expectedStatusCode; // ожидаемый статус код
    private List<String> expectedFields = new ArrayList<>(); // Список ожидаемых полей
    {
        expectedFields.add("name");
        expectedFields.add("job");
        expectedFields.add("updatedAt");
    }

    public TestPutUser() {
        this.putUser = new PutUser();
        this.checkMethods = new CheckMethods();
        body.put("name", "John");
        body.put("job", "zion resident");
    }

    @Test
    @Description("Позитивный тест изменения пользователя")
    public void positiveTestPutUserId() {
        putUser.sendPutRequest(body);
        expectedStatusCode = 200;
        checkMethods.checkStatusCode(expectedStatusCode, putUser.getActualStatusCode());
checkMethods.checkFields(putUser.getUserData(), expectedFields);
    }

    @ParameterizedTest
    @ValueSource(strings = {"name", "job"})
    @Description("Негативный тест изменения пользователя с пропуском одного из полей (баг)")
    public void testNegativePutUserId(String field) {
        body.remove(field);
        putUser.sendPutRequest(body);
        expectedStatusCode = 400;
        checkMethods.checkStatusCode(expectedStatusCode, putUser.getActualStatusCode());
    }
}