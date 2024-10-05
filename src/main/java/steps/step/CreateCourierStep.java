package steps.step;

import orderDetails.Courier;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import steps.api.CreateCourier;

public class CreateCourierStep {

    CreateCourier createCourier = new CreateCourier();


    public Response createStrangeCourier(Courier courier) {
        Response response = createCourier.sendPostCourier(courier);
        return response;
    }
}
