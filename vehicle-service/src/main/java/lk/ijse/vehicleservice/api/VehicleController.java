package lk.ijse.vehicleservice.api;

import lk.ijse.vehicleservice.dto.ParkingSessionDTO;
import lk.ijse.vehicleservice.dto.VehicleDTO;
import lk.ijse.vehicleservice.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/vehicles")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<String> registerVehicle(@RequestBody VehicleDTO vehicleDTO) {
        try {
            VehicleDTO savedVehicle = vehicleService.registerVehicle(vehicleDTO);
            return new ResponseEntity<>("Vehicle registered successfully with ID: " + savedVehicle.getVehicleId(), HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateVehicle(@PathVariable UUID id, @RequestBody VehicleDTO vehicleDTO) {
        try {
            VehicleDTO updatedVehicle = vehicleService.updateVehicle(id, vehicleDTO);
            return new ResponseEntity<>("Vehicle updated successfully with ID: " + updatedVehicle.getVehicleId(), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleDTO> getVehicleById(@PathVariable UUID id) {
        try {
            VehicleDTO vehicle = vehicleService.getVehicleById(id);
            return new ResponseEntity<>(vehicle, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<VehicleDTO>> getAllVehicles() {
        try {
            List<VehicleDTO> vehicles = vehicleService.getAllVehicles();
            return new ResponseEntity<>(vehicles, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable UUID id) {
        try {
            vehicleService.deleteVehicle(id);
            return new ResponseEntity<>("Vehicle deleted successfully", HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/parking-sessions")
    public ResponseEntity<String> startParkingSession(@RequestBody ParkingSessionRequest request) {
        try {
            ParkingSessionDTO session = vehicleService.startParkingSession(request.getVehicleId(), request.getParkingSpaceId());
            return new ResponseEntity<>("Parking session started successfully with ID: " + session.getSessionId(), HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/parking-sessions/{sessionId}/end")
    public ResponseEntity<String> endParkingSession(@PathVariable UUID sessionId) {
        try {
            ParkingSessionDTO session = vehicleService.endParkingSession(sessionId);
            return new ResponseEntity<>("Parking session ended successfully with ID: " + session.getSessionId(), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
