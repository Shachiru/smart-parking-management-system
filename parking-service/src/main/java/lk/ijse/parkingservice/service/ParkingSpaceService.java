package lk.ijse.parkingservice.service;

import lk.ijse.parkingservice.dto.ParkingSpaceDTO;

import java.util.List;
import java.util.UUID;

public interface ParkingSpaceService {
    ParkingSpaceDTO createParkingSpace(ParkingSpaceDTO parkingSpaceDTO);

    ParkingSpaceDTO updateParkingSpace(UUID id, ParkingSpaceDTO parkingSpaceDTO);

    ParkingSpaceDTO getParkingSpaceById(UUID id);

    List<ParkingSpaceDTO> getAllParkingSpaces();

    void deleteParkingSpace(UUID id);

    ParkingSpaceDTO reserveParkingSpace(UUID id, String email);

    ParkingSpaceDTO releaseParkingSpace(UUID id);

    List<ParkingSpaceDTO> findByLocation(String location);

    List<ParkingSpaceDTO> findByAvailability(boolean available);
}
