package com.isbd.coursework.services;

import com.isbd.coursework.api.SegmentFaultApi;
import com.isbd.coursework.database.DbConnection;
import com.isbd.coursework.entities.SegmentFault;
import com.isbd.coursework.entities.enums.FaultClass;
import com.isbd.coursework.entities.enums.FaultStatus;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SegmentFaultService implements SegmentFaultApi {

    private final Connection db;

    SegmentFaultService(DbConnection db) {
        this.db = db.getConnection();
    }

    @Override
    public List<SegmentFault> getFaultsByRailwaySegmentId(Integer segmentId) {
        if (segmentId == 0) return null;
        String selectStatement = "SELECT * FROM segment_fault WHERE rw_seg_id=?;";
        try {
            PreparedStatement st = db.prepareStatement(selectStatement);
            st.setInt(1, segmentId);
            List<SegmentFault> faults = new ArrayList<>();
            ResultSet set = st.executeQuery();
            while (set.next()) {
                faults.add(SegmentFault.fromSet(set));
            }
            return faults;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public List<SegmentFault> getFaultsByRailwaySegmentIdAndClass(
            Integer segmentId, FaultClass faultClass
    ) {
        if (segmentId == 0) return null;
        String selectStatement = "SELECT * FROM segment_fault WHERE rw_seg_id=? AND fault_class=?;";
        return getSegmentFaultsByParameter(segmentId, selectStatement, faultClass.name());
    }

    @Override
    public List<SegmentFault> getFaultsByRailwaySegmentIdAndStatus(
            Integer segmentId, FaultStatus faultStatus
    ) {
        if (segmentId == 0) return null;
        String selectStatement = "SELECT * FROM segment_fault WHERE rw_seg_id=? AND fault_status=?;";
        return getSegmentFaultsByParameter(segmentId, selectStatement, faultStatus.name());
    }

    private List<SegmentFault> getSegmentFaultsByParameter(
            Integer segmentId, String selectStatement, String name
    ) {
        try {
            PreparedStatement st = db.prepareStatement(selectStatement);
            st.setInt(1, segmentId);
            st.setString(2, name);
            List<SegmentFault> faults = new ArrayList<>();
            ResultSet set = st.executeQuery();
            while (set.next()) {
                faults.add(SegmentFault.fromSet(set));
            }
            return faults;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public List<SegmentFault> getFaultsByRailwaySegmentIdAndClassAndStatus(
            Integer segmentId, FaultStatus faultStatus, FaultClass faultClass
    ) {
        if (segmentId == 0) return null;
        String selectStatement = "SELECT * FROM segment_fault WHERE rw_seg_id=? " +
                "AND fault_class=? AND fault_status=?;";
        try {
            PreparedStatement st = db.prepareStatement(selectStatement);
            st.setInt(1, segmentId);
            st.setString(2, faultClass.name());
            st.setString(3, faultStatus.name());
            List<SegmentFault> faults = new ArrayList<>();
            ResultSet set = st.executeQuery();
            while (set.next()) {
                faults.add(SegmentFault.fromSet(set));
            }
            return faults;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public List<SegmentFault> getNotRepairedCriticalFaults() {
        String selectStatement = "SELECT * FROM segment_fault WHERE " +
                "fault_class='critical' AND fault_status='not_repaired';";
        try {
            PreparedStatement st = db.prepareStatement(selectStatement);
            List<SegmentFault> faults = new ArrayList<>();
            ResultSet set = st.executeQuery();
            while (set.next()) {
                faults.add(SegmentFault.fromSet(set));
            }
            return faults;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public List<SegmentFault> getFaultsCloseToStation(Integer stationId) {
        String selectStatement = "SELECT * FROM segment_fault WHERE " +
                "rw_seg_id in (SELECT id FROM railway_segment WHERE from_rs=?);";
        try {
            PreparedStatement st = db.prepareStatement(selectStatement);
            st.setInt(1, stationId);
            List<SegmentFault> faults = new ArrayList<>();
            ResultSet set = st.executeQuery();
            while (set.next()) {
                faults.add(SegmentFault.fromSet(set));
            }
            return faults;
        } catch (SQLException e) {
            return null;
        }
    }

}
