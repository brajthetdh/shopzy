package userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import userservice.entity.UserProfile;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    Optional<UserProfile> findByUsername(String username);
}