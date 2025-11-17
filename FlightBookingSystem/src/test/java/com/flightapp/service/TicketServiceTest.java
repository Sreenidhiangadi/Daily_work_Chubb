package com.flightapp.service;

import static org.junit.jupiter.api.Assertions.*;

import com.flightapp.entity.Flight;
import com.flightapp.entity.FlightType;
import com.flightapp.entity.Passenger;
import com.flightapp.entity.Ticket;
import com.flightapp.entity.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class TicketServiceTest {

    @Test
    void testBookTicketOneWay() {
        User user = new User();
        user.setId(1L);
        user.setName("Sreenidhi");
        user.setEmail("sreenidhi@gmail.com");

        Flight departureFlight = new Flight();
        departureFlight.setId(1L);
        departureFlight.setPrice(5000);
        departureFlight.setAvailableSeats(10);
        departureFlight.setDepartureTime(LocalDateTime.now().plusDays(2));

     
        Passenger passenger = new Passenger();
        passenger.setName("Passenger1");
        List<Passenger> passengers = new ArrayList<>();
        passengers.add(passenger);

        int seatCount = passengers.size();
        assertTrue(departureFlight.getAvailableSeats() >= seatCount, "Enough seats available");

        departureFlight.setAvailableSeats(departureFlight.getAvailableSeats() - seatCount);

        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setDepartureFlight(departureFlight);
        ticket.setTripType(FlightType.ONE_WAY);
        ticket.setPassengers(passengers);
        ticket.setPnr(UUID.randomUUID().toString().substring(0, 8));
        ticket.setBookingTime(LocalDateTime.now());
        ticket.setTotalPrice((double) (departureFlight.getPrice() * seatCount));


        for (Passenger p : passengers) {
            p.setTicket(ticket);
        }

        
        assertEquals(user, ticket.getUser());
        assertEquals(FlightType.ONE_WAY, ticket.getTripType());
        assertEquals(9, departureFlight.getAvailableSeats());
        assertNotNull(ticket.getPnr());
        assertEquals(5000, ticket.getTotalPrice());
    }

    @Test
    void testCancelTicket() {
        User user = new User();
        user.setEmail("sreenidhi@gmail.com");

        Flight departureFlight = new Flight();
        departureFlight.setAvailableSeats(5);
        departureFlight.setDepartureTime(LocalDateTime.now().plusDays(2));

        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setDepartureFlight(departureFlight);
        ticket.setCanceled(false);

        List<Passenger> passengers = new ArrayList<>();
        Passenger passenger = new Passenger();
        passengers.add(passenger);
        ticket.setPassengers(passengers);

        if (!ticket.getUser().getEmail().equals("sreenidhi@gmail.com")) {
            fail("Cannot cancel another user's ticket");
        }

        assertFalse(ticket.isCanceled(), "Ticket is not already canceled");
        assertTrue(LocalDateTime.now().plusHours(24).isBefore(departureFlight.getDepartureTime()), 
                   "Cancellation allowed");

       
        int seats = ticket.getPassengers().size();
        departureFlight.setAvailableSeats(departureFlight.getAvailableSeats() + seats);
        ticket.setCanceled(true);

    
        assertTrue(ticket.isCanceled());
        assertEquals(6, departureFlight.getAvailableSeats());
    }
}
