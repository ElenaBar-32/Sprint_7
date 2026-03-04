package steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.OrderModel;


import static data.OrderData.*;
import static io.restassured.RestAssured.given;

public class OrderSteps {
    @Step("Send POST request to create order")
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
    @Step("Send GET request to  get order")
    public static Response listOrder() {
        return given()
                .log().all()
                .header("Content-Type", "application/json")
                .when()
                .get(LIST_ORDER)
                .then()
                .extract().response();
    }
    @Step("Send PUT request to cancel order with track: {track}")
    public static Response cancelOrder(String track) {
        return given()
                .log().all()
                .header("Content-Type", "application/json")
                .when()
                .put(CANCEL_ORDER + track)
                .then()
                .extract().response();
    }
}

