package lk.ijse.parkingservice.dto;

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
    private String location;
    private int locationCode;
    private String city;
    private boolean available;
    private String email;
    private int payAmount;
    private LocalDateTime parkingTime;
}
