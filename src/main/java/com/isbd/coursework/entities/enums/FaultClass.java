package com.isbd.coursework.entities.enums;

public enum FaultClass {
    critical("critical"),
    non_critical("non_critical");

    final String name;
    FaultClass(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return name;
    }
}
