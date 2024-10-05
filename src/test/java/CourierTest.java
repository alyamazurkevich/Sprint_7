import thesis.MainThesis;
import orderDetails.Courier;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import steps.step.CreateCourierStep;
import steps.step.DeleteCourierStep;

public class CourierTest extends MainThesis {

    CreateCourierStep courierStep = new CreateCourierStep();
    MainThesis thesis = new MainThesis();
    DeleteCourierStep deleteCourierStep = new DeleteCourierStep();

    Courier newCourier;

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    @After
    public void tearDown() {
        deleteCourierStep.deleteCourier(newCourier);
    }


    @Test
    @DisplayName("Создать курьера: курьер со всеми полями")
    @Description("Создание курьера со всеми полями")

    public void createCourierTest() {
        newCourier = new Courier(
                RandomStringUtils.randomAlphabetic(10),
                RandomStringUtils.randomAlphabetic(10),
                RandomStringUtils.randomAlphabetic(10));
        Response response = courierStep.createStrangeCourier(newCourier);
        thesis.checkResponseStatus(response, 201)
                .checkResponseBody(response, "{\"ok\":true}");

    }

    @Test
    @DisplayName("Создать курьера: курьер с обязательными для заполнения полями")
    @Description("Создать курьера без имени")

    public void createCourierNotNameTest() {
        newCourier = new Courier(
                RandomStringUtils.randomAlphabetic(10),
                RandomStringUtils.randomAlphabetic(10),
                null);
        Response response = courierStep.createStrangeCourier(newCourier);
        thesis.checkResponseStatus(response, 201)
                .checkResponseBody(response, "{\"ok\":true}");

    }

    @Test
    @DisplayName("Создать курьера: без обязательного поля")
    @Description("Отрицательный тест для проверки того, что без обязательного поля курьер не создан")

    public void createCourierWithoutMandatoryFieldTest() {
        newCourier = new Courier(
                null,
                RandomStringUtils.randomAlphabetic(10),
                RandomStringUtils.randomAlphabetic(10));
        Response response = courierStep.createStrangeCourier(newCourier);
        thesis.checkResponseStatus(response, 400)
                .checkResponseBodyFieldValue(response, "message", "Недостаточно данных для создания учетной записи");

    }

    @Test
    @DisplayName("Создать курьера: дублировать курьера")
    @Description("Отрицательный результат теста для проверки того, что курьер не был создан с тем же логином")

    public void createDuplicateCourierImpossibleTest() {
        newCourier = new Courier(
                RandomStringUtils.randomAlphabetic(10),
                RandomStringUtils.randomAlphabetic(10),
                RandomStringUtils.randomAlphabetic(10));

        courierStep.createStrangeCourier(newCourier);

        Courier duplicateCourier = new Courier(
                newCourier.getLogin(),
                RandomStringUtils.randomAlphabetic(10),
                RandomStringUtils.randomAlphabetic(10));
        Response response = courierStep.createStrangeCourier(duplicateCourier);
        thesis.checkResponseStatus(response, 409)
                .checkResponseBodyFieldValue(response, "message", "Этот логин уже используется. Попробуйте другой.");

        deleteCourierStep.deleteCourier(duplicateCourier);
    }
}