package com.isbd.coursework.entities.enums;

public enum FaultStatus {
    not_repaired("not_repaired"),
    in_repair("in_repair"),
    repaired("repaired");

    final String name;
    FaultStatus(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return name;
    }
}
