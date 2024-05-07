package com.example.finalproject.Controller;

import com.example.finalproject.API.ApiResponse;
import com.example.finalproject.Model.Ticket;
import com.example.finalproject.Service.TicketService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/api/v1/ticket")
@RestController
public class TicketController {

    private final TicketService ticketService;

     @PostMapping("/add")
    public ResponseEntity addSports(@RequestBody @Valid Ticket ticket) {
        ticketService.addTicket(ticket);
        return ResponseEntity.status(200).body(new ApiResponse("Ticket added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateSports(@PathVariable Integer id, @RequestBody @Valid Ticket ticket) {
        ticketService.updateTicket(id, ticket);
        return ResponseEntity.status(200).body(new ApiResponse("Ticket updated successfully"));
    }

    @GetMapping("/get")
    public List<Ticket> getAllTicket() {
        return ticketService.getAllTickets();
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteSports(@PathVariable Integer id){
        ticketService.deleteTicket(id);
        return ResponseEntity.status(200).body(new ApiResponse("Ticket deleted successfully"));
    }

}
