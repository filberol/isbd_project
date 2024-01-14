package com.isbd.coursework.entities;

public record RailwaySegment(
        Integer id,
        Integer from_rs,
        Integer to_rs,
        Integer length_km
) {
}
