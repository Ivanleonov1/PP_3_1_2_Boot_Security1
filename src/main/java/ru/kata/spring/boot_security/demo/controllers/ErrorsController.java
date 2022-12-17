package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/error")
public class ErrorsController {

    @PostMapping
    public String showError(HttpServletResponse response, Model model) {
        model.addAttribute("response", response);
        model.addAttribute("messages", response.getHeaderNames());
        return "error";
    }
}
