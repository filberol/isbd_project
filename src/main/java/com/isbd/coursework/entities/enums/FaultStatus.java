package com.isbd.coursework.entities.enums;

public enum FaultStatus {
    NOT_REPAIRED("not_repaired"),
    IN_REPAIR("in_repair"),
    REPAIRED("repaired");

    final String name;
    FaultStatus(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return name;
    }
}
