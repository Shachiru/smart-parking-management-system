package lk.ijse.parkingservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "parking_space")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ParkingSpace {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID parkingSpaceId;

    @Column(nullable = false)
    private String location;

    @Column(unique = true)
    private int locationCode;

    @Column(nullable = false)
    private String city;

    @Column(columnDefinition = "BOOLEAN")
    private boolean available;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private int payAmount;

    @Column(nullable = false)
    private LocalDateTime parkingTime;
}