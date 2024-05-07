package com.example.finalproject.Service;

import com.example.finalproject.API.ApiException;
import com.example.finalproject.DTO.BookingDTO;
import com.example.finalproject.Model.Booking;
import com.example.finalproject.Model.Fan;
import com.example.finalproject.Model.Ticket;
import com.example.finalproject.Repository.BookingRepository;
import com.example.finalproject.Repository.FanRepository;
import com.example.finalproject.Repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final TicketRepository ticketRepository;
    private final FanRepository fanRepository;

    public List<Booking> getAllBookings(){
       return bookingRepository.findAll();
    }


    public void updateBooking(Integer bookingId, BookingDTO bookingDTO){
        Booking booking= bookingRepository.findBookingById(bookingId);
        if(booking==null || booking.getId() != bookingDTO.getTicket_id()){
            throw new ApiException("Booking doesn't exist");
        }

        booking.setBookingStatus(bookingDTO.getBookingStatus());
        booking.setBookingNumber(bookingDTO.getBookingNumber());
        bookingRepository.save(booking);
    }

    public void deleteBooking(Integer id){
         Booking booking= bookingRepository.findBookingById(id);
         if (booking==null){
             throw new ApiException("Booking doesn't exist");
         }
         bookingRepository.delete(booking);
    }


    //    endpoint

    public void addBooking(Integer fanId ,BookingDTO bookingDTO){
        Ticket ticket = ticketRepository.findTicketById(bookingDTO.getTicket_id());
        Fan fan = fanRepository.findFanByFanId(fanId);

        if (fan == null) {
            throw new ApiException("Fan Not Found");
        } else if (ticket == null || ticket.getQuantity() == 0) {
            throw new ApiException("Ticket doesn't exist");
        }


        bookingDTO.setBookingStatus("Pending");
        ticket.setQuantity(ticket.getQuantity() - 1);
        Booking booking = new Booking(null , bookingDTO.getBookingNumber() , bookingDTO.getBookingStatus(), ticket);
        bookingRepository.save(booking);
    }



    public void changeBookingStatus(Integer bookingId , String bookingStatus){
        Booking booking= bookingRepository.findBookingById(bookingId);
        if (booking==null){
            throw new ApiException("Booking doesn't exist");
        }
        booking.setBookingStatus(bookingStatus);
        bookingRepository.save(booking);

    }



    public List<Fan> getBookingsByFanId(Integer fanId){
        List<Fan> fan = fanRepository.getAllBookingByFanId(fanId);
        if (fan==null){
            throw new ApiException("Booking doesn't exist");
        }

        return fan;
    }
}
