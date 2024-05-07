package com.example.finalproject.Repository;


import com.example.finalproject.Model.SportAdmin;
import com.example.finalproject.Model.SportAdminRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface SportAdminRequestRepository extends JpaRepository<SportAdminRequest, Integer> {
    SportAdminRequest findSportAdminRequestByRequestId(Integer id);
    SportAdminRequest findSportAdminRequestByTeamName(String teamName);
    List<SportAdminRequest> findSportAdminRequestByStatus(String status);
}
