package com.example.miu_restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FoodDetailActivity extends AppCompatActivity {

    TextView txtName, txtPrice;
    ImageView imgFood;
    EditText edtQuantity;
    Button btnAddToCart;

    String name, price;
    int image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        txtName = findViewById(R.id.txtDetailName);
        txtPrice = findViewById(R.id.txtDetailPrice);
        imgFood = findViewById(R.id.imgDetailFood);
        edtQuantity = findViewById(R.id.edtQuantity);
        btnAddToCart = findViewById(R.id.btnAddToCart);

        // Nhận dữ liệu từ Intent
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        price = intent.getStringExtra("price");
        image = intent.getIntExtra("image", R.drawable.banhcanh);

        // Hiển thị thông tin món ăn
        txtName.setText(name);
        txtPrice.setText(price);
        imgFood.setImageResource(image);

        btnAddToCart.setOnClickListener(v -> {
            String quantityStr = edtQuantity.getText().toString().trim();
            if (quantityStr.isEmpty() || Integer.parseInt(quantityStr) <= 0) {
                Toast.makeText(FoodDetailActivity.this, "Vui lòng nhập số lượng hợp lệ", Toast.LENGTH_SHORT).show();
                return;
            }

            int quantity = Integer.parseInt(quantityStr);

            CartManager.getInstance().addToCart(new Food(image, name, price), quantity);
            Toast.makeText(FoodDetailActivity.this, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
            finish();

        });
    }
}
