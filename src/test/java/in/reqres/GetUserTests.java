package in.reqres;

import data.DataProviderTest;
import helpers.Assert;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import models.User;
import models.UserData;
import org.apache.commons.io.FilenameUtils;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import static io.restassured.RestAssured.given;
import static specification.Spec.requestSpec;
import static specification.Spec.responseSpec200;

/**
 * Класс GetUserTests получает GET запросы содержащих пользователей
 *
 * @author Ермаченкова Анна
 * @version 1.0
 */
@Epic("Получение пользователей методом GET")
public class GetUserTests {
    /**
     * Метод проверяет уникальность имена файлов аватаров пользователей
     *
     * @param param переменная url
     * @param value значение переменной url
     */
    @Test(dataProvider = "fileNamesUserAvatarUniqueData", dataProviderClass = DataProviderTest.class)
    @Story("Проверка уникальности имена файлов аватаров пользователей")
    public void fileNamesUserAvatarUniqueTest(String param, String value) throws MalformedURLException {
        UserData userData = given(requestSpec())
                .when()
                .queryParam(param, value)
                .get("/api/users")
                .then()
                .spec(responseSpec200())
                .extract().body().as(UserData.class);

        URL url;
        Set<String> avatars = new HashSet<>();
        for (User user : userData.getData()) {
            url = new URL(user.getAvatar());
            String avatar = FilenameUtils.getName(url.getPath());
            Assert.assertFalse(avatars.contains(avatar),
                    "Аватар пользователя ID:" + user.getId() + " не уникален");
            avatars.add(avatar);
        }
    }
}
