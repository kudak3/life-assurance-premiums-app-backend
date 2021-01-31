package com.ellachihwa.lapa.utils;

public enum PaymentType {
    ECOCASH("Ecocash"),
    TELECASH("Telecash"),
    ZIPIT("Zipit"),
    ONEMONEY("OneMoney");

    private final String name;

    PaymentType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
