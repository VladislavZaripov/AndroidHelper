package com.example.main_app;

import android.content.*;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Activity_30 extends AppCompatActivity implements View.OnClickListener {

    Button buttonStart, buttonStop;
    TextView textView1;

    final int TIME = 5;
    public final static int STATUS_START = 100;
    public final static int STATUS_FINISH = 200;

    BroadcastReceiver br;
    public final static String BROADCAST_FILTER = "BroadcastReceiver_Filter";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_30);
        textView1 = findViewById(R.id.Act30_textView1);
        buttonStart = findViewById(R.id.Act30_btnStart);
        buttonStop = findViewById(R.id.Act30_btnStop);
        buttonStart.setOnClickListener(this);
        buttonStop.setOnClickListener(this);

        br = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (intent.getIntExtra("status", 0)){
                    case STATUS_START:
                        textView1.setText("BroadcastReceiver: " + "Task start, working time = " + TIME);
                        break;
                    case STATUS_FINISH:
                        textView1.setText(textView1.getText() + "\n" + "BroadcastReceiver: " + "Task finish, result = " + intent.getIntExtra("result", 0));
                        break;
                }
            }
        };
        IntentFilter intFilter1 = new IntentFilter(BROADCAST_FILTER);
        registerReceiver(br, intFilter1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Act30_btnStart:
                startService(new Intent(this, Activity_30_my_service.class).putExtra("Some value",TIME));
                break;
            case R.id.Act30_btnStop:
                stopService(new Intent(this, Activity_30_my_service.class));
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(br);
    }
}