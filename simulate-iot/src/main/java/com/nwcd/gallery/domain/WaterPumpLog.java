package com.nwcd.gallery.domain;

import lombok.Data;
import org.apache.logging.log4j.message.LoggerNameAwareMessage;
import org.hibernate.dialect.Ingres9Dialect;

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

    int productionData;


    /**
     * 当前日期  20190302
     */
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

