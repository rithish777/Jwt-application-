package com.example.jwtgeneration;

import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/jwt")
public class JwtController {

    @PostMapping("/generate")
    public Map<String, String> generate(
            @RequestBody Map<String, String> data) {

        String token = JwtUtil.generateToken(
            data.get("username"),
            data.get("role")
        );

        return Map.of("token", token);
    }

    @PostMapping("/validate")
    public Map<String, Boolean> validate(
            @RequestBody Map<String, String> data) {

        boolean valid = JwtUtil.validateToken(
            data.get("token")
        );

        return Map.of("valid", valid);
    }
}