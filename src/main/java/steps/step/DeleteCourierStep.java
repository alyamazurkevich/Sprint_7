package steps.step;

import orderDetails.Courier;
import orderDetails.Login;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import steps.api.DeleteCourier;
import steps.api.LoginCourier;

public class DeleteCourierStep {

    DeleteCourier deleteCourier = new DeleteCourier();
    LoginCourier loginCourier = new LoginCourier();

    public Response deleteCourier (Courier courier) {

        Login login = new Login(courier.getLogin(), courier.getPassword());
        JsonPath responseLogin = loginCourier.sendPostLoginCourier(login).jsonPath();
        String id = responseLogin.getString("id");
        Response responseDelete = deleteCourier.sendDeleteCourier(id);

        return responseDelete;

    }
}
