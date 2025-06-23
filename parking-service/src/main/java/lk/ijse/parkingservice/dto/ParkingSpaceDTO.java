package lk.ijse.parkingservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkingSpaceDTO {
    private UUID parkingSpaceId;
    @NotBlank(message = "Location is required")
    private String location;
    @NotNull(message = "Location code is required")
    private int locationCode;
    @NotBlank(message = "City is required")
    private String city;
    private boolean available;
    @NotBlank(message = "Email is required")
    private String email;
    private int payAmount;
    @NotNull(message = "Parking time is required")
    private LocalDateTime parkingTime;
}
