package com.example.demo.service;

import com.example.demo.config.RabbitMQConfig;
import com.example.demo.feign.DoctorClient;
import com.example.demo.model.Appointment;
import com.example.demo.repository.AppointmentRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository repo;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private DoctorClient doctorClient;  

    public Appointment createAppointment(Appointment appointment) {

        // Doctor validate karo pehle
        Object doctor = doctorClient.getDoctorById(appointment.getDoctorId());
        if (doctor == null) {
            throw new RuntimeException("Doctor not found: "
                + appointment.getDoctorId());
        }

        appointment.setStatus("CONFIRMED");
        Appointment saved = repo.save(appointment);

        rabbitTemplate.convertAndSend(
            RabbitMQConfig.EXCHANGE,
            RabbitMQConfig.ROUTING_KEY,
            saved
        );
        System.out.println("Event published: " + saved);
        return saved;
    }

    public List<Appointment> getAllAppointments() {
        return repo.findAll();
    }
}