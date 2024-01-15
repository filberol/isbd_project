package com.isbd.coursework.entities.enums;

public enum SiteVisitType {
    inspection("inspection"),
    repair("repair");

    final String name;
    SiteVisitType(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return name;
    }
}
