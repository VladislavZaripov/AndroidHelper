package com.example.main_app;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Activity_9 extends AppCompatActivity {

    String [] name = {"Максим","Сергей","Руслан","Наталья","Иван","Мария","Светлана","Григорий"};
    String [] position = {"Директор","Программист","Бухгалтер","Охранник","Директор","Программист","Бухгалтер","Охранник"};
    int [] salary = {90000,60000,40000,20000,90000,60000,40000,20000};
    int [] colors = new int [2];
    LinearLayout linLayout;
    LayoutInflater ltInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_9);

        colors[0] = Color.parseColor("#559966CC");
        colors[1] = Color.parseColor("#55336699");
        linLayout = findViewById(R.id.linLayout);
        ltInflater = getLayoutInflater();

        for (int i = 0; i < name.length; i++){
            View item = ltInflater.inflate(R.layout.activity_9_item, linLayout,false);

            TextView tvName = item.findViewById(R.id.Act9item_tvName);
            TextView tvPosition = item.findViewById(R.id.Act9item_tvPosition);
            TextView tvSalary = item.findViewById(R.id.Act9item_tvSalary);

            tvName.setText(name[i]);
            tvPosition.setText("Должность: " + position[i]);
            tvSalary.setText("Зарплата: " + salary[i]);
            item.setBackgroundColor(colors[i % 2]);

            linLayout.addView(item);
        }
    }
}