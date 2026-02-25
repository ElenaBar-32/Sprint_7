
import model.OrderModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.hamcrest.CoreMatchers.notNullValue;
import static steps.OrderSteps.createOrder;

@RunWith(Parameterized.class)


public class CreateOrderTest extends BaseApiTest {

    private final String name;
    private final String lastName;
    private final String address;
    private final String metroStation;
    private final String telephone;
    private final Number rentTime;
    private final String deliveryDate;
    private final String comment;
    private final String [] color;


    public CreateOrderTest(String name, String lastName, String address, String metroStation,
                           String telephone, Number rentTime, String deliveryDate, String comment, String [] color) {
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.telephone = telephone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][] getCredentials() {
        return new Object[][]{
                {"Иван", "Иванов", "Павелецкая площадь, дом 3", "Павелецкая", "+79206001111", 1, "2026-02-26","Позвонить за час", new String[]{"BLACK"}},
                {"Петр", "Петров", "пл. Тверская Застава, 2", "Белорусская", "+79206002026", 2,"2026-02-26", "Предварительный звонок", new String[]{"GREY"}},
               {"Петр", "Петров", "пл. Тверская Застава, 2", "Белорусская", "+79206002026", 3,"2026-02-26", "Очень ждем", new String[]{}}

        };
    }

    @Test
    public void OrderTest() {
        OrderModel order = new OrderModel(name, lastName, address, metroStation,
                telephone, rentTime, deliveryDate, comment, color);
        createOrder(order)
                .then()
                .log().all()
                .statusCode(201)
                .body("track", notNullValue());
    }
}
