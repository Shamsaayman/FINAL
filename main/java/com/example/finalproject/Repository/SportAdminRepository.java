package com.example.finalproject.Repository;


import com.example.finalproject.Model.SportAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SportAdminRepository extends JpaRepository<SportAdmin, Integer> {

    SportAdmin findSportAdminByAdminId(Integer id);
    SportAdmin findSportAdminBySportType(String sportType);
}
