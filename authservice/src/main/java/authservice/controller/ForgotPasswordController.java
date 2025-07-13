package authservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class ForgotPasswordController {

    @Autowired
    private OtpService otpService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/forgot")
    public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        User user = userRepository.findByUsername(request.getUsername()).orElse(null);
        if (user == null) return ResponseEntity.notFound().build();
        String otp = otpService.generateOtp(user.getUsername());
        // In real app: send OTP via email/SMS
        return ResponseEntity.ok("OTP: " + otp);
    }

    @PostMapping("/reset")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest request) {
        if (!otpService.validateOtp(request.getUsername(), request.getOtp())) {
            return ResponseEntity.badRequest().body("Invalid OTP");
        }
        User user = userRepository.findByUsername(request.getUsername()).orElse(null);
        if (user == null) return ResponseEntity.notFound().build();
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
        otpService.clearOtp(request.getUsername());
        return ResponseEntity.ok("Password reset successful");
    }
}
