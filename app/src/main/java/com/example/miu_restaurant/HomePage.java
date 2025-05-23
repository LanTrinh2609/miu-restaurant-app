package com.example.miu_restaurant;

import static com.example.miu_restaurant.R.id.tabhost;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {
    Button btnOpenCart;
    int image[] = {R.drawable.banhcanh, R.drawable.chaoga, R.drawable.myquang, R.drawable.phobo, R.drawable.phoga};
    String name[] = {"Bánh canh", "Cháo gà", "Mỳ quảng", "Phở bò", "Phở gà"};
    String price[] = {"30,000đ", "35,000đ", "40,000đ", "50,000đ", "55,000đ"};
    //khai báo listView
    ArrayList<Food> mylist;
    MyArrayAdapter myArrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        ListView lv = findViewById(R.id.lvfood);
        TabHost tabHost = findViewById(R.id.tabhost);
        // Khởi tạo TabHost

        tabHost.setup();  // Setup TabHost

        // Thêm Tab 1
        TabHost.TabSpec tabSpec1 = tabHost.newTabSpec("Tab 1");
        tabSpec1.setContent(R.id.tab1);
        tabSpec1.setIndicator("",getResources().getDrawable(R.drawable.add));  // Tiêu đề tab 1
        tabHost.addTab(tabSpec1);

        // Thêm Tab 2
        TabHost.TabSpec tabSpec2 = tabHost.newTabSpec("Tab 2");
        tabSpec2.setContent(R.id.tab2);
        tabSpec2.setIndicator("",getResources().getDrawable(R.drawable.setting));  // Tiêu đề tab 2
        tabHost.addTab(tabSpec2);

        btnOpenCart = findViewById(R.id.btnOpenCart);
        btnOpenCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, Cart.class);
                startActivity(intent);
            }
        });

        // tạo mới mảng
        mylist = new ArrayList<>();
        for (int i = 0;i<name.length;i++)
        {
            mylist.add(new Food(image[i], name[i] , price[i]));
        }
        myArrayAdapter = new MyArrayAdapter(HomePage.this, R.layout.itemfood, mylist);
        lv.setAdapter(myArrayAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Lấy món ăn được chọn
                Food selectedFood = mylist.get(position);

                // Gửi dữ liệu sang Activity mới
                Intent intent = new Intent(HomePage.this, FoodDetailActivity.class);
                intent.putExtra("name", selectedFood.getName());
                intent.putExtra("price", selectedFood.getPrice());
                intent.putExtra("image", selectedFood.getImage());
                startActivity(intent);
            }
        });
        Button btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(v -> {
            // Chuyển về màn hình đăng nhập và xóa ngăn xếp các activity trước đó
            Intent intent = new Intent(HomePage.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            Toast.makeText(this, "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
        });

    }
}