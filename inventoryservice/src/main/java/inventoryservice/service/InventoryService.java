package inventoryservice.service;

import org.springframework.stereotype.Service;

import inventoryservice.dto.InventoryRequest;
import inventoryservice.dto.InventoryResponse;
import inventoryservice.entity.Inventory;
import inventoryservice.repository.InventoryRepository;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public InventoryResponse getStock(Long productId) {
        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("Product not found in inventory"));
        return new InventoryResponse(inventory.getProductId(), inventory.getQuantity());
    }

    public void addStock(InventoryRequest request) {
        Inventory inventory = inventoryRepository.findByProductId(request.getProductId())
                .orElse(null);
        if (inventory == null) {
            inventory = new Inventory();
            inventory.setProductId(request.getProductId());
            inventory.setQuantity(request.getQuantity());
        } else {
            inventory.setQuantity(inventory.getQuantity() + request.getQuantity());
        }
        inventoryRepository.save(inventory);
    }

    public void reduceStock(InventoryRequest request) {
        Inventory inventory = inventoryRepository.findByProductId(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found in inventory"));
        if (inventory.getQuantity() < request.getQuantity()) {
            throw new RuntimeException("Insufficient stock");
        }
        inventory.setQuantity(inventory.getQuantity() - request.getQuantity());
        inventoryRepository.save(inventory);
    }
}