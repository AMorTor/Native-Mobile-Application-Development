package com.ipn.mx.miniinventariosupabase.service;

import com.ipn.mx.miniinventariosupabase.domain.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    Optional<User> findById(Long id);
    User save(User user);
    User update(Long id, User user);
    void deleteById(Long id);
}
