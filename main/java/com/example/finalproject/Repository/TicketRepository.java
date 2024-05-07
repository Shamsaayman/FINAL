package com.example.finalproject.Repository;

import com.example.finalproject.Model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    Ticket findTicketById(Integer id);

    List<Ticket> findAllTicketByTicketDateIsAfter(LocalDate now);

}
