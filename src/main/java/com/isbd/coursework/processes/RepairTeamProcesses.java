package com.isbd.coursework.processes;

import com.isbd.coursework.database.DbConnection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        String insertStatement = "insert into repair_team(owner_id) values (?);";
        try {
            PreparedStatement st = db.prepareStatement(insertStatement);
            st.setInt(1, ownerId);
            st.executeUpdate();
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
        String insertStatement = "insert into repair_team_member(name, repair_team_id) values (?, ?);";
        try {
            PreparedStatement st = db.prepareStatement(insertStatement);
            st.setString(1, memberName);
            st.setInt(2, teamId);
            st.executeUpdate();
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
        String updateStatement = "update repair_team_member set repair_team_id = ? where id = ?;";
        String selectStatement =
                "select id from repair_team_member where name = ? and repair_team_id = ?;";
        try {
            PreparedStatement selSt = db.prepareStatement(selectStatement);
            selSt.setString(1, memberName);
            selSt.setInt(2, teamId);
            ResultSet set = selSt.executeQuery();
            set.next();
            int memberId = set.getInt("id");
            PreparedStatement updSt = db.prepareStatement(updateStatement);
            updSt.setInt(1, teamId);
            updSt.setInt(2, memberId);
            updSt.executeUpdate();
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
        String updateStatement = "update repair_team set team_head_id=? where repair_team.id=?;";
        String selectStatement = "select id from repair_team_member where name = ?;";
        try {
            PreparedStatement selSt = db.prepareStatement(selectStatement);
            selSt.setString(1, memberName);
            ResultSet set = selSt.executeQuery();
            set.next();
            int memberId = set.getInt("id");
            PreparedStatement updSt = db.prepareStatement(updateStatement);
            updSt.setInt(1, memberId);
            updSt.setInt(2, teamId);
            updSt.executeUpdate();
            System.out.println("Updated team head to " + memberName);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
