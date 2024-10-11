package com.ipn.mx.miniinventariosupabase.service.impl;

import com.ipn.mx.miniinventariosupabase.domain.entity.User;
import com.ipn.mx.miniinventariosupabase.domain.repository.UserRepository;
import com.ipn.mx.miniinventariosupabase.service.UserService;
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
        return userRepository.findById(id);
    }

    @Transactional(readOnly = false)
    public User save(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public User update(Long id, User user) {
        User userToUpdate = userRepository.findById(id).orElseThrow();
        userToUpdate.setId_user(user.getId_user());
        userToUpdate.setUser_name(user.getUser_name());
        userToUpdate.setLast_name(user.getLast_name());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setUser_u(user.getUser_u());
        userToUpdate.setPassword_u(user.getPassword_u());
        userToUpdate.setCreation_date(user.getCreation_date());
        userToUpdate.setAge(user.getAge());
        userToUpdate.setGender(user.getGender());

        return userRepository.save(userToUpdate);
    }

    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
