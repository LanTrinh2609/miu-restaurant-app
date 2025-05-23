package com.example.miu_restaurant;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {
    EditText edtregemail, edtregpass, edtregrepass;
    Button btncreate;
    SQLiteDatabase mydatabase;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Register.this, MainActivity.class);
        startActivity(intent);
        finish(); // đóng Register để không quay lại nữa
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        edtregemail = findViewById(R.id.edtregemail);
        edtregpass = findViewById(R.id.edtregpass);
        edtregrepass = findViewById(R.id.edtregrepass);
        btncreate = findViewById(R.id.btncreate);

        // Mở hoặc tạo CSDL
        mydatabase = openOrCreateDatabase("qlcuahang.db", MODE_PRIVATE, null);

        // Tạo bảng nếu chưa tồn tại
        try {
            String sql = "CREATE TABLE IF NOT EXISTS tbregister(email TEXT PRIMARY KEY, password TEXT)";
            mydatabase.execSQL(sql);
        } catch (Exception e) {
            Log.e("DB_ERROR", "Error creating table: " + e.getMessage());
        }

        btncreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtregemail.getText().toString().trim();
                String password = edtregpass.getText().toString().trim();
                String repassword = edtregrepass.getText().toString().trim();

                // Kiểm tra dữ liệu đầu vào
                if (email.isEmpty()) {
                    Toast.makeText(Register.this, "Email must not be empty.", Toast.LENGTH_LONG).show();
                    edtregemail.requestFocus();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(Register.this, "Password must be more than 6 characters.", Toast.LENGTH_LONG).show();
                    edtregpass.requestFocus();
                    return;
                }

                if (!repassword.equals(password)) {
                    Toast.makeText(Register.this, "Passwords do not match.", Toast.LENGTH_LONG).show();
                    edtregrepass.requestFocus();
                    return;
                }

                // Kiểm tra email đã tồn tại chưa
                Cursor check = mydatabase.rawQuery("SELECT * FROM tbregister WHERE email = ?", new String[]{email});
                if (check.moveToFirst()) {
                    Toast.makeText(Register.this, "Email already exists", Toast.LENGTH_LONG).show();
                    check.close();
                    return;
                }
                check.close();

                // Thêm dữ liệu mới
                ContentValues values = new ContentValues();
                values.put("email", email);
                values.put("password", password);

                long result = mydatabase.insert("tbregister", null, values);
                if (result == -1) {
                    Toast.makeText(Register.this, "Registration failed", Toast.LENGTH_LONG).show();
                    Log.e("INSERT_FAIL", "Failed to insert user: " + values.toString());
                } else {
                    Toast.makeText(Register.this, "Registration successful", Toast.LENGTH_LONG).show();
                    Log.d("REGISTER_SUCCESS", "User registered: " + email);

                    // Debug: In toàn bộ dữ liệu
                    Cursor c = mydatabase.rawQuery("SELECT * FROM tbregister", null);
                    while (c.moveToNext()) {
                        Log.d("DATA_DEBUG", "Email: " + c.getString(0) + ", Password: " + c.getString(1));
                    }
                    c.close();

                    // Chuyển sang trang HomePage
                    Intent intent = new Intent(Register.this, HomePage.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
