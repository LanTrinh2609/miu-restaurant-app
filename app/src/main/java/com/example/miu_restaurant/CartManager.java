package com.example.miu_restaurant;

import java.util.ArrayList;

public class CartManager {
    private static CartManager instance;
    private ArrayList<CartItem> cartList;

    private CartManager() {
        cartList = new ArrayList<>();
    }

    public static CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    public void addToCart(Food food, int quantity) {
        for (CartItem item : cartList) {
            if (item.getFood().getName().equals(food.getName())) {
                // Cộng dồn số lượng
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        cartList.add(new CartItem(food, quantity));
    }


    public ArrayList<CartItem> getCartItems() {
        return cartList;
    }

    public int getTotalAmount() {
        int total = 0;
        for (CartItem item : cartList) {
            total += item.getTotalPrice();
        }
        return total;
    }

    public void clearCart() {
        cartList.clear();
    }
}
