package lk.ijse.userservice.dto;

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
    private String email;
    private String password;
    private UserRole role;
    private Date createdAt;
}
