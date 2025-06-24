package lk.ijse.vehicleservice.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkingSessionRequest {
    private UUID vehicleId;
    private UUID parkingSpaceId;
}
