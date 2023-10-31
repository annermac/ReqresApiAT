package in.reqres;

import helpers.Assert;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import models.ResourceData;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static specification.Spec.requestSpec;
import static specification.Spec.responseSpec200;

/**
 * Класс GetResourcesTests получает GET запросы содержащих ресурсы
 *
 * @author Ермаченкова Анна
 * @version 1.0
 */
@Epic("Получение ресурсов методом GET")
public class GetResourcesTests {
    /**
     * Метод проверяет, что список ресурсов возвращает данные отсортированные по годам
     */
    @Test
    @Story("Проверка, что список ресурсов возвращает данные отсортированные по годам")
    public void resourceListReturnSortedDataByYearTest() {
        ResourceData resource = given(requestSpec())
                .when()
                .get("/api/unknown")
                .then()
                .spec(responseSpec200())
                .extract().body().as(ResourceData.class);

        List<Integer> yearsData = resource.getData().stream()
                .map(data -> Integer.parseInt(data.getYear().toString()))
                .collect(Collectors.toList());

        List<Integer> yearSorted = new ArrayList<>(yearsData);
        Collections.sort(yearSorted);

        Assert.assertEquals(yearSorted, yearsData,
                "Ожидали, что массив отсортирован по годам " + yearSorted + " , но исходный массив " + yearsData);

    }
}