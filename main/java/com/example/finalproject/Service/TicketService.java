package com.example.finalproject.Service;

import com.example.finalproject.API.ApiException;
import com.example.finalproject.Model.Ticket;
import com.example.finalproject.Repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TicketService {
    private TicketRepository ticketRepository;


    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }



    public void addTicket(Ticket ticket) {

        ticketRepository.save(ticket);
    }


    public void updateTicket(Integer id,Ticket ticket) {
        Ticket ticket1=ticketRepository.findTicketById(id);
        if (ticket1 == null){
            throw new ApiException("invalid ticket id");
        }

        ticket1.setSportType(ticket.getSportType());
        ticket1.setQuantity(ticket.getQuantity());
        ticket1.setSeatNumber(ticket.getSeatNumber());
        ticket1.setTicketTime(ticket.getTicketTime());
        ticket1.setTicketDate(ticket.getTicketDate());
        ticket1.setSeatClass(ticket.getSeatClass());
        ticket1.setPrice(ticket.getPrice());

        ticket1.setBooking(ticket.getBooking());

        ticketRepository.save(ticket1);
    }

    public void deleteTicket(Integer id) {
        Ticket ticket=ticketRepository.findTicketById(id);

        if (ticket == null){
            throw new ApiException("invalid ticket id");
        }
        ticketRepository.delete(ticket);
    }
}
