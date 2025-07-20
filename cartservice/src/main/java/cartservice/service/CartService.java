package cartservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cartservice.dto.CartItemRequest;
import cartservice.dto.CartResponse;
import cartservice.entity.Cart;
import cartservice.entity.CartItem;
import cartservice.repository.CartRepository;

@Service
public class CartService {
    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public CartResponse getCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setUserId(userId);
            return cartRepository.save(newCart);
        });

        CartResponse response = new CartResponse();
        response.setUserId(cart.getUserId());

        List<CartResponse.Item> items = new ArrayList<>();
        for (CartItem item : cart.getItems()) {
            CartResponse.Item i = new CartResponse.Item();
            i.setProductId(item.getProductId());
            i.setQuantity(item.getQuantity());
            items.add(i);
        }

        response.setItems(items);
        return response;
    }

    public void addItem(Long userId, CartItemRequest request) {
        Cart cart = cartRepository.findByUserId(userId).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setUserId(userId);
            return cartRepository.save(newCart);
        });

        for (CartItem item : cart.getItems()) {
            if (item.getProductId().equals(request.getProductId())) {
                item.setQuantity(item.getQuantity() + request.getQuantity());
                cartRepository.save(cart);
                return;
            }
        }

        CartItem item = new CartItem();
        item.setProductId(request.getProductId());
        item.setQuantity(request.getQuantity());
        item.setCart(cart);
        cart.getItems().add(item);
        cartRepository.save(cart);
    }

    public void removeItem(Long userId, Long productId) {
        Cart cart = cartRepository.findByUserId(userId).orElseThrow();
        cart.getItems().removeIf(item -> item.getProductId().equals(productId));
        cartRepository.save(cart);
    }

    public void clearCart(Long userId) {
        cartRepository.deleteByUserId(userId);
    }
}