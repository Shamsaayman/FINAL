package com.example.finalproject.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.*;
import lombok.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Athlete {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer athleteId ;
// @NotEmpty(message = "username should be not empty")
//    @Size(min = 4 , message = "username should be more than 4 char")
//    @Column(columnDefinition = "varchar(5) not null")
    private String username ;

//    @NotEmpty(message = "password should be not empty")
//    @Size(min = 5 , message = "password should be more than 4 char")
//    @Column(columnDefinition = "varchar(15) not null unique")
    private String password ;
//    @Email
//    @NotEmpty(message = "email should be not empty")
//    @Column(columnDefinition = "varchar(30) not null unique")
    private String email ;
//    @NotNull(message = "age should be not empty")
//    @Column(columnDefinition = "int not null ")
    private Integer age;
//    @NotNull(message = "weight should be not empty")
//    @Column(columnDefinition = "int not null ")
    private Integer weight;
//    @NotNull(message = "height should be not empty")
//    @Column(columnDefinition = "int not null ")
    private Integer height;
//    @NotEmpty(message = "gender should be not empty")
//    @Column(columnDefinition = "varchar(5) check(gender='male' or gender='female')")
    private String gender ;
//    @NotEmpty(message = "sport should be not empty")
//    @Column(columnDefinition = "varchar(5) not null ")
    private String sport ;
//    @NotEmpty(message = "category should be not empty")
//    @Column(columnDefinition = "varchar(5) not null ")
    private String category ;

//    @NotNull(message = "achievements should be not empty")
//    @Positive(message = "achievements should be positive only")
//    @Column(columnDefinition = "int not null")
    private Integer achievements;


    //    @NotNull(message = "License Number should be not empty")
//    @Column(columnDefinition = "int not null unique ")
    private  Integer licenseNumber;


    private Integer rank;


//    relations

    @OneToOne(cascade = CascadeType.ALL , mappedBy = "athlete")
    @PrimaryKeyJoinColumn
    private User user ;

    @OneToOne(cascade = CascadeType.ALL , mappedBy = "athlete")
    @PrimaryKeyJoinColumn
    private TeamAdminRequest teamAdminRequest ;

    @ManyToOne
    @JsonIgnore
    private TeamAdmin teamAdmin ;






}
