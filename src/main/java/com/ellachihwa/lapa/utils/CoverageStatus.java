package com.ellachihwa.lapa.utils;

public enum CoverageStatus {
    ACCEPTED("Accepted"),
    DECLINED("Declined"),
    PENDING("Pending");

   final private String name;

    CoverageStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
