package lk.ijse.vehicleservice.client;

import lk.ijse.vehicleservice.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "user-service")
public interface UserServiceClient {
    @GetMapping("/user-service/api/v1/users/{id}")
    ResponseEntity<UserDTO> getUserById(@PathVariable("id") UUID id);
}
