package com.isbd.coursework.entities.enums;

public enum FaultClass {
    CRITICAL("critical"),
    NON_CRITICAL("non_critical");

    final String name;
    FaultClass(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return name;
    }
}
