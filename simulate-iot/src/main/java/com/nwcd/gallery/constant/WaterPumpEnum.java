package com.nwcd.gallery.constant;

public enum  WaterPumpEnum {

    XXX_SIZE(1, "XXX"),

    XX_SIZE(2, "XX"),

    X_SIZE(3, "X"),

    L_SIZE(4, "L"),

    M_SIZE(5, "M"),

    S_SIZE(6, "S");

    private int size;
    private String name;

    WaterPumpEnum(int size,String name ) {
        this.size = size;
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public String getName() {
        return name;
    }
}
