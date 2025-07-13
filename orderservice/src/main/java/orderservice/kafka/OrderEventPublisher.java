package orderservice.kafka;

import org.json.JSONObject;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderEventPublisher {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public OrderEventPublisher(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishOrderPlaced(Long productId, Integer quantity) {
        JSONObject json = new JSONObject();
        json.put("productId", productId);
        json.put("quantity", quantity);
        kafkaTemplate.send("order.placed", json.toString());
    }

    public void publishOrderCancelled(Long productId, Integer quantity) {
        JSONObject json = new JSONObject();
        json.put("productId", productId);
        json.put("quantity", quantity);
        kafkaTemplate.send("order.cancelled", json.toString());
    }
}