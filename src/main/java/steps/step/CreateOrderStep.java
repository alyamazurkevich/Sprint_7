package steps.step;

import orderDetails.CreationOrder;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import steps.api.CreateOrder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class CreateOrderStep {

    CreateOrder createOrder = new CreateOrder();

    String randomString = RandomStringUtils.randomAlphabetic(10);
    int randomInt = new Random().nextInt(10);
    String date =  new SimpleDateFormat("yyyy-MM-dd").format(new Date());


    public Response createOrderWithColor(List<String> colour) {
        CreationOrder creationorder = new CreationOrder (
                randomString,
                randomString,
                randomString,
                randomInt,
                randomString,
                randomInt,
                date,
                randomString,
                colour
        );
        Response response = createOrder.sendPostOrder(creationorder);
        return response;
    }
}
