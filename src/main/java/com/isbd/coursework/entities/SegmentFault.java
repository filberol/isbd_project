package com.isbd.coursework.entities;

import com.isbd.coursework.entities.enums.FaultClass;
import com.isbd.coursework.entities.enums.FaultStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

public record SegmentFault(
        Integer id,
        Integer rwSegId,
        FaultClass faultClass,
        Integer positionPointKm,
        FaultStatus faultStatus
) {
    public static SegmentFault fromSet(ResultSet set) throws SQLException {
        return new SegmentFault(
                set.getInt("id"),
                set.getInt("rw_seg_id"),
                FaultClass.valueOf(set.getString("fault_class")),
                set.getInt("position_point_km"),
                FaultStatus.valueOf(set.getString("fault_status"))
        );
    }
}
