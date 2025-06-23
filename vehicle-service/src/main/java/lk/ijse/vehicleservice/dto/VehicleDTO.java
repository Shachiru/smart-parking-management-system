package lk.ijse.vehicleservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDTO {
    private UUID vehicleId;
    private String licensePlate;
    private String vehicleType;
    private UUID ownerId;
}