package com.taxist.JibMeak.dto;

import java.time.LocalTime;
import java.util.List;

public class WarehouseDTO {
    private Long id;
    private String address;
    private double latitude;
    private double longitude;
    private LocalTime openHour;
    private LocalTime closeHour;

    private List<Long> tourIds;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }

    public LocalTime getOpenHour() { return openHour; }
    public void setOpenHour(LocalTime openHour) { this.openHour = openHour; }

    public LocalTime getCloseHour() { return closeHour; }
    public void setCloseHour(LocalTime closeHour) { this.closeHour = closeHour; }
}
