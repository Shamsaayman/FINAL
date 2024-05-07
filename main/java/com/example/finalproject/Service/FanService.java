package com.example.finalproject.Service;

import com.example.finalproject.API.ApiException;
import com.example.finalproject.DTO.FanDTO;
import com.example.finalproject.Model.*;
import com.example.finalproject.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class FanService {
    private final FanRepository fanRepository;
    private final TicketRepository ticketRepository;
    private final BookingRepository bookingRepository;
    private final TeamAdminRepository teamAdminRepository;
    private final AuthRepository authRepository;
    private final SportRepository sportRepository;
    private  final  AthleteRepository athleteRepository;

    public List<Fan> getAllFans(){
        return fanRepository.findAll();
    }


    public void RegisterFan( FanDTO fanDTO){
        User u = new User(null, fanDTO.getUsername(), fanDTO.getPassword(), "FAN",null,null,null,null);
        String hashPassword = new BCryptPasswordEncoder().encode(fanDTO.getPassword());
        u.setPassword(hashPassword);
        authRepository.save(u);

        Fan f = new Fan(null, fanDTO.getUsername(), fanDTO.getPassword(), fanDTO.getEmail(), fanDTO.getAge(), fanDTO.getFavoriteSport(), u,null);
        fanRepository.save(f);
    }

    public void updateFan(Integer fanId , FanDTO fanDTO){
        Fan f = fanRepository.findFanByFanId(fanId);
        f.setAge(fanDTO.getAge());
        f.setUsername(fanDTO.getUsername());
        f.setEmail(fanDTO.getEmail());
        f.setPassword(fanDTO.getPassword());
        f.setAge(fanDTO.getAge());
        f.setFavoriteSport(fanDTO.getFavoriteSport());
        fanRepository.save(f);
    }

    public void deleteFan(Integer fanId){
        Fan fan1 = fanRepository.findFanByFanId(fanId);
        fanRepository.delete(fan1);
    }


//    endpoint


    public String FanLogIn(Integer fanId , String password){
        Fan fan1 = fanRepository.findFanByFanId(fanId);
        if (fan1 == null || !fan1.getPassword().equals(password)){
            throw new ApiException("Invalid Id or Password");
        }
        return "Welcome " + fan1.getUsername() + "To Olympic Sphere";
    }


    public List<Ticket> viewTicketToBook(){
        List<Ticket> tickets = ticketRepository.findAllTicketByTicketDateIsAfter(LocalDate.now());

        for (Ticket ticket : tickets){
            if (ticket.getQuantity() >=1){

               return tickets ;
            }
        }

        return tickets;
    }


    public Ticket getTicketDetails(Integer ticketId){
        Ticket ticket = ticketRepository.findTicketById(ticketId);
        if (ticket == null){
            throw new ApiException("Invalid Id");
        }
        return ticket;
    }


    public List<Booking> viewMyBookedTicket(){
        List<Booking> booking = bookingRepository.getBookingsByBookingStatusIgnoreCase("Attended");
        if (booking == null){
            throw new ApiException("Invalid Id");
        }
        return booking;
    }


    public List<Booking> viewBookingByStatus(String status){
        List<Booking> booking = bookingRepository.getBookingsByBookingStatusIgnoreCase(status);
        if (booking == null){
            throw new ApiException("Invalid Id");
        }
        return booking;
    }



    public void changeStatusToAttended(Integer BookingId){
        Booking booking = bookingRepository.findBookingById(BookingId);
        if (booking == null){
            throw new ApiException("Invalid Id");
        }
        booking.setBookingStatus("Attended");
        bookingRepository.save(booking);
    }


    public void cancelBooking(Integer BookingId){
        Booking booking = bookingRepository.findBookingById(BookingId);
        if (booking == null){
            throw new ApiException("Invalid Id");
        }
        booking.setBookingStatus("Canceled");
        bookingRepository.save(booking);
    }

    public List<String> getAllTeams(){
        List<TeamAdmin>teamAdmins=teamAdminRepository.findAll();
        List<String>teams=new ArrayList<>();
        for(TeamAdmin team:teamAdmins){
            teams.add("Name: "+team.getTeamName()+" Number of Athletes: "+team.getAthleteSet().size());
        }
        return teams;
    }

    public List<Sport> viewAllSports(){
        return sportRepository.findAll();
    }

    public String athelteAtchevments(String ahthelteName){
        Athlete athlete= athleteRepository.findAthleteByUsername(ahthelteName);
        if(athlete==null){
            throw new ApiException("Athelte Not Found");
        }
        return "Athlete Atchevments is: "+athlete.getAchievements();
    }




}
