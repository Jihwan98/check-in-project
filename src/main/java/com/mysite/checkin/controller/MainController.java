package com.mysite.checkin.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/main")
    public String main() {
        return "main";
    }

    @GetMapping("/")
    public String root() {
        return "redirect:/main";
    }
}
