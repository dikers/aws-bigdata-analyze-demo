package com.dikers.gallery.domain;

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

    long deviceId;

    String uuid;

    int waterPumpTypeId;

    int pumpOutput;

    int power;

    int temperature;

    float longitude;

    float latitude;

    long logTime;

    int productionDate;


    /**
     * 当前日期  20190302
     */
    int currentDateRecord;

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
                ", productionDate=" + productionDate +
                ", currentDateRecord=" + currentDateRecord +
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
                "," + productionDate +
                ","+ currentDateRecord;
    }

}

