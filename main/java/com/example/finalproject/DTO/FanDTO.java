package com.example.finalproject.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FanDTO {
    private int id;

    private String username ;
    //    @NotEmpty(message = "password should be not empty")
//    @Size(min = 5 , message = "password should be more than 4 char")
    @Column(columnDefinition = "varchar(15) not null unique")
    private String password ;
    @Email
//    @NotEmpty(message = "email should be not empty")
    @Column(columnDefinition = "varchar(30) not null unique")
    private String email ;
    //    @NotNull(message = "age should be not empty")
    @Column(columnDefinition = "int not null ")
    private Integer age;
    //    @NotEmpty(message = "favorite Sport should be not empty")
    @Column(columnDefinition = "varchar(5) not null ")
    private String favoriteSport ;

}
