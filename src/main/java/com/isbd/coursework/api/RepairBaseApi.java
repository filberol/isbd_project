package com.isbd.coursework.api;

import com.isbd.coursework.entities.RepairBase;

public interface RepairBaseApi {
    RepairBase getRepairBaseByStationId(Integer stationId);
}
