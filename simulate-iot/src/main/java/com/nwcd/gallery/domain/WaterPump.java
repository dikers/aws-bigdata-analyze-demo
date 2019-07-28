package com.nwcd.gallery.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @author dikers
 */


@Data
@Entity
@Table(name = "water_pump")
public class WaterPump {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;


    int size;

    String name;
    /**
     *  最大抽水量
     */
    int maxPumpOutput;

    /**
     * 平均抽水量
     */
    int meanPumpOutput;

    /**
     * 误差范围
     */
    float pumpOutputErrorRatio;


    /**
     *
     */
    int maxPower;

    /**
     *
     */
    int meanPower;

    /**
     *
     */
    float powerErrorRatio;

    /**
     *
     */
    int price;

    /**
     *
     */
    int lifeCycle;

    @Override
    public String toString() {
        return "WaterPump{" +
                "id=" + id +
                ", size=" + size +
                ", name='" + name + '\'' +
                ", maxPumpOutput=" + maxPumpOutput +
                ", meanPumpOutput=" + meanPumpOutput +
                ", pumpOutputErrorRatio=" + pumpOutputErrorRatio +
                ", maxPower=" + maxPower +
                ", meanPower=" + meanPower +
                ", powerErrorRatio=" + powerErrorRatio +
                ", price=" + price +
                ", lifeCycle=" + lifeCycle +
                '}';
    }
}
