package thesis;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.Assert;
import static org.hamcrest.Matchers.*;

public class MainThesis {
    @Step("Проверьте статус ответа")
    public MainThesis checkResponseStatus(Response response, int statusCode) {
        response.then().statusCode(statusCode);
        return this;

    }

    @Step("Проверьте текст ответа")
    public MainThesis  checkResponseBody(Response response, String expectedMessage) {
        Assert.assertEquals(expectedMessage, response.asString());
        return this;
    }

    @Step("Проверьте значение поля основной части ответа")
    public MainThesis  checkResponseBodyFieldValue(Response response, String field, String expectedValue) {
        Assert.assertEquals(expectedValue, response.jsonPath().getString(field));
        return this;

    }

    @Step("Проверьте, чтобы поле текста ответа не было пустым")
    public MainThesis  checkResponseBodyFieldNotNull(Response response, String fieldName) {
        response.then().assertThat().body(fieldName, notNullValue());
        return this;
    }

    @Step("Проверка поля текст ответа")
    public MainThesis  checkResponseBodyField(Response response, String fieldName) {
        response.then().assertThat().body("$", hasKey(fieldName));
        return this;
    }
}
