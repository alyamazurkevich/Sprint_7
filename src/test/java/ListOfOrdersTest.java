import thesis.MainThesis;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import steps.api.ListOfOrders;

public class ListOfOrdersTest {
    ListOfOrders listOfOrders = new ListOfOrders();
    MainThesis thesis = new  MainThesis();

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
    }

    @Test
    @DisplayName("Получить список заказов: базовый тест")
    @Description("Базовый тест для получения списка заказов")
    public void listOfOrdersTest () {
        Response response = listOfOrders.sendListOfOrders();
        thesis.checkResponseStatus(response, 200)
                .checkResponseBodyFieldNotNull(response, "orders");


    }
}
