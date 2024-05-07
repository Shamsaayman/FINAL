package com.example.finalproject.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Sport {


       @Id
       private Integer id;



       @NotEmpty(message = "name must be not empty")
       @Column(columnDefinition = "varchar(30) not null")
       private String name;





  //relation


    @OneToOne
    @MapsId
  private SportAdmin sportAdmin;
}
