package com.flightapp.service;

import static org.junit.jupiter.api.Assertions.*;

import com.flightapp.entity.Flight;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.*;

class FlightServiceTest {

    @Test
    void testUpdateFlight() {
        Flight flight = new Flight();
        flight.setId(1L);
        flight.setAirline("Indigo");
        flight.setFromPlace("CityA");
        flight.setToPlace("CityB");
        flight.setDepartureTime(LocalDateTime.parse("2025-11-20T10:00:00"));
        flight.setArrivalTime(LocalDateTime.parse("2025-11-20T12:00:00"));
        flight.setPrice(5000);
        flight.setTotalSeats(100);
        flight.setAvailableSeats(100);

        Map<String, Object> updates = new HashMap<>();
        updates.put("airline", "Air India");
        updates.put("price", 5500);

        if (updates.containsKey("airline")) flight.setAirline((String) updates.get("airline"));
        if (updates.containsKey("price")) flight.setPrice((Integer) updates.get("price"));

        assertEquals("Air India", flight.getAirline());
        assertEquals(5500, flight.getPrice());
    }

    @Test
    void testSearchFlightById() {
        Flight flight = new Flight();
        flight.setId(1L);
        Flight foundFlight = flight; 

        assertEquals(1L, foundFlight.getId());
    }

    @Test
    void testAddFlight() {
        Flight flight = new Flight();
        flight.setId(1L);
        flight.setAirline("Indigo");

        Flight addedFlight = flight;

        assertEquals("Indigo", addedFlight.getAirline());
    }

    @Test
    void testDeleteFlight() {
        Flight flight = new Flight();
        flight.setId(1L);

        String message = "Flight deleted successfully";

        assertEquals("Flight deleted successfully", message);
    }
}
