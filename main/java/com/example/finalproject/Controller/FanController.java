package com.example.finalproject.Controller;

import com.example.finalproject.API.ApiResponse;
import com.example.finalproject.DTO.FanDTO;
import com.example.finalproject.Model.Fan;
import com.example.finalproject.Model.User;
import com.example.finalproject.Service.FanService;
import com.example.finalproject.Service.SportAdminService;
import com.example.finalproject.Service.SportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/fan")
@RequiredArgsConstructor
public class FanController {
    private final FanService fanService;

    @GetMapping("/get")
    public ResponseEntity getAllFans(){
        return ResponseEntity.status(200).body(fanService.getAllFans());
    }

    @PostMapping("/add")
    public ResponseEntity RegisterFan(@RequestBody @Valid FanDTO fanDTO){
        fanService.RegisterFan(fanDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Fan added"));
    }

    @PutMapping("/update")
    public ResponseEntity updateFan(@AuthenticationPrincipal User user, @RequestBody @Valid FanDTO fanDTO){
        fanService.updateFan(user.getId() ,fanDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Fan updated"));
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteFan(@AuthenticationPrincipal User user){
        fanService.deleteFan(user.getId());
        return ResponseEntity.status(200).body(new ApiResponse("Fan deleted"));
    }


//    endpoint



    @GetMapping("/logIn")
    public ResponseEntity FanLogIn(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(fanService.FanLogIn(user.getId() , user.getPassword()));
    }


    @GetMapping("/view-ticket")
    public ResponseEntity viewTicketToBook(){
        return ResponseEntity.status(200).body(fanService.viewTicketToBook());
    }


    @GetMapping("/ticket-details/{ticketId}")
    public ResponseEntity getTicketDetails(@PathVariable Integer ticketId){
        return ResponseEntity.status(200).body(fanService.getTicketDetails(ticketId));
    }


    @GetMapping("/my-booked")
    public ResponseEntity viewMyBookedTicket(){
        return ResponseEntity.status(200).body(fanService.viewMyBookedTicket());
    }

    @GetMapping("/get-teams")
    public ResponseEntity getAllTeams(){
        return ResponseEntity.status(200).body(fanService.getAllTeams());
    }


    @GetMapping("/get-by-status/{status}")
    public ResponseEntity viewBookingByStatus(@PathVariable String status){
        return ResponseEntity.status(200).body(fanService.viewBookingByStatus(status));
    }


    @PutMapping("/change-status-to-attended/{bookingId}")
    public ResponseEntity changeStatusToAttended(@PathVariable Integer bookingId){
        fanService.changeStatusToAttended(bookingId);
        return ResponseEntity.status(200).body(new ApiResponse("Booking status changed to attended"));
    }


    @PutMapping("/cancel-booking/{bookingId}")
    public ResponseEntity cancelBooking(@PathVariable Integer bookingId){
        fanService.cancelBooking(bookingId);
        return ResponseEntity.status(200).body(new ApiResponse("Booking cancelled"));
    }


    @GetMapping("/logout")
    public ResponseEntity FanLogout(){
        return ResponseEntity.status(200).body(new ApiResponse("Fan Log Out"));
    }

    @GetMapping("/all-sports")
    public ResponseEntity ViewAllSports(){
        return ResponseEntity.status(200).body(fanService.viewAllSports());
    }



    @GetMapping("/view-atchevments/{athelteName}")
    public ResponseEntity athelteAtchevments(@PathVariable String athelteName){
        return ResponseEntity.status(200).body(fanService.athelteAtchevments(athelteName));
    }
}
