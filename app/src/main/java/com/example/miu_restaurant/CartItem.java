package com.example.miu_restaurant;

import java.text.NumberFormat;
import java.util.Locale;

public class CartItem {
    private Food food;
    private int quantity;

    public void setFood(Food food) {
        this.food = food;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public CartItem(Food food, int quantity) {
        this.food = food;
        this.quantity = quantity;
    }

    public Food getFood() {
        return food;
    }

    public int getQuantity() {
        return quantity;
    }

    // Tính tổng tiền (số nguyên)
    public int getTotalPrice() {
        try {
            // Loại bỏ dấu phẩy và chữ "đ" trong chuỗi giá
            String priceString = food.getPrice().replace(",", "").replace("đ", "").trim();
            return Integer.parseInt(priceString) * quantity;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    // Trả về tổng tiền định dạng "xx,xxxđ"
    public String getFormattedTotalPrice() {
        int total = getTotalPrice();
        NumberFormat formatter = NumberFormat.getNumberInstance(new Locale("vi", "VN"));
        return formatter.format(total) + "đ";
    }
}
