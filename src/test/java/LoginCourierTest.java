import data.CourierData;
import model.CourierLoginModel;
import model.CourierModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static data.CourierData.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static steps.CourierSteps.*;

public class LoginCourierTest extends BaseApiTest {


    Integer id;


    @Before
    public void start() {
        CourierModel courier = new CourierModel(LOGIN, PASSWORD, FIRSTNAME);
        createCourier(courier);
    }

    @Test
    public void testSuccessLoginCourier() {
        CourierLoginModel login = new CourierLoginModel(LOGIN, PASSWORD);
        loginCourier(login)
                 .then()
                .log().all()
                .statusCode(200)
                .body("id", (notNullValue()));

    }

    @Test
    public void testLoginCourierWithoutLogin() {
        CourierLoginModel login = new CourierLoginModel(null, PASSWORD);
        loginCourier(login)
                .then()
                .log().all()
                .statusCode(400)
                .body("message", equalTo(ERROR_INCORRECT_DATA));
    }

    @Test
    public void testLoginCourierWithoutPassword() {
        CourierLoginModel login = new CourierLoginModel(LOGIN, null);
        loginCourier(login)
                .then()
                .log().all()
                .statusCode(400)
                .body("message", equalTo(ERROR_INCORRECT_DATA));
    }

    @Test
    public void testLoginCourierWithoutPasswordAndLogin() {
        CourierLoginModel login = new CourierLoginModel(null, null);
        loginCourier(login)
                .then()
                .log().all()
                .statusCode(400)
                .body("message", equalTo(ERROR_INCORRECT_DATA));

    }

    @Test
    public void testLoginCourierNonExistentPassword() {
        CourierLoginModel login = new CourierLoginModel(LOGIN, PASSWORD + "new");
        loginCourier(login)
                .then()
                .log().all()
                .statusCode(404)
                .body("message", equalTo(CourierData.ERROR_ACCOUNT_NOT_FOUND));
    }

    @Test
    public void testLoginCourierNonExistentLogin() {
        CourierLoginModel login = new CourierLoginModel(LOGIN + "new", PASSWORD);
        loginCourier(login)
                .then()
                .log().all()
                .statusCode(404)
                .body("message", equalTo(ERROR_ACCOUNT_NOT_FOUND));
    }

    @Test
    public void testLoginCourierNonExistentLoginAndPassword() {
        CourierLoginModel login = new CourierLoginModel(LOGIN + "new", PASSWORD + "new");
        loginCourier(login)
                .then()
                .log().all()
                .statusCode(404)
                .body("message", equalTo(ERROR_ACCOUNT_NOT_FOUND));
    }

    @After
    public void cleanUp() {
        if (id != null) {
            deleteCourierById(id);
        }
    }
}



