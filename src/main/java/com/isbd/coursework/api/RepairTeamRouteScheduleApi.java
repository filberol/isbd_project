package com.isbd.coursework.api;

import com.isbd.coursework.entities.RepairTeamRouteSchedule;

import java.sql.Timestamp;
import java.util.List;

public interface RepairTeamRouteScheduleApi {
    RepairTeamRouteSchedule getScheduleForRoute(Integer routeId);
    List<RepairTeamRouteSchedule> getSchedulesBetween(Timestamp from, Timestamp to);
    List<RepairTeamRouteSchedule> getSchedulesForRouteBetween(Integer routeId, Timestamp from, Timestamp to);
}
