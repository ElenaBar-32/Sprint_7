package model;

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

    public Number getCourierId() {
        return courierId;
    }

    public void setCourierId(Number courierId) {
        this.courierId = courierId;
    }

    public String getNearestStation() {
        return nearestStation;
    }

    public void setNearestStation(String nearestStation) {
        this.nearestStation = nearestStation;
    }

    public Number getLimit() {
        return limit;
    }

    public void setLimit(Number limit) {
        this.limit = limit;
    }

    public Number getPage() {
        return page;
    }

    public void setPage(Number page) {
        this.page = page;
    }

}