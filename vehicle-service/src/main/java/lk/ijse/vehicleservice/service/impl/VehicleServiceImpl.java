package lk.ijse.vehicleservice.service.impl;

import lk.ijse.vehicleservice.dto.ParkingSessionDTO;
import lk.ijse.vehicleservice.dto.VehicleDTO;
import lk.ijse.vehicleservice.entity.ParkingSession;
import lk.ijse.vehicleservice.entity.Vehicle;
import lk.ijse.vehicleservice.repo.ParkingSessionRepo;
import lk.ijse.vehicleservice.repo.VehicleRepo;
import lk.ijse.vehicleservice.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleRepo vehicleRepo;

    @Autowired
    private ParkingSessionRepo parkingSessionRepo;

    @Override
    public VehicleDTO registerVehicle(VehicleDTO vehicleDTO) {
        if (vehicleDTO.getOwnerId() == null) {
            throw new RuntimeException("Owner ID is required");
        }
        if (vehicleRepo.existsByLicensePlate(vehicleDTO.getLicensePlate())) {
            throw new RuntimeException("Vehicle with license plate " + vehicleDTO.getLicensePlate() + " already exists");
        }
        Vehicle vehicle = new Vehicle();
        vehicle.setLicensePlate(vehicleDTO.getLicensePlate());
        vehicle.setVehicleType(vehicleDTO.getVehicleType());
        vehicle.setOwnerId(vehicleDTO.getOwnerId());
        vehicle = vehicleRepo.save(vehicle);
        return mapToDTO(vehicle);
    }

    @Override
    public VehicleDTO updateVehicle(UUID id, VehicleDTO vehicleDTO) {
        Vehicle vehicle = vehicleRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
        if (!vehicle.getLicensePlate().equals(vehicleDTO.getLicensePlate()) &&
                vehicleRepo.existsByLicensePlate(vehicleDTO.getLicensePlate())) {
            throw new RuntimeException("License plate " + vehicleDTO.getLicensePlate() + " already taken");
        }
        vehicle.setLicensePlate(vehicleDTO.getLicensePlate());
        vehicle.setVehicleType(vehicleDTO.getVehicleType());
        vehicle.setOwnerId(vehicleDTO.getOwnerId());
        vehicle = vehicleRepo.save(vehicle);
        return mapToDTO(vehicle);
    }

    @Override
    public VehicleDTO getVehicleById(UUID id) {
        Vehicle vehicle = vehicleRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
        return mapToDTO(vehicle);
    }

    @Override
    public List<VehicleDTO> getAllVehicles() {
        return vehicleRepo.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteVehicle(UUID id) {
        if (!vehicleRepo.existsById(id)) {
            throw new RuntimeException("Vehicle not found");
        }
        vehicleRepo.deleteById(id);
    }

    @Override
    public ParkingSessionDTO startParkingSession(UUID vehicleId, UUID parkingSpaceId) {
        Vehicle vehicle = vehicleRepo.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
        if (parkingSessionRepo.findByVehicleIdAndStatus(vehicleId, "active").isPresent()) {
            throw new RuntimeException("Vehicle already has an active parking session");
        }
        if (parkingSessionRepo.findByParkingSpaceIdAndStatus(parkingSpaceId, "active").isPresent()) {
            throw new RuntimeException("Parking space is already occupied");
        }
        ParkingSession session = new ParkingSession();
        session.setVehicleId(vehicleId);
        session.setParkingSpaceId(parkingSpaceId);
        session.setEntryTime(LocalDateTime.now());
        session.setStatus("active");
        session = parkingSessionRepo.save(session);
        return mapToSessionDTO(session);
    }

    @Override
    public ParkingSessionDTO endParkingSession(UUID sessionId) {
        ParkingSession session = parkingSessionRepo.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Parking session not found"));
        if (!"active".equals(session.getStatus())) {
            throw new RuntimeException("Parking session is not active");
        }
        session.setExitTime(LocalDateTime.now());
        session.setStatus("completed");
        session = parkingSessionRepo.save(session);
        return mapToSessionDTO(session);
    }

    private VehicleDTO mapToDTO(Vehicle vehicle) {
        return new VehicleDTO(
                vehicle.getVehicleId(),
                vehicle.getLicensePlate(),
                vehicle.getVehicleType(),
                vehicle.getOwnerId()
        );
    }

    private ParkingSessionDTO mapToSessionDTO(ParkingSession session) {
        return new ParkingSessionDTO(
                session.getSessionId(),
                session.getVehicleId(),
                session.getParkingSpaceId(),
                session.getEntryTime(),
                session.getExitTime(),
                session.getStatus()
        );
    }
}
