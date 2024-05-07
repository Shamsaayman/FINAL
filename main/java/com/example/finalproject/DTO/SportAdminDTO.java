package com.example.finalproject.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SportAdminDTO {

    private  int id;
    //    @NotEmpty(message = "Name should be not empty")
    private String sportAdminName ;


    //    @NotEmpty(message = "password should be not empty")
//    @Size(min = 5 , message = "password should be more than 4 char")
    private String password ;


    //    @NotNull(message = "License Number should be not empty")

    private Integer licenseNumber ;


    //    @NotEmpty(message = "Phone Number should be not empty")
//    @Pattern(regexp = "^05.*",message = "Phone number should be start with 05")
//    @Size(min = 10)
    private String phoneNumber ;


    //    @NotEmpty(message = "Email should be not empty")
    @Email(message = "Pleas enter a valid email")
    private String email ;


    //    @NotEmpty(message = "Sport type should be not empty")
//    @Pattern(regexp = "^(Cycling|Fencing|Boxing|Lifting|Basketball|Tennis|Rowing|Karate|Judo|Archery)$" , message = "Sport type be either Cycling ,Fencing ,Boxing " +
//            ",Lifting ,Basketball ,Tennis ,Rowing ,Karate ,Judo ,Archery only")
//    @Size(min = 5 , message = "Sport type length must be at least 5 characters")
    private String sportType ;
}
