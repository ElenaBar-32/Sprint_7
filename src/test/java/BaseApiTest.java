import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import static steps.CourierSteps.deleteCourierById;


public class BaseApiTest {


    protected Integer courierId;

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
    }

    @After
    public void cleanUp() {
        if (courierId != null) {
            deleteCourierById(courierId);
        }
    }
}
