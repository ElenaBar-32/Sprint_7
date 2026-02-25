package steps;

import io.restassured.response.Response;
import model.CourierLoginModel;
import model.CourierModel;

import static data.CourierData.*;
import static io.restassured.RestAssured.given;

public class CourierSteps {


    public static Response createCourier(CourierModel courier) {
        return given()
                .log().all()
                .header("Content-Type", "application/json")
                .body(courier)
                .when()
                .post(CREATE_PATH)
                .then()
                .extract().response();

    }

    public static Response loginCourier(CourierLoginModel login) {
        return given()
                .log().all()
                .header("Content-Type", "application/json")
                .body(login)
                .when()
                .post(SIGN_IN)
                .then()
                .extract().response();
    }

    public static void deleteCourierById(Integer courierId) {
        given()
                .log().all()
                .header("Content-Type", "application/json")
                .when()
                .delete(DELETE_PATH + "/" + courierId)
                .then();

    }
}

