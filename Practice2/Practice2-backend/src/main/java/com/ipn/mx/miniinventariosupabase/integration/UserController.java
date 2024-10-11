package com.ipn.mx.miniinventariosupabase.integration;

import com.ipn.mx.miniinventariosupabase.domain.entity.User;
import com.ipn.mx.miniinventariosupabase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping({"/", ""})
    private List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping({"/{id}", "/{id}/"})
    private User findById(@PathVariable Long id) {
        return userService.findById(id).orElseThrow();
    }

    @PostMapping({"/", ""})
    private User save(@RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping({"/{id}", "/{id}/"})
    private User update(@PathVariable Long id, @RequestBody User user) {
        return userService.update(id, user);
    }

    @DeleteMapping({"/{id}", "/{id}/"})
    private void deleteById(@PathVariable Long id) {
        userService.deleteById(id);
    }
}
