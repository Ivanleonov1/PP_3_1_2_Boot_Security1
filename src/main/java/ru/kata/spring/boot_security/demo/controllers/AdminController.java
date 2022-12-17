package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.UserEntity;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.Collections;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public AdminController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping(params = "logout")
    public String logout() {
        return "redirect:/logout";
    }

    //--- CREATE

    /***
     * Подготовить объект User для сохранения в базу
     */
    @GetMapping("/new")
    public String createForm(@ModelAttribute("user") UserEntity user) {
        return "admin/new";
    }

    /***
     * Сохранить в базу
     */
    @PostMapping
    public String create(@ModelAttribute("user") UserEntity user) {
        user.setRoles(Collections.singleton(roleService.createRole("USER")));
        user.setPassword(passwordEncoder.encode("0000"));
        user.setUsername(user.getNickname().toLowerCase());
        if (userService.loadUserByUsername(user.getUsername()) == null) {
            userService.createOrUpdate(user);
            return "redirect:/admin";
        } else {
            return "redirect:/admin/new?error=login is exists";
        }
    }

    //--- READ

    /***
     * Получить всех пользователей
     */
    @GetMapping
    public String index(Model model) {
        model.addAttribute("users", userService.getList());
        return "admin/all";
    }

    /***
     * Получить одного пользователя
     */
    @GetMapping("/{id}")
    public String read(Model model, @PathVariable(name = "id") Long id) {
        model.addAttribute("user", userService.get(id));
        return "user/profile";
    }

    //--- UPDATE

    /***
     * Подготовить изменения для объекта User
     */
    @GetMapping("/{id}/edit")
    public String editForm(Model model, @PathVariable(name = "id") Long id) {
        model.addAttribute("user", userService.get(id));
        return "admin/edit";
    }

    /***
     * Сохранить изменённого пользователя
     */
    @PatchMapping()
    public String edit(@ModelAttribute("user") UserEntity user) {
        UserEntity oldUser = userService.get(user.getId());
        user.setUsername(oldUser.getUsername());
        user.setPassword(oldUser.getPassword());
        user.setRoles(oldUser.getRoles());
        userService.createOrUpdate(user);
        return "redirect:/admin";
    }

    //--- DELETE

    /***
     * Удалить пользователя (подготовки объекта User не требуется)
     */
    @DeleteMapping()
    public String delete(Model model, @Param("id") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }

}
