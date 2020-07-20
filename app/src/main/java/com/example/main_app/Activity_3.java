package com.example.main_app;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Objects;

public class Activity_3 extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener{

    SeekBar seekBar;
    Button button1, button2;
    ImageView imageView1, imageView2;
    LinearLayout.LayoutParams lParam1, lParam2, lParam3, lParam4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        seekBar = findViewById(R.id.Act3_seekBar);
        seekBar.setOnSeekBarChangeListener(this);
        button1 = findViewById(R.id.Act3_button1);
        button2 = findViewById(R.id.Act3_button2);
        imageView1 = findViewById(R.id.Act3_imageView1);
        imageView2 = findViewById(R.id.Act3_imageView2);
        lParam1 = (LinearLayout.LayoutParams) button1.getLayoutParams();
        lParam2 = (LinearLayout.LayoutParams) button2.getLayoutParams();
        lParam3 = (LinearLayout.LayoutParams) imageView1.getLayoutParams();
        lParam4 = (LinearLayout.LayoutParams) imageView2.getLayoutParams();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        lParam1.weight = progress;
        lParam2.weight = seekBar.getMax() - progress;
        lParam3.height = progress *4;
        lParam4.height = (seekBar.getMax() - progress)*4;
        button1.setText(String.valueOf(progress));
        button2.setText(String.valueOf(seekBar.getMax() - progress));
        imageView1.setLayoutParams(lParam3);
        imageView2.setLayoutParams(lParam4);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }
}

