package com.example.finalproject.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
public class SportAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer adminId ;


//    @NotEmpty(message = "Name should be not empty")
    @Column(columnDefinition = "varchar(25) not null unique")
    private String sportAdminName ;

//    @NotEmpty(message = "password should be not empty")
//    @Size(min = 5 , message = "password should be more than 4 char")
    @Column(columnDefinition = "varchar(15) not null unique")
    private String password ;


//    @NotNull(message = "License Number should be not empty")
    @Column(columnDefinition = "int not null unique")
    private Integer licenseNumber ;


//    @NotEmpty(message = "Phone Number should be not empty")
//    @Pattern(regexp = "^05.*",message = "Phone number should be start with 05")
//    @Size(min = 10)
    @Column(columnDefinition = "varchar(10) not null unique")
    private String phoneNumber ;


//    @NotEmpty(message = "Email should be not empty")
    @Email(message = "Pleas enter a valid email")
    @Column(columnDefinition = "varchar(40) not null unique")
    private String email ;


//    @NotEmpty(message = "Sport type should be not empty")
//    @Pattern(regexp = "^(Cycling|Fencing|Boxing|Lifting|Basketball|Tennis|Rowing|Karate|Judo|Archery)$" , message = "Sport type be either Cycling ,Fencing ,Boxing " +
//            ",Lifting ,Basketball ,Tennis ,Rowing ,Karate ,Judo ,Archery only")
//    @Size(min = 5 , message = "Sport type length must be at least 5 characters")
    @Column(columnDefinition = "varchar(20) not null")
    private String sportType ;



// relations


    @OneToOne(cascade = CascadeType.ALL,mappedBy = "sportAdmin")
    @PrimaryKeyJoinColumn
    @JsonIgnore
    private  Sport sport;

    @OneToOne(cascade = CascadeType.ALL , mappedBy = "sportAdmin")
    @PrimaryKeyJoinColumn
    private User user ;


    @OneToMany(cascade = CascadeType.ALL , mappedBy = "sportAdmin")
    private Set<TeamAdmin> teamAdminSet ;

    @ManyToMany(mappedBy = "sportAdminList")
    @JsonIgnore
    private Set<SportAdminRequest> requests;



}
