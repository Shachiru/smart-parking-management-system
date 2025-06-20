package lk.ijse.parkingservice.repo;

import lk.ijse.parkingservice.entity.ParkingSpace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ParkingSpaceRepo extends JpaRepository<ParkingSpace, UUID> {

}
