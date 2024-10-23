package mx.ipn.crud.Controllers;


import mx.ipn.crud.Model.DTO.UserDTO;
import mx.ipn.crud.Services.UserService;
import mx.ipn.crud.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping({"/", ""})
    private List<UserDTO> findAll() {
        return userService.findAll();
    }

    @GetMapping({"/{id}", "/{id}/"})
    private UserDTO findById(@PathVariable Long id) {
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