package com.example.finalproject.Repository;

import com.example.finalproject.Model.Fan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface FanRepository extends JpaRepository<Fan,Integer> {
    Fan findFanByFanId(Integer fanId);


    List<Fan> getAllBookingByFanId(Integer fanId);
}
