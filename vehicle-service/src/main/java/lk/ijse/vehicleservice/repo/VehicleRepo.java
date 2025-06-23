package lk.ijse.vehicleservice.repo;

import lk.ijse.vehicleservice.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VehicleRepo extends JpaRepository<Vehicle, UUID> {
    boolean existsByLicensePlate(String licensePlate);
}
