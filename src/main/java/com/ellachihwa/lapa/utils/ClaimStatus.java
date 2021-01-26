package com.ellachihwa.lapa.utils;

public enum ClaimStatus {
    APPROVED("Approved"),
    PENDING("Pending"),
    DECLINED("Declined"),
    ;


    private final String name;

    ClaimStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
