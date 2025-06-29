package com.example.reservationrestapi.entity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Flight flight;
    private String seatNumber;
}
