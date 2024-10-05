package steps.step;

import orderDetails.Courier;
import orderDetails.Login;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import steps.api.LoginCourier;

public class LoginCourierStep {

    LoginCourier loginCourier = new LoginCourier();

    public Response courierEntrySuccessful(Courier courier) {
        Login login = new Login(courier.getLogin(), courier.getPassword());
        Response response = loginCourier.sendPostLoginCourier(login);
        return response;
    }

    public Response logInWithoutLogin(Courier courier) {
        Login login = new Login(null, courier.getPassword());
        Response response = loginCourier.sendPostLoginCourier(login);
        return response;
    }

    public Response logInWithInvalidLogin(Courier courier) {
        Login login = new Login(courier.getLogin(), RandomStringUtils.randomAlphabetic(10));
        Response response = loginCourier.sendPostLoginCourier(login);
        return response;
    }

    public Response logInWithoutPassword(Courier courier) {
        Login login = new Login(courier.getLogin(), null);
        Response response = loginCourier.sendPostLoginCourier(login);
        return response;
    }

    public Response logInWithInvalidPassword(Courier courier) {
        Login login = new Login(RandomStringUtils.randomAlphabetic(10), courier.getPassword());
        Response response = loginCourier.sendPostLoginCourier(login);
        return response;
    }
}
