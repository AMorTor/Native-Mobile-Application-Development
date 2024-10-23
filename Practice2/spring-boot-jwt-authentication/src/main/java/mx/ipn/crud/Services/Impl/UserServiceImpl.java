package mx.ipn.crud.Services.Impl;


import mx.ipn.crud.Repositories.UserRepository;
import mx.ipn.crud.Services.UserService;
import mx.ipn.crud.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        return userRepository.findById(Math.toIntExact(id));
    }

    @Transactional
    public User save(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User must not be null");
        }

        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new IllegalArgumentException("Username must not be empty");
        }

        if (user.getLast_name() == null || user.getLast_name().isEmpty()) {
            throw new IllegalArgumentException("Last name must not be empty");
        }

        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email must not be empty");
        }

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password must not be empty");
        }

        return userRepository.save(user);
    }

    @Transactional
    public User update(Long id, User user) {
        User userToUpdate = userRepository.findById(Math.toIntExact(id)).orElseThrow();
        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setLast_name(user.getLast_name());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setPassword(user.getPassword());
        return userRepository.save(userToUpdate);
    }

    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(Math.toIntExact(id));
    }
}
