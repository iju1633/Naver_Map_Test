package com.example.map.dto;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Item {

    @PrimaryKey(autoGenerate = true) // id가 1씩 올라가며 counting 됨
    private int id = 0; // 하나의 사용자에 대한 고유 ID 값
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    // 생성자
    public Item(String attraction_name, String address_doro) {
        this.attraction_name = attraction_name;
        this.address_doro = address_doro;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", attraction_name='" + attraction_name + '\'' +
                ", address_doro='" + address_doro + '\'' +
                '}';
    }
}
