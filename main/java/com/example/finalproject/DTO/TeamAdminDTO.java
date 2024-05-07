package com.example.finalproject.DTO;


import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TeamAdminDTO {

    private Integer teamId ;

    @NotEmpty(message = "Team name should be not empty")
    @Column(columnDefinition = "varchar(25) not null unique")
    private String teamName ;


    @NotEmpty(message = "password should be not empty")
    @Size(min = 5 , message = "password should be more than 4 char")
    private String password ;


    @NotEmpty(message = "Team admin name should be not empty")
    private String teamAdminName ;


    @NotEmpty(message = "Email should be not empty")
    @Email(message = "Pleas enter a valid email")
    private String teamEmail ;


    @NotEmpty(message = "Phone Number should be not empty")
    @Pattern(regexp = "^05.*",message = "Phone number should be start with 05")
    @Size(min = 10)
    private String phoneNumber ;


    private Integer teamRank;
}
