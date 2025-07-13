package authservice.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class OtpService {
    private final Map<String, String> otpStore = new HashMap<>();

    public String generateOtp(String username) {
        String otp = String.valueOf(100000 + (int)(Math.random() * 900000));
        otpStore.put(username, otp);
        return otp;
    }

    public boolean validateOtp(String username, String otp) {
        return otpStore.containsKey(username) && otpStore.get(username).equals(otp);
    }

    public void clearOtp(String username) {
        otpStore.remove(username);
    }
}