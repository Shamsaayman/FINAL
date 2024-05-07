package com.example.finalproject.Repository;


import com.example.finalproject.Model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    Booking findBookingById(Integer id);

    List<Booking> getBookingsByBookingStatusIgnoreCase(String string);




}
