package lk.ijse.vehicleservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkingSessionDTO {
    private UUID sessionId;
    private UUID vehicleId;
    private UUID parkingSpaceId;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private String status;
}
