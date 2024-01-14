package com.isbd.coursework.entities;

public record RepairTeamRoute(
        Integer id,
        Integer repairTeamId,
        Integer fromBaseId,
        Integer toBaseId
) {
}
