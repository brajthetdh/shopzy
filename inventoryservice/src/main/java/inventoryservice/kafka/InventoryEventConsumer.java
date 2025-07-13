package inventoryservice.kafka;

import org.json.JSONObject;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import inventoryservice.dto.InventoryRequest;
import inventoryservice.service.InventoryService;

@Component
public class InventoryEventConsumer {

    private final InventoryService inventoryService;

    public InventoryEventConsumer(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @KafkaListener(topics = "order.placed", groupId = "inventory-group")
    public void consumeOrderPlaced(String message) {
        JSONObject json = new JSONObject(message);
        Long productId = json.getLong("productId");
        Integer quantity = json.getInt("quantity");
        inventoryService.reduceStock(new InventoryRequest(productId, quantity));
    }

    @KafkaListener(topics = "order.cancelled", groupId = "inventory-group")
    public void consumeOrderCancelled(String message) {
        JSONObject json = new JSONObject(message);
        Long productId = json.getLong("productId");
        Integer quantity = json.getInt("quantity");
        inventoryService.addStock(new InventoryRequest(productId, quantity));
    }
}