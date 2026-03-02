
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.notNullValue;
import static steps.OrderSteps.*;


public class ListOrderTest extends BaseApiTest {


    @Test
    @DisplayName("List order")
    @Description("Checking the receipt of the order list")
    public void getListOrder() {
        listOrder()
                .then()
                .log().all()
                .statusCode(SC_OK)
                .body("orders", notNullValue());
    }
}

