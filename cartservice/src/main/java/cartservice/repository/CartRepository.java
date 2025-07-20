package cartservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cartservice.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUserId(Long userId);
    void deleteByUserId(Long userId);
}