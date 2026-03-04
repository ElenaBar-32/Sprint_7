import data.CourierData;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import model.CourierLoginModel;
import model.CourierModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static data.CourierData.*;
import static org.apache.http.HttpStatus.*;
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
    @DisplayName ("Success login courier")
    @Description ("Check successful courier authorization")
    public void testSuccessLoginCourier() {
        CourierLoginModel login = new CourierLoginModel(LOGIN, PASSWORD);
        loginCourier(login)
                 .then()
                .log().all()
                .statusCode(SC_OK)
                .body("id", (notNullValue()));

    }

    @Test
    @DisplayName ("Courier without login")
    @Description ("Checking courier authorization without login")
    public void testLoginCourierWithoutLogin() {
        CourierLoginModel login = new CourierLoginModel(null, PASSWORD);
        loginCourier(login)
                .then()
                .log().all()
                .statusCode(SC_BAD_REQUEST)
                .body("message", equalTo(ERROR_INCORRECT_DATA));
    }

    @Test
    @DisplayName ("Courier without password")
    @Description ("Checking courier authorization without password")
    public void testLoginCourierWithoutPassword() {
        CourierLoginModel login = new CourierLoginModel(LOGIN, null);
        loginCourier(login)
                .then()
                .log().all()
                .statusCode(SC_BAD_REQUEST)
                .body("message", equalTo(ERROR_INCORRECT_DATA));
    }

    @Test
    @DisplayName ("Courier without password and login")
    @Description ("Checking courier authorization without password and without login")
    public void testLoginCourierWithoutPasswordAndLogin() {
        CourierLoginModel login = new CourierLoginModel(null, null);
        loginCourier(login)
                .then()
                .log().all()
                .statusCode(SC_BAD_REQUEST)
                .body("message", equalTo(ERROR_INCORRECT_DATA));

    }

    @Test
    @DisplayName ("Login courier non-existent password")
    @Description ("Checking courier authorization with a non-existent password")
    public void testLoginCourierNonExistentPassword() {
        CourierLoginModel login = new CourierLoginModel(LOGIN, PASSWORD + "new");
        loginCourier(login)
                .then()
                .log().all()
                .statusCode(SC_NOT_FOUND)
                .body("message", equalTo(CourierData.ERROR_ACCOUNT_NOT_FOUND));
    }

    @Test
    @DisplayName ("Login courier non-existent login")
    @Description ("Checking courier authorization with a non-existent login")
    public void testLoginCourierNonExistentLogin() {
        CourierLoginModel login = new CourierLoginModel(LOGIN + "new", PASSWORD);
        loginCourier(login)
                .then()
                .log().all()
                .statusCode(SC_NOT_FOUND)
                .body("message", equalTo(ERROR_ACCOUNT_NOT_FOUND));
    }

    @Test
    @DisplayName ("Login courier non-existent login and password")
    @Description ("Checking courier authorization with a non-existent login and password")
    public void testLoginCourierNonExistentLoginAndPassword() {
        CourierLoginModel login = new CourierLoginModel(LOGIN + "new", PASSWORD + "new");
        loginCourier(login)
                .then()
                .log().all()
                .statusCode(SC_NOT_FOUND)
                .body("message", equalTo(ERROR_ACCOUNT_NOT_FOUND));
    }

    @After
    public void cleanUp() {
        if (id != null) {
            deleteCourierById(id);
        }
    }
}



