package org.example.user;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.example.Client;

public class UserClient extends Client {
    public static final String ROOT_LOGIN = "/auth/login";
    public static final String ROOT_USER = "/auth/user";

    @Step("Получить accessToken пользователя")
    public String getAccessTokenOnLogin(User user) {
        return spec()
                .body(user)
                .when()
                .post(ROOT_LOGIN)
                .then().log().all()
                .extract()
                .path("accessToken");
    }
    @Step("Удалить пользователя по accessToken")
    public ValidatableResponse delete(String accessToken) {
        return spec()
                .auth().oauth2(accessToken.replace("Bearer ", ""))
                .when()
                .delete(ROOT_USER)
                .then().log().all();
    }
}
