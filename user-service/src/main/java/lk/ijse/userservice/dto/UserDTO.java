package lk.ijse.userservice.dto;

import jakarta.validation.constraints.Email;
import lk.ijse.userservice.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private UUID userId;
    private String name;
    @Email(message = "Please provide a valid email address")
    private String email;
    private String password;
    private UserRole role;
    private Date createdAt;
}
