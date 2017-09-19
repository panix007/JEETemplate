package com.ntahr.common.dataaccess.mongo.objects;

import org.springframework.data.annotation.Id;

public class LocationPoint {

    @Id
    private String id;
    private String deviceId;
    private String deviceName;
    private String latitude;
    private String longitude;
    private Long recordedTime;

    public LocationPoint() {

    }

    public LocationPoint(String latitude, String longitude, String deviceId, String deviceName, Long recordedTime) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.recordedTime = recordedTime;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Long getRecordedTime() {
        return recordedTime;
    }

    public void setRecordedTime(Long recordedTime) {
        this.recordedTime = recordedTime;
    }

    @Override
    public String toString() {
        return String.format("LocationPoint[id = '%s', latitude = '%s', longitude = '%s', recordedTime = '%s', deviceId = '%s', deviceName = '%s']",
                id, latitude, longitude, recordedTime, deviceId, deviceName);
    }
}
