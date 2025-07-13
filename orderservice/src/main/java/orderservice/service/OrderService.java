package orderservice.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import orderservice.dto.OrderRequest;
import orderservice.dto.OrderResponse;
import orderservice.entity.Order;
import orderservice.kafka.OrderEventPublisher;
import orderservice.repository.OrderRepository;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderEventPublisher eventPublisher;

    public OrderService(OrderRepository orderRepository, OrderEventPublisher eventPublisher) {
        this.orderRepository = orderRepository;
        this.eventPublisher = eventPublisher;
    }

    public OrderResponse placeOrder(OrderRequest request) {
        Order order = new Order();
        order.setProductId(request.getProductId());
        order.setQuantity(request.getQuantity());
        order.setStatus("PLACED");
        order.setCreatedAt(LocalDateTime.now());
        Order saved = orderRepository.save(order);

        eventPublisher.publishOrderPlaced(saved.getProductId(), saved.getQuantity());

        return new OrderResponse(saved.getId(), saved.getStatus());
    }

    public OrderResponse cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus("CANCELLED");
        orderRepository.save(order);

        eventPublisher.publishOrderCancelled(order.getProductId(), order.getQuantity());

        return new OrderResponse(order.getId(), order.getStatus());
    }
}