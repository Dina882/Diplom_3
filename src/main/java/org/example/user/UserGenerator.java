package org.example.user;
import org.apache.commons.lang3.RandomStringUtils;

public class UserGenerator {

    public User random() {
        return new User(
                RandomStringUtils.randomAlphanumeric(10) + "@yandex.ru",
                RandomStringUtils.randomAlphanumeric(10),
                RandomStringUtils.randomAlphanumeric(10));
    }
    public User randomBadPwd() {
        return new User(
                RandomStringUtils.randomAlphanumeric(10) + "@yandex.ru",
                RandomStringUtils.randomAlphanumeric(5),
                RandomStringUtils.randomAlphanumeric(10));
    }
}
