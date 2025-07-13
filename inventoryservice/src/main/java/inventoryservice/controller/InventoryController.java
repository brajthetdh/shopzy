package inventoryservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import inventoryservice.dto.InventoryRequest;
import inventoryservice.dto.InventoryResponse;
import inventoryservice.service.InventoryService;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/{productId}")
    public ResponseEntity<InventoryResponse> getStock(@PathVariable Long productId) {
        return ResponseEntity.ok(inventoryService.getStock(productId));
    }

    @PostMapping("/add")
    public ResponseEntity<String> addStock(@RequestBody InventoryRequest request) {
        inventoryService.addStock(request);
        return ResponseEntity.ok("Stock added");
    }

    @PostMapping("/reduce")
    public ResponseEntity<String> reduceStock(@RequestBody InventoryRequest request) {
        inventoryService.reduceStock(request);
        return ResponseEntity.ok("Stock reduced");
    }
}