package lk.ijse.parkingservice.service.impl;

import lk.ijse.parkingservice.dto.ParkingSpaceDTO;
import lk.ijse.parkingservice.entity.ParkingSpace;
import lk.ijse.parkingservice.repo.ParkingSpaceRepo;
import lk.ijse.parkingservice.repo.UserRepo;
import lk.ijse.parkingservice.service.ParkingSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class ParkingSpaceServiceImpl implements ParkingSpaceService {
    @Autowired
    private ParkingSpaceRepo parkingSpaceRepository;

    @Autowired
    private UserRepo userRepository;

    @Override
    public ParkingSpaceDTO createParkingSpace(ParkingSpaceDTO parkingSpaceDTO) {
        if (parkingSpaceRepository.existsByLocation(parkingSpaceDTO.getLocation()) ||
                parkingSpaceRepository.existsByLocationCode(parkingSpaceDTO.getLocationCode())) {
            throw new RuntimeException("Location or Location Code already exists");
        }
        if (!userRepository.existsByEmail(parkingSpaceDTO.getEmail())) {
            throw new RuntimeException("User with email " + parkingSpaceDTO.getEmail() + " not found");
        }
        ParkingSpace parkingSpace = mapToEntity(parkingSpaceDTO);
        parkingSpace = parkingSpaceRepository.save(parkingSpace);
        return mapToDTO(parkingSpace);
    }

    @Override
    public ParkingSpaceDTO updateParkingSpace(UUID id, ParkingSpaceDTO parkingSpaceDTO) {
        ParkingSpace existingSpace = parkingSpaceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parking Space not found"));
        if (!existingSpace.getLocation().equals(parkingSpaceDTO.getLocation()) &&
                parkingSpaceRepository.existsByLocation(parkingSpaceDTO.getLocation())) {
            throw new RuntimeException("Location already exists");
        }
        if (existingSpace.getLocationCode() != parkingSpaceDTO.getLocationCode() &&
                parkingSpaceRepository.existsByLocationCode(parkingSpaceDTO.getLocationCode())) {
            throw new RuntimeException("Location Code already exists");
        }
        if (!userRepository.existsByEmail(parkingSpaceDTO.getEmail())) {
            throw new RuntimeException("User with email " + parkingSpaceDTO.getEmail() + " not found");
        }
        updateEntity(existingSpace, parkingSpaceDTO);
        existingSpace = parkingSpaceRepository.save(existingSpace);
        return mapToDTO(existingSpace);
    }

    @Override
    public ParkingSpaceDTO getParkingSpaceById(UUID id) {
        ParkingSpace parkingSpace = parkingSpaceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parking Space not found"));
        return mapToDTO(parkingSpace);
    }

    @Override
    public List<ParkingSpaceDTO> getAllParkingSpaces() {
        return parkingSpaceRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteParkingSpace(UUID id) {
        if (!parkingSpaceRepository.existsById(id)) {
            throw new RuntimeException("Parking Space not found");
        }
        parkingSpaceRepository.deleteById(id);
    }

    @Override
    public ParkingSpaceDTO reserveParkingSpace(UUID id, String email) {
        ParkingSpace parkingSpace = parkingSpaceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parking Space not found"));
        if (!userRepository.existsByEmail(email)) {
            throw new RuntimeException("User with email " + email + " not found");
        }
        if (!parkingSpace.isAvailable()) {
            throw new RuntimeException("Parking Space is already reserved");
        }
        parkingSpace.setAvailable(false);
        parkingSpace.setEmail(email);
        parkingSpace.setParkingTime(LocalDateTime.now());
        parkingSpace = parkingSpaceRepository.save(parkingSpace);
        return mapToDTO(parkingSpace);
    }

    @Override
    public ParkingSpaceDTO releaseParkingSpace(UUID id) {
        ParkingSpace parkingSpace = parkingSpaceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parking Space not found"));
        if (parkingSpace.isAvailable()) {
            throw new RuntimeException("Parking Space is already available");
        }
        parkingSpace.setAvailable(true);
        parkingSpace.setEmail(null);
        parkingSpace.setParkingTime(null);
        parkingSpace.setPayAmount(0);
        parkingSpace = parkingSpaceRepository.save(parkingSpace);
        return mapToDTO(parkingSpace);
    }

    @Override
    public List<ParkingSpaceDTO> findByLocation(String location) {
        return parkingSpaceRepository.findAll()
                .stream()
                .filter(space -> space.getLocation().equalsIgnoreCase(location))
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ParkingSpaceDTO> findByAvailability(boolean available) {
        return parkingSpaceRepository.findAll()
                .stream()
                .filter(space -> space.isAvailable() == available)
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private ParkingSpaceDTO mapToDTO(ParkingSpace parkingSpace) {
        ParkingSpaceDTO dto = new ParkingSpaceDTO();
        dto.setParkingSpaceId(parkingSpace.getParkingSpaceId());
        dto.setLocation(parkingSpace.getLocation());
        dto.setLocationCode(parkingSpace.getLocationCode());
        dto.setCity(parkingSpace.getCity());
        dto.setAvailable(parkingSpace.isAvailable());
        dto.setEmail(parkingSpace.getEmail());
        dto.setPayAmount(parkingSpace.getPayAmount());
        dto.setParkingTime(parkingSpace.getParkingTime());
        return dto;
    }

    private ParkingSpace mapToEntity(ParkingSpaceDTO dto) {
        ParkingSpace parkingSpace = new ParkingSpace();
        parkingSpace.setParkingSpaceId(dto.getParkingSpaceId());
        parkingSpace.setLocation(dto.getLocation());
        parkingSpace.setLocationCode(dto.getLocationCode());
        parkingSpace.setCity(dto.getCity());
        parkingSpace.setAvailable(dto.isAvailable());
        parkingSpace.setEmail(dto.getEmail());
        parkingSpace.setPayAmount(dto.getPayAmount());
        parkingSpace.setParkingTime(dto.getParkingTime());
        return parkingSpace;
    }

    private void updateEntity(ParkingSpace parkingSpace, ParkingSpaceDTO dto) {
        parkingSpace.setLocation(dto.getLocation());
        parkingSpace.setLocationCode(dto.getLocationCode());
        parkingSpace.setCity(dto.getCity());
        parkingSpace.setAvailable(dto.isAvailable());
        parkingSpace.setEmail(dto.getEmail());
        parkingSpace.setPayAmount(dto.getPayAmount());
        parkingSpace.setParkingTime(dto.getParkingTime());
    }
}
