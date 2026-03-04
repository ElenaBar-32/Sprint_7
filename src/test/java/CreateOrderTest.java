
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import model.OrderModel;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.notNullValue;
import static steps.OrderSteps.cancelOrder;
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
    private final String[] color;

    String track;

    public CreateOrderTest(String name, String lastName, String address, String metroStation,
                           String telephone, Number rentTime, String deliveryDate, String comment, String[] color) {
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

    @Parameterized.Parameters(name = "Создание заказа: {0} {1}, цвет: {8}")
    public static Object[][] getCredentials() {
        return new Object[][]{
                {"Иван", "Иванов", "Павелецкая площадь, дом 3", "Павелецкая", "+79206001111", 1, "2026-02-26", "Позвонить за час", new String[]{"BLACK"}},
                {"Петр", "Петров", "пл. Тверская Застава, 2", "Белорусская", "+79206002026", 2, "2026-02-26", "Предварительный звонок", new String[]{"GREY"}},
                {"Петр", "Петров", "пл. Тверская Застава, 2", "Белорусская", "+79206002026", 3, "2026-02-26", "Очень ждем", new String[]{"GREY", "BLACK"}},
                {"Петр", "Петров", "пл. Тверская Застава, 2", "Белорусская", "+79206002026", 3, "2026-02-26", "Очень ждем", new String[]{}}

        };
    }

    @Test
    @DisplayName("Order test")
    @Description("Checking created order")
    public void OrderTest() {
        OrderModel order = new OrderModel(name, lastName, address, metroStation,
                telephone, rentTime, deliveryDate, comment, color);
        String track = createOrder(order)
                .then()
                .log().all()
                .statusCode(SC_CREATED)
                .body("track", notNullValue())
                .extract()
                .path("track")
                .toString();

    }

    @After
    public void cancel() {
        if (track != null) {
            cancelOrder(track);
        }
    }
}



