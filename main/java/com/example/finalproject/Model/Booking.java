package com.example.finalproject.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Booking {
    @Id
   private Integer id;
//   @GeneratedValue
   private Integer bookingNumber;
//@Column(columnDefinition = "varchar(50) check(bookingStatus='attended' or bookingStatus='not attended' or bookingStatus='pending')")
//@Pattern(regexp = "^(Attended |Not Attended|Pending)")
   private String bookingStatus;



   //Relations
   @OneToOne
   @MapsId
   @JsonIgnore
   private Ticket ticket;

}
