package com.example.finalproject.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Ticket {


      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Integer id;


//
//      @Column(columnDefinition = "varchar(30) not null")
//      @NotEmpty(message = "Sport Type must be not empty")
     private String sportType;

//    @Column(columnDefinition = "varchar(30) not null")
//    @NotEmpty(message = "Seat Class must be not empty")
//    @Pattern(regexp = "^(Bronze |Silver|Gold)")
    private String SeatClass;

//
//    @NotNull(message = "quantity should be not empty")
//    @Positive(message = "positive number only")
//    @Column(columnDefinition = "int not null")
    private Integer quantity ;


//     @NotNull(message = "Seat must be not empty")
//     @Column(columnDefinition = "int not null")
     private Integer seatNumber;



//     @NotNull(message = "Price must be not empty")
//     @Column(columnDefinition = "int not null")
     private Integer Price;


    //@Column(columnDefinition = "DATETIME")
    @FutureOrPresent
     private LocalDate ticketDate ;


    //@Column(columnDefinition = "DATETIME")
     private LocalTime ticketTime ;



     //Relations

     @OneToOne(cascade = CascadeType.ALL, mappedBy = "ticket")
     @PrimaryKeyJoinColumn
     private Booking booking;

     @ManyToMany
     @JsonIgnore
     private Set<Fan> fans;
}
