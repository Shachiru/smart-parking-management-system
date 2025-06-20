package lk.ijse.parkingservice.controller;

import lk.ijse.parkingservice.dto.ParkingSpaceDTO;
import lk.ijse.parkingservice.service.ParkingSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/v1/parking")
public class ParkingSpaceController {
    @Autowired
    private ParkingSpaceService parkingSpaceService;

    @PostMapping
    public ResponseEntity<ParkingSpaceDTO> createParkingSpace(@RequestBody ParkingSpaceDTO parkingSpaceDTO) {
        return new ResponseEntity<>(parkingSpaceService.createParkingSpace(parkingSpaceDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParkingSpaceDTO> updateParkingSpace(@PathVariable UUID id, @RequestBody ParkingSpaceDTO parkingSpaceDTO) {
        return new ResponseEntity<>(parkingSpaceService.updateParkingSpace(id, parkingSpaceDTO), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingSpaceDTO> getParkingSpaceById(@PathVariable UUID id) {
        return new ResponseEntity<>(parkingSpaceService.getParkingSpaceById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ParkingSpaceDTO>> getAllParkingSpaces() {
        return new ResponseEntity<>(parkingSpaceService.getAllParkingSpaces(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParkingSpace(@PathVariable UUID id) {
        parkingSpaceService.deleteParkingSpace(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}/reserve")
    public ResponseEntity<ParkingSpaceDTO> reserveParkingSpace(@PathVariable UUID id, @RequestParam String email) {
        return new ResponseEntity<>(parkingSpaceService.reserveParkingSpace(id, email), HttpStatus.OK);
    }

    @PutMapping("/{id}/release")
    public ResponseEntity<ParkingSpaceDTO> releaseParkingSpace(@PathVariable UUID id) {
        return new ResponseEntity<>(parkingSpaceService.releaseParkingSpace(id), HttpStatus.OK);
    }

    @GetMapping("/location")
    public ResponseEntity<List<ParkingSpaceDTO>> findByLocation(@RequestParam String location) {
        return new ResponseEntity<>(parkingSpaceService.findByLocation(location), HttpStatus.OK);
    }

    @GetMapping("/availability")
    public ResponseEntity<List<ParkingSpaceDTO>> findByAvailability(@RequestParam boolean available) {
        return new ResponseEntity<>(parkingSpaceService.findByAvailability(available), HttpStatus.OK);
    }
}
