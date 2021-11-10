package com.example.map.dto;

public class Item {

    private String attraction_name;
    private String address_doro;
    private Double latitude;
    private Double longitude;

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

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    
    // 생성자
    public Item(String attraction_name, String address_doro) {
        this.attraction_name = attraction_name;
        this.address_doro = address_doro;
    }

    


}
