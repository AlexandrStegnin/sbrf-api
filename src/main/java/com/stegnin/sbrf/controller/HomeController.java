package com.stegnin.sbrf.controller;

import com.stegnin.sbrf.service.AuthService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alexandr Stegnin
 */

@RestController
public class HomeController {

    private final AuthService authService;

    public HomeController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping(path = "/")
    public String hello() {
        return authService.getWelcomeMessage();
    }

    @ResponseBody
    @GetMapping(path = "/secured")
    public String securedArea() {
        return authService.getAuthInfo();
    }

}
