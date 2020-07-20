package com.example.main_app;

import android.app.PendingIntent;
import android.content.*;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Activity_29 extends AppCompatActivity implements View.OnClickListener {

    Button buttonStart, buttonStop;
    TextView textView1;

    final int TASK_CODE = 1;
    final int TIME = 5;
    public final static int STATUS_START = 100;
    public final static int STATUS_FINISH = 200;

    PendingIntent pi;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_29);
        textView1 = findViewById(R.id.Act29_textView1);
        buttonStart = findViewById(R.id.Act29_btnStart);
        buttonStop = findViewById(R.id.Act29_btnStop);
        buttonStart.setOnClickListener(this);
        buttonStop.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Act29_btnStart:
                pi = createPendingResult(TASK_CODE, new Intent(), 0);
                startService(new Intent(this, Activity_29_my_service.class).
                        putExtra("Some value",TIME).putExtra("Pending",pi));
                break;
            case R.id.Act29_btnStop:
                stopService(new Intent(this, Activity_29_my_service.class));
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            switch (resultCode){
                case STATUS_START:
                    textView1.setText("PendingIntent: " + "Task start, working time = " + TIME);
                    break;
                case STATUS_FINISH:
                    textView1.setText(textView1.getText() + "\n" + "PendingIntent: " + "Task finish, result = " + data.getIntExtra("result", 0));
                    break;
            }
    }

}