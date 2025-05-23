package com.example.miu_restaurant;

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

public class MainActivity extends AppCompatActivity {
    EditText edtaddr, edtpass;
    Button btnlogin, btnregis;
    SQLiteDatabase mydatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        edtaddr = findViewById(R.id.edtaddr);
        edtpass = findViewById(R.id.edtpass);
        btnlogin = findViewById(R.id.btnlogin);
        btnregis = findViewById(R.id.btnregis);

        // Mở cơ sở dữ liệu
        mydatabase = openOrCreateDatabase("qlcuahang.db", MODE_PRIVATE, null);
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS tbregister(email TEXT PRIMARY KEY, password TEXT)");

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtaddr.getText().toString().trim();
                String password = edtpass.getText().toString().trim();

                Log.d("LOGIN_INPUT", "Email: " + email + ", Password: " + password);

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Email and password must not be empty.", Toast.LENGTH_SHORT).show();
                    return;
                }

                Cursor cursor = mydatabase.rawQuery("SELECT * FROM tbregister WHERE email = ? AND password = ?", new String[]{email, password});

                if (cursor.getCount() > 0) {
                    Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, HomePage.class));
                } else {
                    Toast.makeText(MainActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                }

                // DEBUG: Log toàn bộ dữ liệu
                Cursor c = mydatabase.rawQuery("SELECT * FROM tbregister", null);
                while (c.moveToNext()) {
                    Log.d("LOGIN_DEBUG", "Email: " + c.getString(0) + ", Password: " + c.getString(1));
                }
                c.close();

                cursor.close();
            }
        });

        btnregis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Register.class));
            }
        });
    }
}
