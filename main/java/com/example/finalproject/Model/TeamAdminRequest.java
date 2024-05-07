package com.example.finalproject.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class TeamAdminRequest {

    @Id
    private Integer id ;


    private String status ;




//     relation

    @OneToOne
    @MapsId
    @JsonIgnore
    private Athlete athlete ;


    @ManyToMany
    private List<TeamAdmin> teamAdminRequests ;


}
