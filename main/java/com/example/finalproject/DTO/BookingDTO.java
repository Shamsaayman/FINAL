package com.example.finalproject.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor

public class BookingDTO {
    private Integer ticket_id;
    //   @GeneratedValue
    private Integer bookingNumber;
    //@Column(columnDefinition = "varchar(50) check(bookingStatus='attended' or bookingStatus='not attended' or bookingStatus='pending')")
//@Pattern(regexp = "^(Attended |Not Attended|Pending)")
    private String bookingStatus;


}
