package com.isbd.coursework.api;

import com.isbd.coursework.entities.RailwaySegment;
import com.isbd.coursework.entities.RailwayStation;
import org.springframework.data.relational.core.sql.In;

import java.util.List;

public interface RailwayStationApi {
    List<RailwayStation> getAllStations();
    List<RailwayStation> getAllStations(Integer limit);
    List<RailwayStation> getStationNameLike(String name);
    List<RailwaySegment> getSegmentsRelated(Integer id);
    List<RailwayStation> getStationsRelated(Integer id);
    RailwayStation getStationById(Integer stationId);
}
