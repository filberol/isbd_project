package com.isbd.coursework.entities;

import java.time.ZonedDateTime;

public record SiteFaultFixation(
        Integer id,
        Integer routeId,
        ZonedDateTime plannedAt,
        ZonedDateTime departedAt,
        ZonedDateTime arrivedAt
) {
}
