package com.geospatial.entities;

import org.springframework.data.annotation.Id;

public class Lidar {

    @Id
    public String Id;

    public Double Lat;

    public Double Long;

    public String filename;

    public String description;


    public Lidar(Double Lat, Double Long, String filename, String description){
        this.Lat = Lat;
        this.Long = Long;
        this.filename = filename;
        this.description = description;
    }

    public Double getLat() {
    	return this.Lat;
    }
    public void setLat(Double Lat) {
    	this.Lat = Lat;
    }
    public Double getLong() {
    	return this.Long;
    }
    public void setLong(Double Long) {
    	this.Long = Long;
    }
    public String getFilename() {
    	return this.filename;
    }
    public void setFilename(String filename) {
    	this.filename = filename;
    }
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    
}