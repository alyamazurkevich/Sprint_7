import thesis.MainThesis;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized;
import steps.step.CancelOrderStep;
import steps.step.CreateOrderStep;

import java.util.Arrays;
import java.util.List;


public class OrderTest {

    List<String> colour;

    public OrderTest(List<String> colour) {
        this.colour = colour;
    }

    @Parameterized.Parameters
    public static Object[][] createOrder() {
        return new Object[][]{
                {Arrays.asList("BACK")},
                {Arrays.asList("WHITE")},
                {Arrays.asList("WHITE", "BLACK")},
                {Arrays.asList()}

        };
    }

    CreateOrderStep createOrderStep = new CreateOrderStep();
    MainThesis thesis = new MainThesis();
    CancelOrderStep cancelOrderStep = new CancelOrderStep();

    String track;

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
    }

    @After
    public void tearDown() {
        cancelOrderStep.cancelOrderr(track);
    }

    @Test
    @DisplayName("Создание заказа: проверка цвета")
    @Description("Создание заказа с выбранным цветом")
    public void createOrderWithColourTest() {

        Response response = createOrderStep.createOrderWithColor(colour);
        thesis.checkResponseStatus(response, 201)
                .checkResponseBodyField(response, "track");
        track = response.jsonPath().getString("track");
    }
}
