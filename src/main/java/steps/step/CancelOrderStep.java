package steps.step;

import io.restassured.response.Response;
import steps.api.CancelOrder;

import java.util.HashMap;
import java.util.Map;

public class CancelOrderStep {


    CancelOrder cancelOrder = new CancelOrder();

    public Response cancelOrder(String track) {

        Map<String, String> trackInfo = new HashMap<>();
        trackInfo.put("track", track);
        Response response = cancelOrder.sendPutCancelOrder(trackInfo);

        return response;

    }

    public Response cancelOrderr(String track) {

        Response response = cancelOrder.sendPutCancelOrderr(track);
        return response;

    }
}
