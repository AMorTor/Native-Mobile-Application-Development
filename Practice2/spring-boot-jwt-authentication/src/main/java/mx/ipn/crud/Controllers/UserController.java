package mx.ipn.crud.Controllers;


import mx.ipn.crud.Services.UserService;
import mx.ipn.crud.User.User;
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
    private User save(@RequestBody User product) {
        return userService.save(product);
    }

    @PutMapping({"/{id}", "/{id}/"})
    private User update(@PathVariable Long id, @RequestBody User product) {
        return userService.update(id, product);
    }

    @DeleteMapping({"/{id}", "/{id}/"})
    private void deleteById(@PathVariable Long id) {
        userService.deleteById(id);
    }


}