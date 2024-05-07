package com.example.finalproject.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class TeamAdmin {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer teamId ;

//    @NotEmpty(message = "Team name should be not empty")
    @Column(columnDefinition = "varchar(25) not null unique")
    private String teamName ;

//    @NotEmpty(message = "password should be not empty")
//    @Size(min = 5 , message = "password should be more than 4 char")
    @Column(columnDefinition = "varchar(15) not null unique")
    private String password ;


//    @NotEmpty(message = "Team admin name should be not empty")
    @Column(columnDefinition = "varchar(25) not null unique")
    private String teamAdminName ;


//    @NotEmpty(message = "Email should be not empty")
    @Email(message = "Pleas enter a valid email")
    @Column(columnDefinition = "varchar(40) not null unique")
    private String teamEmail ;


//    @NotEmpty(message = "Phone Number should be not empty")
//    @Pattern(regexp = "^05.*",message = "Phone number should be start with 05")
//    @Size(min = 10)
    @Column(columnDefinition = "varchar(10) not null unique")
    private String phoneNumber ;


private Integer teamRank;

//    Relations

    @OneToOne(cascade = CascadeType.ALL , mappedBy = "teamAdmin")
    @PrimaryKeyJoinColumn
    private User user ;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "teamAdmin")
    private Set<Athlete> athleteSet ;

    @ManyToOne
    @JsonIgnore
    private SportAdmin sportAdmin;

    @ManyToMany(mappedBy = "teamAdminList")
    @JsonIgnore
    private Set<SportAdminRequest> requests;









}
