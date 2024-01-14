package com.isbd.coursework.entities;

public record Warehouse(
        Integer id,
        Integer stationId,
        Integer resourceAvailableKm
) {
}
