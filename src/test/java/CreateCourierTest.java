import data.CourierData;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import model.CourierModel;
import org.junit.Test;

import static data.CourierData.*;
import static java.net.HttpURLConnection.HTTP_CREATED;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static steps.CourierSteps.createCourier;


public class CreateCourierTest extends BaseApiTest {


    private String generateUniqueLogin() {
        return LOGIN + System.currentTimeMillis();

    }

    @Test
    @DisplayName("Create courier")
    @Description("Checking the successful creation of a courier")
    public void testCreateCourier() {
        String uniqueLogin = generateUniqueLogin();
        CourierModel courier = new CourierModel(uniqueLogin, PASSWORD, FIRSTNAME);
        createCourier(courier)
                .then()
                .log().all()
                .statusCode(HTTP_CREATED)
                .body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Create courier with same login")
    @Description("Checking for repeated creation of a courier with the same login")
    public void testCreateCourierWithSameLogin() {
        String uniqueLogin = generateUniqueLogin();
        CourierModel courier = new CourierModel(uniqueLogin, PASSWORD, FIRSTNAME);
        createCourier(courier)
                .then()
                .log().all()
                .statusCode(SC_CREATED)
                .body("ok", equalTo(true));

        createCourier(courier)
                .then()
                .log().all()
               .statusCode(SC_CONFLICT)
                .body("message", equalTo(ERROR_LOGIN_ALREADY_USED ));

    }

    @Test
    @DisplayName("Create courier without login")
    @Description("Checking the creation of a courier without a login")
    public void testCreateCourierWithoutLogin() {
        CourierModel courier = new CourierModel(null, PASSWORD, FIRSTNAME);
        createCourier(courier)
                .then()
                .log().all()
                .statusCode(SC_BAD_REQUEST)
                .body("message", equalTo(CourierData.ERROR_INSUFFICIENT_DATA));
    }

    @Test
    @DisplayName("Create courier without password")
    @Description("Checking the creation of a courier without a password")
    public void testCreateCourierWithoutPassword() {
        String uniqueLogin = generateUniqueLogin();
        CourierModel courier = new CourierModel(uniqueLogin, null, FIRSTNAME);
        createCourier(courier)
                .then()
                .log().all()
                .statusCode(SC_BAD_REQUEST)
                .body("message", equalTo(CourierData.ERROR_INSUFFICIENT_DATA));
    }

    @Test
    @DisplayName("Create courier without firstname")
    @Description("Checking the creation of a courier without a firstname")
    public void testCreateCourierWithoutFirstname() {
        String uniqueLogin = generateUniqueLogin();
        CourierModel courier = new CourierModel(uniqueLogin, PASSWORD, null);
        createCourier(courier)
                .then()
                .log().all()
                .statusCode(SC_BAD_REQUEST)
                .body("message", equalTo(CourierData.ERROR_INSUFFICIENT_DATA));
    }
}


