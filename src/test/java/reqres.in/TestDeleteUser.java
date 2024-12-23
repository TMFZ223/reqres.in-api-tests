package reqres.in;

import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;

public class TestDeleteUser {
    private  DeleteUser deleteUser;
private  CheckMethods checkMethods;

    public TestDeleteUser() {
        this.deleteUser = new DeleteUser();
        this.checkMethods = new CheckMethods();
    }

    @Test
    @Description("Удаление существующего пользователя")
    public void positiveTestDeleteUser() {
        deleteUser.sendDeleteRequest(10);
        checkMethods.checkStatusCode(204, deleteUser.getActualStatusCode());
    }
@Test
    @Description("Удаление пользователя с некорректным ID (Баг)")
    public  void  negativeTestDeleteUser() {
        deleteUser.sendDeleteRequest("sunday");
        checkMethods.checkStatusCode(404, deleteUser.getActualStatusCode());
}
}