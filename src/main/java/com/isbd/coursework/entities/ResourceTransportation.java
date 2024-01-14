package com.isbd.coursework.entities;

import java.time.ZonedDateTime;

public record ResourceTransportation(
        Integer id,
        Integer fromWarehouseId,
        Integer toWarehouseId,
        ZonedDateTime startAt,
        ZonedDateTime finishAt
) {
}
