import data.CourierData;
import model.CourierModel;
import org.junit.Test;

import static data.CourierData.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static steps.CourierSteps.createCourier;


public class CreateCourierTest extends BaseApiTest {


    private String generateUniqueLogin() {
        return LOGIN + System.currentTimeMillis();

    }

    @Test
    public void testCreateCourier() {
        String uniqueLogin = generateUniqueLogin();
        CourierModel courier = new CourierModel(uniqueLogin, PASSWORD, FIRSTNAME);
        createCourier(courier)
                .then()
                .log().all()
                .statusCode(201)
                .body("ok", equalTo(true));
    }

    @Test
    public void testCreateCourierWithSameLogin() {
        String uniqueLogin = generateUniqueLogin();
        CourierModel courier = new CourierModel(uniqueLogin, PASSWORD, FIRSTNAME);
        createCourier(courier)
                .then()
                .log().all()
                .statusCode(201)
                .body("ok", equalTo(true));

        createCourier(courier)
                .then()
                .log().all()
                //.statusCode(409)
                .body("message", equalTo(ERROR_LOGIN_ALREADY_USED ));

    }

    @Test
    public void testCreateCourierWithoutLogin() {
        CourierModel courier = new CourierModel(null, PASSWORD, FIRSTNAME);
        createCourier(courier)
                .then()
                .log().all()
                .statusCode(400)
                .body("message", equalTo(CourierData.ERROR_INSUFFICIENT_DATA));
    }

    @Test
    public void testCreateCourierWithoutPassword() {
        String uniqueLogin = generateUniqueLogin();
        CourierModel courier = new CourierModel(uniqueLogin, null, FIRSTNAME);
        createCourier(courier)
                .then()
                .log().all()
                .statusCode(400)
                .body("message", equalTo(CourierData.ERROR_INSUFFICIENT_DATA));
    }

    @Test
    public void testCreateCourierWithoutFirstname() {
        String uniqueLogin = generateUniqueLogin();
        CourierModel courier = new CourierModel(uniqueLogin, PASSWORD, null);
        createCourier(courier)
                .then()
                .log().all()
                .statusCode(400)
                .body("message", equalTo(CourierData.ERROR_INSUFFICIENT_DATA));
    }
}


