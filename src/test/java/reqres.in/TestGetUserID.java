package reqres.in;

import io.qameta.allure.Description;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.*;
import java.util.stream.Stream;

public class TestGetUserID {
    private GetUser getUser;
    private CheckMethods checkMethods;
    private Map<String, Object> params = new HashMap<>(); // Query параметры
    private static Stream<Object> objectProvider() { // Значения несуществующих пользователей для второго теста
        return Stream.of(-1, 0, 13, 0.125, "abc", "АБВ", "select", "<script");
    }

    private int expectedStatusCode; // ожидаемый статус код
    private List<String> expectedFields = new ArrayList<>(); // Список ожидаемых полей

    {
        expectedFields.add("id");
        expectedFields.add("email");
        expectedFields.add("first_name");
        expectedFields.add("last_name");
        expectedFields.add("avatar");
    }

    public TestGetUserID() {
        this.getUser = new GetUser();
        this.checkMethods = new CheckMethods();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 6, 12})
    @Description("Просмотр существующих пользователей")
    public void TestGetUserIdExist(int userId) {
        params.put("id", userId);
        getUser.sendGetRequest(params);
        expectedStatusCode = 200; // ожидаемый статус код
        checkMethods.checkStatusCode(expectedStatusCode, getUser.getActualStatusCode());
        checkMethods.checkFields(getUser.getUserData(), expectedFields);
        getUser.logRequestAndResponse(); // Логировать запрос и ответ
    }

    @ParameterizedTest
@MethodSource("objectProvider") // Передача нескольких разных значений в параметризованный тест
    @Description("Просмотр несуществующих пользователей")
    public void negativeTestGetUserId(Object userId) {
        params.put("id", userId);
        getUser.sendGetRequest(params);
        expectedStatusCode = 404; // ожидаемый статус код
        checkMethods.checkStatusCode(expectedStatusCode, getUser.getActualStatusCode());
        checkMethods.checkMissingFields(getUser.getUserData(), expectedFields);
        getUser.logRequestAndResponse(); // Логирование запроса и ответа
    }
}