package com.example.finalproject.Repository;

import com.example.finalproject.Model.TeamAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamAdminRepository extends JpaRepository<TeamAdmin, Integer> {

    TeamAdmin findTeamAdminByTeamId(Integer id);

    TeamAdmin findTeamAdminByteamName(String name);

    List<TeamAdmin> findTeamAdminByTeamRankBetween(Integer lowerRank, Integer upperRank);
    List<TeamAdmin> findTeamAdminByTeamName(String teamName);


}
