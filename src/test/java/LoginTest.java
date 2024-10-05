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
import steps.step.LoginCourierStep;

public class LoginTest {

    LoginCourierStep loginCourierStep = new LoginCourierStep();
    CreateCourierStep courierStep = new CreateCourierStep();
    MainThesis thesis = new MainThesis();
    DeleteCourierStep deleteCourierStep = new DeleteCourierStep();

    Courier newCourier;

    @Before
    public void setUp() {

        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
        newCourier = new Courier(
                RandomStringUtils.randomAlphabetic(10),
                RandomStringUtils.randomAlphabetic(10),
                RandomStringUtils.randomAlphabetic(10));
        courierStep.createStrangeCourier(newCourier);
    }

    @After
    public void tearDown() {
        deleteCourierStep.deleteCourier(newCourier);
    }

    @Test
    @DisplayName("Логин курьера: без обязательного поля - логин")
    @Description("Отрицательный тест для проверки того, что без обязательного входа в систему невозможно авторизоваться курьером")

    public void loginCourierWithoutMandatoryLoginImpossibleTest() {

        Response response = loginCourierStep.logInWithoutLogin(newCourier);
        thesis.checkResponseStatus(response, 400)
                .checkResponseBodyFieldValue(response, "message", "Недостаточно данных для входа");

    }

    @Test
    @DisplayName("Логин курьера: базовый тест")
    @Description("Базовый тест для входа в систему с помощью созданного курьера")

    public void loginCourierTest() {

        Response response = loginCourierStep.courierEntrySuccessful(newCourier);
        thesis.checkResponseStatus(response, 200)
                .checkResponseBodyField(response, "id");

    }


    @Test
    @DisplayName("Логин курьера: неверные значения пароля")
    @Description("Отрицательный результат теста для проверки того, что при неправильных значениях пароля невозможно войти в систему курьера")

    public void loginCourierWithIncorrectPasswordImpossibleTest() {

        Response response = loginCourierStep.logInWithInvalidPassword(newCourier);
        thesis.checkResponseStatus(response, 404)
                .checkResponseBodyFieldValue(response, "message", "Учетная запись не найдена");

    }

    @Test
    @DisplayName("Логин курьера: неверные значения для входа в систему")
    @Description("Отрицательный результат теста для проверки того, что при неправильных значениях для входа в систему невозможно войти в систему курьера")

    public void loginCourierWithIncorrectLoginImpossibleTest() {


        Response response = loginCourierStep.logInWithInvalidLogin(newCourier);
        thesis.checkResponseStatus(response, 404)
                .checkResponseBodyFieldValue(response, "message", "Учетная запись не найдена");

    }

}
