package com.isbd.coursework.api;

import com.isbd.coursework.entities.SegmentFault;
import com.isbd.coursework.entities.enums.FaultClass;
import com.isbd.coursework.entities.enums.FaultStatus;

import java.util.List;

public interface SegmentFaultApi {
    List<SegmentFault> getFaultsByRailwaySegmentId(Integer segmentId);
    List<SegmentFault> getFaultsByRailwaySegmentIdAndClass(Integer segmentId, FaultClass faultClass);
    List<SegmentFault> getFaultsByRailwaySegmentIdAndStatus(Integer segmentId, FaultStatus faultStatus);
    List<SegmentFault> getFaultsByRailwaySegmentIdAndClassAndStatus(
            Integer segmentId, FaultStatus faultStatus, FaultClass faultClass
    );
    List<SegmentFault> getNotRepairedCriticalFaults();
    List<SegmentFault> getFaultsCloseToStation(Integer stationId);
}
