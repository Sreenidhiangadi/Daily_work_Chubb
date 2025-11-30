package com.flightapp.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.flightapp.dto.FlightSearchRequest;
import com.flightapp.entity.Flight;
import com.flightapp.service.FlightService;

import jakarta.validation.Valid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/flight")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping("/airline/inventory/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<String> addFlight(@Valid @RequestBody Flight flight) {
        return flightService.addFlight(flight)
        		.map(saved -> "Flight added successfully with id: " + saved.getId());
    }
    
    @PostMapping("/search")
    public Flux<Flight> searchFlights(@RequestBody FlightSearchRequest request) {
        return flightService.searchFlights(
                request.getFromPlace(),
                request.getToPlace(),
                request.getStartTime(),
                request.getEndTime());
    }
    
    @PutMapping("/internal/{id}/reserve/{seatCount}")
    public Mono<Flight> reserveSeats(@PathVariable String id, @PathVariable int seatCount) {
        return flightService.reserveSeats(id, seatCount);
    }
    
    @PutMapping("/internal/{id}/release/{seatCount}")
    public Mono<Flight> releaseSeats(@PathVariable String id, @PathVariable int seatCount) {
        return flightService.releaseSeats(id, seatCount);
    }
    
    @GetMapping("/getallflights")
    public Flux<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }
    
    @PostMapping("/search/airline")
    public Flux<Flight> searchByAirline(@RequestBody Map<String, String> body) {
        return flightService.searchFlightsByAirline(
                body.get("fromPlace"),
                body.get("toPlace"),
                body.get("airline")
        );
    }
    
    @GetMapping("/{id}")
    public Mono<Flight> getFlightById(@PathVariable String id) {
        return flightService.searchFlightById(id);
    }
//    @PutMapping("/{id}")
//    public Mono<Flight> updateFlight(
//            @PathVariable String id,
//            @RequestBody Map<String, Object> updates) {
//        return flightService.updateFlight(id, updates);
//    }
//
//
   

}
