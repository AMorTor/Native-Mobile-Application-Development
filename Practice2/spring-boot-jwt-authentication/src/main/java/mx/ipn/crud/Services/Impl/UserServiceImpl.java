package mx.ipn.crud.Services.Impl;

import mx.ipn.crud.Model.DTO.UserDTO;
import mx.ipn.crud.Repositories.UserRepository;
import mx.ipn.crud.Services.UserService;
import mx.ipn.crud.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<UserDTO> findById(Long id) {
        return userRepository.findById(Math.toIntExact(id))
                .map(this::convertToDTO);
    }
    @Transactional
    public User save(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User must not be null");
        }

        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new IllegalArgumentException("Username must not be empty");
        }

        if (user.getLastname() == null || user.getLastname().isEmpty()) {
            throw new IllegalArgumentException("Last name must not be empty");
        }

        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email must not be empty");
        }

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password must not be empty");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Transactional
    public User update(Long id, User user) {
        User userToUpdate = userRepository.findById(Math.toIntExact(id)).orElseThrow();

        if (user.getUsername() != null && !user.getUsername().isEmpty()) {
            userToUpdate.setUsername(user.getUsername());
        }

        if (user.getLastname() != null && !user.getLastname().isEmpty()) {
            userToUpdate.setLastname(user.getLastname());
        }

        if (user.getEmail() != null && !user.getEmail().isEmpty()) {
            userToUpdate.setEmail(user.getEmail());
        }

        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            userToUpdate.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        if (user.getRole() != null) {
            userToUpdate.setRole(user.getRole());
        }

        return userRepository.save(userToUpdate);
    }

    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(Math.toIntExact(id));
    }

    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setLastname(user.getLastname());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole().name());
        return userDTO;
    }
}
