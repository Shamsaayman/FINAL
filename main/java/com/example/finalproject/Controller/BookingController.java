package com.example.finalproject.Controller;

import com.example.finalproject.API.ApiResponse;
import com.example.finalproject.DTO.BookingDTO;
import com.example.finalproject.Model.Booking;
import com.example.finalproject.Service.BookingService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/api/v1/booking")
@RestController
public class BookingController {
    private final BookingService bookingService;



    @PutMapping("/update/{bookingId}")
    public ResponseEntity updateBooking(@PathVariable Integer bookingId , @RequestBody @Valid BookingDTO bookingDTO) {
        bookingService.updateBooking(bookingId ,bookingDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Booking updated successfully"));
    }

    @GetMapping("/get")
    public List<Booking> getAllBooking() {
        return bookingService.getAllBookings();
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBooking(@PathVariable Integer id){
        bookingService.deleteBooking(id);
        return ResponseEntity.status(200).body(new ApiResponse("Booking deleted successfully"));
    }


    //    endpoint
    @PostMapping("/add/{fanId}")
    public ResponseEntity addBooking(@PathVariable Integer fanId ,@RequestBody @Valid BookingDTO bookingdto) {
        bookingService.addBooking(fanId ,bookingdto);
        return ResponseEntity.status(200).body(new ApiResponse("Booking added successfully"));
    }


    @PutMapping("/change-status/{bookingId}/{bookingStatus}")
    public ResponseEntity changeBookingStatus(@PathVariable Integer bookingId , @PathVariable String bookingStatus){
        bookingService.changeBookingStatus(bookingId ,bookingStatus);
        return ResponseEntity.status(200).body(new ApiResponse("Booking status changed successfully"));
    }


    @GetMapping("/all-bookings/{fanId}")
    public ResponseEntity getBookingsByFanId(@PathVariable Integer fanId){
        return ResponseEntity.status(200).body(bookingService.getBookingsByFanId(fanId));
    }
}
