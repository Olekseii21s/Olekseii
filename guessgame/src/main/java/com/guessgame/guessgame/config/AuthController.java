package com.guessgame.guessgame.config;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AuthController {

    @GetMapping("/auth/user")
    public Map<String, Object> getAuthenticatedUser(@AuthenticationPrincipal OAuth2User principal) {
        return principal.getAttributes(); // Повертає email, name, picture та інше з Google
    }

    @GetMapping("/auth/login")
    public String login() {
        return "<a href=\"/oauth2/authorization/google\">Login with Google</a>";
    }

    @GetMapping("/auth/logout")
    public String logout() {
        return "<a href=\"/logout\">Logout</a>";
    }
}
