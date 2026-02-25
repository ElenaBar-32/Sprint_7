package steps;

import io.restassured.response.Response;
import model.OrderModel;


import static data.OrderData.*;
import static io.restassured.RestAssured.given;

public class OrderSteps {
    public static Response createOrder(OrderModel order) {
        return given()
                .log().all()
                .header("Content-Type", "application/json")
                .body(order)
                .when()
                .post(CREATE_ORDER)
                .then()
                .extract().response();

    }

    public static Response listOrder() {
        return given()
                .log().all()
                .header("Content-Type", "application/json")
                .when()
                .get(LIST_ORDER)
                .then()
                .extract().response();
    }
 }


