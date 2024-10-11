package com.ipn.mx.miniinventariosupabase.domain.repository;

import com.ipn.mx.miniinventariosupabase.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
