package com.autodns.gateway;

import data.DataProviderTest;
import helpers.Assert;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import io.restassured.RestAssured;

import org.testng.annotations.Test;
import org.w3c.dom.Document;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

/**
 * Класс TagCountTest получает данные XML c помощью GET запроса и проверяет ожидаемое количество тегов
 *
 * @author Ермаченкова Анна
 * @version 1.0
 */
@Epic("Сайт содержащий XML")
public class TagCountTest {
    /**
     * Метод считает количество тегов с сайта содержащего XML
     *
     * @param url              ссылка на сайт
     * @param expectedTagCount ожидаемое количество тегов
     */
    @Story("Посчитать количество тегов с сайта содержащего XML")
    @Test(dataProvider = "xmlTagCountData", dataProviderClass = DataProviderTest.class)
    public void testTagCount(String url, int expectedTagCount) throws IOException, SAXException, ParserConfigurationException {
        String response = RestAssured.given()
                .when()
                .get(url)
                .then()
                .extract()
                .asString();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource source = new InputSource(new StringReader(response));
        Document document = builder.parse(source);
        int actualTagCount = document.getElementsByTagName("*").getLength();

        Assert.assertEquals(actualTagCount, expectedTagCount,
                "Ожидалось, что количество тегов будет равно " + expectedTagCount + ", но получили " + actualTagCount);
    }
}
