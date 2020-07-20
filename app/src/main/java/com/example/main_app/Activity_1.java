package com.example.main_app;

import android.graphics.Color;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Objects;

public class Activity_1 extends AppCompatActivity {

    TextView textView;
    ImageView imageView;

    final int MENU_COLOR_RED = 1;  final int MENU_COLOR_GREEN = 2;
    final int MENU_SIZE_22 = 3;    final int MENU_SIZE_40 = 4;
    final int MENU_PICTURE1 = 5;   final int MENU_PICTURE2 = 6;
    final int MENU_PICTURE3 = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);

        textView = findViewById(R.id.Act1_textView);
        imageView = findViewById(R.id.Act1_imageView);
        registerForContextMenu(textView);
        registerForContextMenu(imageView);

    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        switch (v.getId()){
            case R.id.Act1_textView:
                menu.add(0,MENU_COLOR_RED,0,"Red");
                menu.add(0,MENU_COLOR_GREEN,0,"Green");
                menu.add(0,MENU_SIZE_22,0,"22");
                menu.add(0,MENU_SIZE_40,0,"40");
                break;
            case R.id.Act1_imageView:
                menu.add(1,MENU_PICTURE1,0,"Picture1");
                menu.add(1,MENU_PICTURE2,0,"Picture2");
                menu.add(1,MENU_PICTURE3,0,"Picture3");
                break;
        }
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case MENU_COLOR_RED:
                textView.setTextColor(Color.RED);
                break;
            case MENU_COLOR_GREEN:
                textView.setTextColor(Color.GREEN);
                break;
            case MENU_SIZE_22:
                textView.setTextSize(22);
                break;
            case MENU_SIZE_40:
                textView.setTextSize(40);
                break;
            case MENU_PICTURE1:
                imageView.setImageResource(R.mipmap.ic_launcher);
                break;
            case MENU_PICTURE2:
                imageView.setImageResource(android.R.mipmap.sym_def_app_icon);
                break;
            case MENU_PICTURE3:
                imageView.setImageResource(R.drawable.baseline_android_black_48);
                break;
        }
        return super.onContextItemSelected(item);
    }
}