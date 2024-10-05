package steps.api;

import orderDetails.Login;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class LoginCourier {

    @Step("Send POST request to /api/v1/courier/login")
    public Response sendPostLoginCourier(Login login){
        Response response =given()
                .header("Content-type", "application/json")
                .and()
                .body(login)
                .when()
                .post("/api/v1/courier/login");
        return response;
    }
}
