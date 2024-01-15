package com.isbd.coursework.api;

import com.isbd.coursework.entities.RailwaySegment;
import com.isbd.coursework.entities.RailwayStation;

import java.util.List;

public interface RailwayStationApi {
    public List<RailwayStation> getAllStations();

    public List<RailwayStation> getAllStations(Integer limit);

    public List<RailwayStation> getStationNameLike(String name);

    public List<RailwaySegment> getSegmentsRelated(Integer id);

    public List<RailwayStation> getStationsRelated(Integer id);



    }
