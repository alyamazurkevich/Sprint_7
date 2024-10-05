package steps.api;

import orderDetails.CreationOrder;
import io.qameta.allure.Step;
import io.restassured.response.Response;


import static io.restassured.RestAssured.given;

public class CreateOrder {

    @Step("Send POST request to /api/v1/orders")
    public Response sendPostOrder(CreationOrder creationorder) {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(creationorder)
                .when()
                .post("/api/v1/orders");
        return response;
    }
}
