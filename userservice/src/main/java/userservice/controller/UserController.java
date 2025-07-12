package userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import userservice.dto.UpdateUserRequest;
import userservice.dto.UserResponse;
import userservice.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getProfile(Authentication authentication) {
        String username = authentication.getName();
        return ResponseEntity.ok(userService.getUserProfile(username));
    }

    @PutMapping("/me")
    public ResponseEntity<Void> updateProfile(Authentication authentication, @RequestBody UpdateUserRequest req) {
        String username = authentication.getName();
        userService.updateUserProfile(username, req);
        return ResponseEntity.ok().build();
    }
}
