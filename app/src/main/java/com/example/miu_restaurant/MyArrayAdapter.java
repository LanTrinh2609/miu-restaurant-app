package com.example.miu_restaurant;

import android.app.Activity;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyArrayAdapter extends ArrayAdapter<Food> {
    Activity context;
    int idlayout;
    ArrayList<Food> mylist;
    // tạo constructor để MainActivity gọi đến và truyền tham số vào

    public MyArrayAdapter( Activity context, int idlayout, ArrayList<Food> mylist) {
        super(context, idlayout, mylist);
        this.context = context;
        this.idlayout = idlayout;
        this.mylist = mylist;
    }
    // gọi hàm getView để tiến hành sắp xếp dữ liệu

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Tạo đế chứa layout
        LayoutInflater myinflater = context.getLayoutInflater();
        // đặt idlayout lên đế để tạo thành View
        convertView = myinflater.inflate(idlayout, null);
        //lấy 1 phần tử trong mảng
        Food myfood = mylist.get(position);
        // khai báo, tham chiếu id và hiển thi ảnh lên image view
        ImageView imgfood = convertView.findViewById(R.id.imgFood);
        imgfood.setImageResource(myfood.getImage());
        // khai báo, tham chếu id và hiển thị tên món ăn lên textview
        TextView txtfood = convertView.findViewById(R.id.txtName);
        txtfood.setText(myfood.getName());
        // khai báo, tham chếu id và hiển thị giá lên textview
        TextView txtprice = convertView.findViewById(R.id.txtPrice);
        txtprice.setText(myfood.getPrice());
        return convertView;
    }
}
