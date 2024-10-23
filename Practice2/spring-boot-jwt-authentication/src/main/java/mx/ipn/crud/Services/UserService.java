package mx.ipn.crud.Services;

import mx.ipn.crud.Model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public interface UserService {
    List<User> findAll();
    Optional<User> findById(Long id);
    User save(User product);
    User update(Long id, User product);
    void deleteById(Long id);
}
