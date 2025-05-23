package com.example.miu_restaurant;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.text.NumberFormat;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;

public class Cart extends AppCompatActivity {
    EditText edtTableName;
    ListView listCart;
    TextView txtTotal;
    Button btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        edtTableName = findViewById(R.id.edtTableName);
        listCart = findViewById(R.id.listCart);
        txtTotal = findViewById(R.id.txtTotal);
        btnConfirm = findViewById(R.id.btnConfirm);

        // Hiển thị danh sách giỏ hàng
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1);
        for (CartItem item : CartManager.getInstance().getCartItems()) {
            adapter.add(item.getQuantity() + " x " + item.getFood().getName() + " = " + item.getTotalPrice() + "đ");
        }
        listCart.setAdapter(adapter);

        // **Hiển thị tổng tiền đúng định dạng**
        int totalAmount = CartManager.getInstance().getTotalAmount();
        NumberFormat formatter = NumberFormat.getNumberInstance(new Locale("vi", "VN"));
        txtTotal.setText("Tổng tiền: " + formatter.format(totalAmount) + "đ");


        btnConfirm.setOnClickListener(v -> {
            String table = edtTableName.getText().toString().trim();
            if (table.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập tên bàn", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(this, "Đã xác nhận đơn cho bàn số " + table, Toast.LENGTH_LONG).show();
            CartManager.getInstance().clearCart();
            finish();
        });
    }
}
