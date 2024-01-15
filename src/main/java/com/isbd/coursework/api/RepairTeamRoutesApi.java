package com.isbd.coursework.api;

import com.isbd.coursework.entities.RepairTeamRoute;

import java.util.List;

public interface RepairTeamRoutesApi {
    List<RepairTeamRoute> getTeamRoutesByTeamId(Integer teamId);
    List<RepairTeamRoute> getTeamRoutesFromBase(Integer baseId);
    List<RepairTeamRoute> getTeamRoutesToBase(Integer baseId);
}
