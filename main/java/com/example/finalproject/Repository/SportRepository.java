package com.example.finalproject.Repository;

import com.example.finalproject.Model.Sport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SportRepository extends JpaRepository<Sport, Integer> {
    Sport findSportById(Integer id);
    Sport findSportByName(String name);

}
