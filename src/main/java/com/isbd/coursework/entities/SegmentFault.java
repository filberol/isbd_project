package com.isbd.coursework.entities;

import com.isbd.coursework.entities.enums.FaultClass;
import com.isbd.coursework.entities.enums.FaultStatus;

public record SegmentFault(
        Integer id,
        Integer rwSegId,
        FaultClass faultClass,
        Integer positionPointKm,
        FaultStatus faultStatus
) {
}
