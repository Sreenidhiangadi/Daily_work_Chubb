package com.flightapp.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.flightapp.entity.Flight;
import com.flightapp.repository.FlightRepository;
import com.flightapp.service.FlightService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FlightServiceImpl implements FlightService {

	private final FlightRepository flightRepository;

	public FlightServiceImpl(FlightRepository flightRepository) {
		this.flightRepository = flightRepository;
	}

	@Override
	public Mono<Flight> addFlight(Flight flight) {
		return flightRepository.save(flight);
	}

	@Override
	public Flux<Flight> getAllFlights() {
		return flightRepository.findAll();
	}

//    @Override
//    public Mono<Flight> updateFlight(String id, Map<String, Object> updates) {
//        return flightRepository.findById(id)
//                .switchIfEmpty(Mono.error(new RuntimeException("Flight not found")))
//                .flatMap(flight -> {
//                    try {
//                        applyUpdates(flight, updates);
//                    } catch (Exception e) {
//                        return Mono.error(new RuntimeException("Invalid input format: " + e.getMessage()));
//                    }
//                    return flightRepository.save(flight);
//                });
//    }
//
//    private void applyUpdates(Flight flight, Map<String, Object> updates) {
//        setIfPresent(updates, "airline", val -> flight.setAirline((String) val));
//        setIfPresent(updates, "fromPlace", val -> flight.setFromPlace((String) val));
//        setIfPresent(updates, "toPlace", val -> flight.setToPlace((String) val));
//        setIfPresent(updates, "departureTime", val -> flight.setDepartureTime(LocalDateTime.parse(val.toString())));
//        setIfPresent(updates, "arrivalTime", val -> flight.setArrivalTime(LocalDateTime.parse(val.toString())));
//        setIfPresent(updates, "price", val -> flight.setPrice(Integer.parseInt(val.toString())));
//        setIfPresent(updates, "totalSeats", val -> flight.setTotalSeats(Integer.parseInt(val.toString())));
//        setIfPresent(updates, "availableSeats", val -> flight.setAvailableSeats(Integer.parseInt(val.toString())));
//    }
//
//    private <T> void setIfPresent(Map<String, Object> updates, String key, java.util.function.Consumer<Object> setter) {
//        if (updates.containsKey(key)) {
//            setter.accept(updates.get(key));
//        }
//    }

    @Override
    public Mono<Flight> searchFlightById(String id) {
        return flightRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Flight with this ID is not present")));
    }
	@Override
	public Mono<Flight> reserveSeats(String flightId, int seatCount) {
		return flightRepository.findById(flightId).switchIfEmpty(Mono.error(new RuntimeException("Flight not found")))
				.flatMap(flight -> {
					if (flight.getAvailableSeats() < seatCount) {
						return Mono.error(new RuntimeException("Not enough seats"));
					}
					flight.setAvailableSeats(flight.getAvailableSeats() - seatCount);
					return flightRepository.save(flight);
				});
	}

	@Override
	public Mono<Flight> releaseSeats(String flightId, int seatCount) {
		return flightRepository.findById(flightId).switchIfEmpty(Mono.error(new RuntimeException("Flight not found")))
				.flatMap(flight -> {
					flight.setAvailableSeats(flight.getAvailableSeats() + seatCount);
					return flightRepository.save(flight);
				});
	}

	@Override
	public Flux<Flight> searchFlights(String fromPlace, String toPlace, LocalDateTime start, LocalDateTime end) {
		return flightRepository.findByFromPlaceAndToPlaceAndDepartureTimeBetween(fromPlace, toPlace, start, end);
	}

	@Override
	public Flux<Flight> searchFlightsByAirline(String fromPlace, String toPlace, String airline) {
		return flightRepository.findByFromPlaceAndToPlaceAndAirline(fromPlace, toPlace, airline);
	}
}
