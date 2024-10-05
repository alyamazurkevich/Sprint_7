package steps.api;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DeleteCourier {

    @Step("Send DELETE request to /api/v1/courier/")
    public Response sendDeleteCourier(String id){
        Response response =given()
                .header("Content-type", "application/json")
                .and()
                .delete("/api/v1/courier/" + id);
        return response;
    }
}
