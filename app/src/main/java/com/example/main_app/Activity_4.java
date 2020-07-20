package com.example.main_app;

import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Objects;

public class Activity_4 extends AppCompatActivity implements View.OnClickListener {

    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;

    ImageView drawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);

        drawable = findViewById(R.id.Act4_imageView);

        button1 = findViewById(R.id.Act4_button1);
        button2 = findViewById(R.id.Act4_button2);
        button3 = findViewById(R.id.Act4_button3);
        button4 = findViewById(R.id.Act4_button4);
        button5 = findViewById(R.id.Act4_button5);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Animation anim;

        switch (v.getId()){
            case R.id.Act4_button1:
                anim = AnimationUtils.loadAnimation(this,R.anim.myalpha);
                drawable.startAnimation(anim);
                break;
            case R.id.Act4_button2:
                anim = AnimationUtils.loadAnimation(this,R.anim.myscale);
                drawable.startAnimation(anim);
                break;
            case R.id.Act4_button3:
                anim = AnimationUtils.loadAnimation(this,R.anim.mytrans);
                drawable.startAnimation(anim);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        Animation anim = AnimationUtils.loadAnimation(Activity_4.this,R.anim.mytrans1);
                        drawable.startAnimation(anim);
                    }
                }, 1000);
                handler.postDelayed(new Runnable() {
                    public void run() {
                        Animation anim = AnimationUtils.loadAnimation(Activity_4.this,R.anim.mytrans2);
                        drawable.startAnimation(anim);
                    }
                }, 2000);
                handler.postDelayed(new Runnable() {
                    public void run() {
                        Animation anim = AnimationUtils.loadAnimation(Activity_4.this,R.anim.mytrans3);
                        drawable.startAnimation(anim);
                    }
                }, 3000);
                break;
            case R.id.Act4_button4:
                anim = AnimationUtils.loadAnimation(this,R.anim.myrotate);
                drawable.startAnimation(anim);
                break;
            case R.id.Act4_button5:
                anim = AnimationUtils.loadAnimation(this,R.anim.mycombo);
                drawable.startAnimation(anim);
                break;
        }
    }
}