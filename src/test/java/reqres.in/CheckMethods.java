package reqres.in;

import io.qameta.allure.Step;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class CheckMethods {

    // Далее методы для позитивного и негативного теста
    @Step("Проверить, что статус код {expectedCode} возвращается в ответе")
    public void checkStatusCode(int expectedCode, int actualCode) {
        assertEquals(expectedCode, actualCode);
    }

    @Step("Убедиться в том, что в ответе присутствуют следующие непустые поля: {userFields}")
    public void checkFields(Map<String, Object> userData, List<String> userFields) {
        for (String field : userFields) {
            assertTrue(userData.containsKey(field), "Поле " + field + " отсутствует в ответе");
            assertNotNull(field, "Поле " + field + " пустое");
        }
    }

    @Step("Убедиться в том, что в ответе не существует следующих полей: {userFields}")
    public void checkMissingFields(Map<String, Object> userData, List<String> userFields) {
        if (userData == null) {
            return;
        }
        for (String field : userFields) {
            assertFalse(userData.containsKey(field), "Поле " + field + " присутствует в ответе");
        }
    }

    @Step("Убедиться, что сообщение об ошибке содержит текст: {errorText}")
    public void checkErrorText(String error, String ErrorText) {
        assertTrue(error.contains(ErrorText), "Поле ошибки не содержит ожидаемого текста");
    }
}