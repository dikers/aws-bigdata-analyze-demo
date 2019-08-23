package com.dikers.gallery.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DatePowerSumBean {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("date")
    private Integer date;


    @JsonProperty("power")
    private Integer power;


}
