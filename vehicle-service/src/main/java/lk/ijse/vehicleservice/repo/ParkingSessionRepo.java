package lk.ijse.vehicleservice.repo;

import lk.ijse.vehicleservice.entity.ParkingSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ParkingSessionRepo extends JpaRepository<ParkingSession, UUID> {
    Optional<ParkingSession> findByVehicleIdAndStatus(UUID vehicleId, String status);
    Optional<ParkingSession> findByParkingSpaceIdAndStatus(UUID parkingSpaceId, String status);
}
