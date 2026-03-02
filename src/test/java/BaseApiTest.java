import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import static data.OrderData.BASE_URI;
import static steps.CourierSteps.deleteCourierById;

public class BaseApiTest {

    protected Integer courierId;

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URI;
    }

    @After
    public void cleanUp() {
        if (courierId != null) {
            deleteCourierById(courierId);
        }
    }
}
