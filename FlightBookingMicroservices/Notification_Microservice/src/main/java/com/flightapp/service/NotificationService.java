package com.flightapp.service;

import com.flightapp.messaging.BookingEvent;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private static final Logger log = LoggerFactory.getLogger(NotificationService.class);

    private final JavaMailSender mailSender;

    @KafkaListener(
            topics = "booking-events",
            groupId = "notification-service",
            containerFactory = "bookingEventKafkaListenerContainerFactory")
    public void handleBookingEvent(BookingEvent event) {
        log.info("Received booking event: {}", event);
        String subject;
        String body;

        if ("BOOKING_CONFIRMED".equals(event.getEventType())) {
            subject = "Your flight booking is confirmed - PNR " + event.getPnr();
            body = "Thank you for booking. Your PNR is " + event.getPnr()
                    + " and total price is " + event.getTotalPrice();
        } else if ("BOOKING_CANCELLED".equals(event.getEventType())) {
            subject = "Your flight booking is cancelled - PNR " + event.getPnr();
            body = "Your booking with PNR " + event.getPnr() + " has been cancelled.";
        } else {
            subject = "Flight booking update";
            body = "Update for booking PNR: " + event.getPnr();
        }

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(event.getUserEmail());
            message.setSubject(subject);
            message.setText(body);
            mailSender.send(message);
        } catch (Exception e) {
            log.error("Failed to send email", e);
        }
    }
}
