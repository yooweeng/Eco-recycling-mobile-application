package com.example.nearbyrecyclestationmap;

public class PlaceModel {
    int id;
    String name;
    String placeType;

    public PlaceModel() {
    }

    public PlaceModel(int id, String name, String placeType) {
        this.id = id;
        this.name = name;
        this.placeType = placeType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlaceType() {
        return placeType;
    }

    public void setPlaceType(String placeType) {
        this.placeType = placeType;
    }
}
