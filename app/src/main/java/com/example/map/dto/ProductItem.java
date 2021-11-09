package com.example.map.dto;

public class ProductItem {
    public ProductItem(String attraction_name, String address_doro) {
        this.attraction_name = attraction_name;
        this.address_doro = address_doro;
    }

    public String getAttraction_name() {
        return attraction_name;
    }

    public void setAttraction_name(String attraction_name) {
        this.attraction_name = attraction_name;
    }

    public String getAddress_doro() {
        return address_doro;
    }

    public void setAddress_doro(String address_doro) {
        this.address_doro = address_doro;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    private String attraction_name;
    private String address_doro;
    private String latitude;
    private String longitude;



}
