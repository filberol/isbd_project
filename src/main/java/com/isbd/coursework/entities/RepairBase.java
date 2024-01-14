package com.isbd.coursework.entities;

public record RepairBase(
        Integer id,
        Integer stationId,
        Integer sizeTeams,
        Integer currTeamsHosted
) {
}
