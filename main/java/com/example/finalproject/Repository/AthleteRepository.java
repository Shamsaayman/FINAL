package com.example.finalproject.Repository;

import com.example.finalproject.Model.Athlete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AthleteRepository extends JpaRepository<Athlete,Integer> {
    Athlete findAthleteByAthleteId(Integer athleteId);

    List<Athlete> findAthleteByRankBetween(Integer lowerRank, Integer upperRank);

    Athlete findAthleteByLicenseNumber(Integer licenseNumber);

    List<Athlete> findAthleteBySport(String sportname);

    Athlete findAthleteByUsername(String athleteName);




}
