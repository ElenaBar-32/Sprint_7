package model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListOrderModel {
    private Number courierId;
    private String nearestStation;
    private Number limit;
    private Number page;

    public ListOrderModel(Number courierId, String nearestStation, Number limit, Number page) {
        this.courierId = courierId;
        this.nearestStation = nearestStation;
        this.limit = limit;
        this.page = page;
    }
}
