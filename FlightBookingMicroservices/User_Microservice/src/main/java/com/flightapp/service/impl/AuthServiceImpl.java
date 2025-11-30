package com.flightapp.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightapp.entity.User;
import com.flightapp.repository.UserRepository;
import com.flightapp.service.AuthService;

import reactor.core.publisher.Mono;

@Service
public class AuthServiceImpl implements AuthService {

	private final UserRepository userRepository;

	private Map<String, String> userSessions = new HashMap<>();

	
	public AuthServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public Mono<User> register(User user) {
	    return userRepository.findByEmail(user.getEmail())
	        .flatMap(existing ->
	            Mono.<User>error(new RuntimeException("User already exists with email " + user.getEmail()))
	        )
	        .switchIfEmpty(
	            userRepository.save(user)
	        );
	}

	@Override
	public Mono<String> login(String email, String password) {
		return userRepository.findByEmail(email).switchIfEmpty(Mono.error(new RuntimeException("user is not found")))
				.flatMap(user -> {
					if (!user.getPassword().equals(password)) {
						return Mono.error(new RuntimeException("Invalid password"));
					}
					String sessionId = UUID.randomUUID().toString();
					userSessions.put(sessionId, email);
					return Mono.just(sessionId);
				});
	}

	@Override
	public Mono<User> getByEmail(String email) {
		return userRepository.findByEmail(email).switchIfEmpty(Mono.error(new RuntimeException("no user found")));
	}

}
