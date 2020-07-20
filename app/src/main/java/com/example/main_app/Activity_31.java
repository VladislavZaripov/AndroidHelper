package com.example.main_app;

import android.content.*;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Activity_31 extends AppCompatActivity implements View.OnClickListener {

    Button buttonStart, buttonStop, buttonStartBind, buttonStopBind, buttonStartBindTask;

    final int TIME = 5;

    ServiceConnection sConn;
    boolean bound = false;

    Activity_31_my_service myService;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_31);
        buttonStart = findViewById(R.id.Act31_btnStart);
        buttonStop = findViewById(R.id.Act31_btnStop);
        buttonStartBind = findViewById(R.id.Act31_btnStartBind);
        buttonStopBind = findViewById(R.id.Act31_btnStopBind);
        buttonStartBindTask = findViewById(R.id.Act31_btnStartBindTask);
        buttonStart.setOnClickListener(this);
        buttonStop.setOnClickListener(this);
        buttonStartBind.setOnClickListener(this);
        buttonStopBind.setOnClickListener(this);
        buttonStartBindTask.setOnClickListener(this);

        sConn = new ServiceConnection() {
            public void onServiceConnected(ComponentName name, IBinder binder) {
                myService = ((Activity_31_my_service.MyBinder) binder).getService();
                bound = true;
            }
            public void onServiceDisconnected(ComponentName name) {
                bound = false;
            }
        };
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Act31_btnStart:
                startService(new Intent(this, Activity_31_my_service.class).putExtra("Some value",TIME));
                break;
            case R.id.Act31_btnStop:
                stopService(new Intent(this, Activity_31_my_service.class));
                break;
            case R.id.Act31_btnStartBind:
                bindService(new Intent(this, Activity_31_my_service.class), sConn, BIND_AUTO_CREATE); // BIND_AUTO_CREATE - create service, 0 - do not create
                break;
            case R.id.Act31_btnStopBind:
                if (!bound) return;
                unbindService(sConn);
                bound = false;
                break;
            case R.id.Act31_btnStartBindTask:
                try {
                    myService.Task();
                }
                catch (Exception e){
                    Toast.makeText(this,"Binder no connect",Toast.LENGTH_LONG).show();
                }

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (!bound) return;
        unbindService(sConn);
        bound = false;
    }
}