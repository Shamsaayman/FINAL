package com.example.finalproject.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AthleteDTO {

    private Integer id;
    // @NotEmpty(message = "username should be not empty")
//    @Size(min = 4 , message = "username should be more than 4 char")

    private String username ;
    //    @Size(min = 4 , message = "username should be more than 4 char")


    private String password ;
    @Email
//    @NotEmpty(message = "email should be not empty")

    private String email ;
    //    @NotNull(message = "age should be not empty")

    private Integer age;
    //    @NotNull(message = "weight should be not empty")

    private Integer weight;
    //    @NotNull(message = "height should be not empty")

    private Integer height;
    //    @NotEmpty(message = "gender should be not empty")
    private String gender ;
    //    @NotEmpty(message = "sport should be not empty")
    private String sport ;
    //    @NotEmpty(message = "category should be not empty")
    private String category ;

    //    @NotNull(message = "achievements should be not empty")
//    @Positive(message = "achievements should be positive only")
    private Integer achievements;


    private  Integer licenseNumber;


    private Integer rank;
}
