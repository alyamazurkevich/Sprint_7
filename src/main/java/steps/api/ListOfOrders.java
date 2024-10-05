package steps.api;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ListOfOrders {

    @Step("Send GET request to /api/v1/orders")
    public Response sendListOfOrders(){
        Response response =given()
                .header("Content-type", "application/json")
                .and()
                .get("/api/v1/orders");
        return response;
    }
}
