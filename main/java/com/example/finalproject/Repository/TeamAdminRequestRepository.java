package com.example.finalproject.Repository;

import com.example.finalproject.Model.TeamAdminRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TeamAdminRequestRepository extends JpaRepository<TeamAdminRequest, Integer> {

    TeamAdminRequest findTeamAdminRequestById(Integer id);

}
