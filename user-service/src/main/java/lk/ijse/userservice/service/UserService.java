package lk.ijse.userservice.service;

import lk.ijse.userservice.dto.UserDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserDTO registerUser(UserDTO userDTO);
    UserDTO updateUser(UUID id, UserDTO userDTO);
    UserDTO getUserById(UUID id);
    List<UserDTO> getAllUsers();
    void deleteUser(UUID id);
    UserDTO authenticateUser(String email, String password);
}
