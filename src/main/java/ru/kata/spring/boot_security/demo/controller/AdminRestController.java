package ru.kata.spring.boot_security.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;


@RestController
@RequestMapping("api/admin")
public class AdminRestController {

    private final UserService userService;

    public AdminRestController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/admin")
    public ModelAndView getMainPage() {

        return new ModelAndView("main");
    }

    @GetMapping()
    public ResponseEntity<List<User>> getInfoUsersList() {
        List<User> userList = userService.getAllUsers();
        if (userList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); //204
        }
        return new ResponseEntity<>(userList, HttpStatus.OK); // 200
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404
        }
        return new ResponseEntity<>(user, HttpStatus.OK); // 200
    }

    @PostMapping
    public ResponseEntity<User>  getSaveUserForm(@RequestBody User user) {
        if (userService.addUser(user)) {
            return new ResponseEntity<>(user, HttpStatus.CREATED); // 201
        }
        return new ResponseEntity<>(user, HttpStatus.CONFLICT); // 400
    }

    @PutMapping
    public ResponseEntity<User> getUpdateUserForm(@RequestBody User user) {
        if (userService.updateUser(user)) {
            return new ResponseEntity<>(user, HttpStatus.OK); // 200
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT); // 400
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long>  getRemoveUserForm(@PathVariable long id) {
        if (userService.deleteUserById(id)) {
            return new ResponseEntity<>(id, HttpStatus.OK); // 200
        }
        return new ResponseEntity<>(id, HttpStatus.NOT_FOUND); // 404
    }
}
