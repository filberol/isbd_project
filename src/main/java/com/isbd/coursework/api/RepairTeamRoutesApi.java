package com.isbd.coursework.api;

import com.isbd.coursework.entities.RepairTeamRoute;
import com.isbd.coursework.entities.dto.TeamRouteDescription;

import java.util.List;

public interface RepairTeamRoutesApi {
    List<RepairTeamRoute> getTeamRoutesByTeamId(Integer teamId);
    List<RepairTeamRoute> getTeamRoutesFromBase(Integer baseId);
    List<RepairTeamRoute> getTeamRoutesToBase(Integer baseId);
    List<TeamRouteDescription> getTeamRouteDescriptionsFromBase(Integer baseId);
}
