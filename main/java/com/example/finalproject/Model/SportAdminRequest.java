package com.example.finalproject.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class SportAdminRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer requestId ;
//    @Column(columnDefinition = "varchar(20) not null")
    private String teamName;
    //@Pattern(regexp = "^(Accepted|Declines)$" , message = "status must be Accepted or Pending or rejected")
//    @Column(columnDefinition = "varchar(20) not null")
    private String status ;
//    @Column(columnDefinition = "varchar(20) not null")
    private String sportType;



    @ManyToMany
    private Set<TeamAdmin>teamAdminList;


    @ManyToMany
    private Set<SportAdmin> sportAdminList;



}
