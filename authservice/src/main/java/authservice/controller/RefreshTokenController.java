package authservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import authservice.dto.AuthResponse;
import authservice.dto.RefreshTokenRequest;
import authservice.security.JwtUtil;

@RestController
@RequestMapping("/auth")
public class RefreshTokenController {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@RequestBody RefreshTokenRequest request) {
        String refreshToken = request.getRefreshToken();

        if (!jwtUtil.validateToken(refreshToken, "refresh")) {
            return ResponseEntity.badRequest().build();
        }

        String username = jwtUtil.extractUsername(refreshToken);
        String newAccessToken = jwtUtil.generateAccessToken(username);

        return ResponseEntity.ok(new AuthResponse(newAccessToken, refreshToken));
    }
}