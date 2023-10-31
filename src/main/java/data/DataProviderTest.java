package data;

import org.testng.annotations.DataProvider;

/**
 * Класс DataProvider содержит тестовые данные
 */
public class DataProviderTest {
    @DataProvider(name = "fileNamesUserAvatarUniqueData")
    public Object[][] fileNamesUserAvatarUniqueData() {
        return new Object[][]{
                {"page", "2"},
        };
    }

    @DataProvider(name = "userLoginPositiveData")
    public Object[][] userLoginPositiveData() {
        return new Object[][]{
                {"eve.holt@reqres.in", "cityslicka"},
        };
    }

    @DataProvider(name = "userLoginNegativeData")
    public Object[][] userLoginNegativeData() {
        return new Object[][]{
                {"eve.holt@reqres.in", null, "Missing password"},
        };
    }

    @DataProvider(name = "xmlTagCountData")
    public Object[][] xmlTagCountData() {
        return new Object[][]{
                {"https://gateway.autodns.com/", 14},
        };
    }
}
