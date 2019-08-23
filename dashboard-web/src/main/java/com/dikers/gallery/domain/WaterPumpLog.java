package com.nwcd.gallery.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @author dikers
 */


@Data
@Entity
@Table(name = "water_pump_log")
public class WaterPumpLog {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "deviceId")
    long deviceId;

    @Column(name = "uuid")
    String uuid;

    @Column(name = "waterPumpTypeId")
    int waterPumpTypeId;

    @Column(name = "pumpOutput")
    int pumpOutput;

    @Column(name = "power")
    int power;

    @Column(name = "temperature")
    int temperature;

    @Column(name = "longitude")
    float longitude;

    @Column(name = "latitude")
    float latitude;

    @Column(name = "logTime")
    long logTime;

    @Column(name = "productionData")
    int productionData;


    /**
     * 当前日期  20190302
     */
    @Column(name = "currentData")
    int currentData ;

    @Override
    public String toString() {
        return "WaterPumpLog{" +
                "deviceId=" + deviceId +
                ", waterPumpTypeId=" + waterPumpTypeId +
                ", pumpOutput=" + pumpOutput +
                ", power=" + power +
                ", temperature=" + temperature +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", logTime=" + logTime +
                ", productionData=" + productionData +
                ", currentData=" + currentData +
                '}';
    }


    public String toLogString() {
        return  deviceId +
                "," + waterPumpTypeId +
                "," + pumpOutput +
                "," + power +
                "," + temperature +
                "," + longitude +
                "," + latitude +
                "," + logTime +
                "," + productionData +
                ","+ currentData;
    }

}

