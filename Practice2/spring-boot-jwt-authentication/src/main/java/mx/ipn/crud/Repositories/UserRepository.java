package mx.ipn.crud.Repositories;

import java.util.Optional;

import mx.ipn.crud.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String username);
}
