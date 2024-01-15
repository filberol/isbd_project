package com.isbd.coursework.api;

import com.isbd.coursework.entities.RepairTeamMember;

import java.util.List;

public interface RepairTeamApi {
    List<RepairTeamMember> getRepairTeamMembersById(Integer teamId);
    RepairTeamMember getTeamHeadById(Integer teamId);
    List<Integer> getTeamIdsByOwner(Integer ownerId);
}
