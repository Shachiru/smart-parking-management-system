package lk.ijse.vehicleservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "parking_sessions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkingSession {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID sessionId;

    @Column(nullable = false)
    private UUID vehicleId;

    @Column(nullable = false)
    private UUID parkingSpaceId;

    @Column(nullable = false)
    private LocalDateTime entryTime;

    @Column
    private LocalDateTime exitTime;

    @Column(nullable = false)
    private String status;
}
