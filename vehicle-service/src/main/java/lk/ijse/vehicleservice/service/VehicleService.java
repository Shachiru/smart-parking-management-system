package lk.ijse.vehicleservice.service;

import lk.ijse.vehicleservice.dto.ParkingSessionDTO;
import lk.ijse.vehicleservice.dto.VehicleDTO;

import java.util.List;
import java.util.UUID;

public interface VehicleService {
    VehicleDTO registerVehicle(VehicleDTO vehicleDTO);

    VehicleDTO updateVehicle(UUID id, VehicleDTO vehicleDTO);

    VehicleDTO getVehicleById(UUID id);

    List<VehicleDTO> getAllVehicles();

    void deleteVehicle(UUID id);

    ParkingSessionDTO startParkingSession(UUID vehicleId, UUID parkingSpaceId);

    ParkingSessionDTO endParkingSession(UUID sessionId);
}
