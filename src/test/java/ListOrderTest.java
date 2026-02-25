
import org.junit.Test;
import static org.hamcrest.CoreMatchers.notNullValue;
import static steps.OrderSteps.*;


public class ListOrderTest extends BaseApiTest {


    @Test
    public void getListOrder() {
        listOrder()
                .then()
                .log().all()
                .statusCode(200)
                .body("orders", notNullValue());
    }
}

