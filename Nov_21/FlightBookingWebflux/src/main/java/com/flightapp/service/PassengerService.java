package com.flightapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightapp.entity.Passenger;
import com.flightapp.repository.PassengerRepository;

import reactor.core.publisher.Flux;

@Service
public class PassengerService {
	@Autowired
private PassengerRepository passengerRepository;
	public Flux<Passenger> getPassenger(){
		return passengerRepository.findAll();
	}
	public Flux<Passenger> getPassengersByTicketId(Long ticketId){
	    return passengerRepository.findByTicketId(ticketId);
	}

	
}
