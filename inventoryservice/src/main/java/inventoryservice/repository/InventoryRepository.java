package inventoryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import inventoryservice.entity.Inventory;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Optional<Inventory> findByProductId(Long productId);
}