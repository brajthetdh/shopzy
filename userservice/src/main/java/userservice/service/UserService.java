package userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import userservice.dto.UpdateUserRequest;
import userservice.dto.UserResponse;
import userservice.entity.UserProfile;
import userservice.exception.ResourceNotFoundException;
import userservice.repository.UserProfileRepository;

@Service
public class UserService {
    @Autowired
    private UserProfileRepository userProfileRepository;

    public UserResponse getUserProfile(String username) {
        UserProfile user = userProfileRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return UserResponse.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .phone(user.getPhone())
                .gender(user.getGender())
                .build();
    }

    public void updateUserProfile(String username, UpdateUserRequest req) {
        UserProfile user = userProfileRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        user.setFullName(req.getFullName());
        user.setPhone(req.getPhone());
        user.setGender(req.getGender());

        userProfileRepository.save(user);
    }
}