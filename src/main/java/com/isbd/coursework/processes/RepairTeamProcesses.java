package com.isbd.coursework.processes;

import com.isbd.coursework.database.DbConnection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.*;

@Controller
@RequestMapping("/admin/team")
public class RepairTeamProcesses {

    private final Connection db;

    RepairTeamProcesses(DbConnection db) {
        this.db = db.getConnection();
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity<String> addRepairTeam(
            @RequestParam Integer ownerId
    ) {
        try {
            CallableStatement st = db.prepareCall("{ call add_repair_team(?)}");
            st.setInt(1, ownerId);
            st.execute();
            System.out.println("Inserted repair team for company " + ownerId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
    @RequestMapping("/member")
    public ResponseEntity<String> addRepairTeamMember(
            @RequestParam Integer teamId,
            @RequestParam String memberName
    ) {
        try {
            CallableStatement st = db.prepareCall("{ call add_repair_team_member(?,?)}");
            st.setInt(1, teamId);
            st.setString(2, memberName);

            st.execute();
            System.out.println("Inserted repair team member " + memberName);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
    @RequestMapping("/member/change")
    public ResponseEntity<String> changeMemberAffiliation(
            @RequestParam Integer teamId,
            @RequestParam String memberName
    ) {
        try {
            PreparedStatement selSt = db.prepareCall("{call change_team_affiliation(?,?)}");
            selSt.setInt(1, teamId);
            selSt.setString(2, memberName);
            selSt.execute();
            System.out.println("Updated team affiliation for member " + memberName);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
    @RequestMapping("/head")
    public ResponseEntity<String> setTeamHead(
            @RequestParam Integer teamId,
            @RequestParam String memberName
    ) {
        try {
            CallableStatement selSt = db.prepareCall("{ call set_team_head(?,?)}");
            selSt.setInt(1, teamId);
            selSt.setString(2, memberName);
            selSt.execute();
            System.out.println("Updated team head to " + memberName);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
