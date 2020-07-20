package com.example.main_app;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class Activity_30_my_service extends Service {

    int some_value;

    public void onCreate() {
        Toast.makeText(this, "Service Created", Toast.LENGTH_LONG).show();
        super.onCreate();
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        some_value = intent.getIntExtra("Some value",1);
        new Thread(new Runnable() {
            public void run() {
                try {
                    sendBroadcast(new Intent(Activity_30.BROADCAST_FILTER).putExtra("status",Activity_30.STATUS_START));
                    TimeUnit.SECONDS.sleep(some_value);
                    sendBroadcast(new Intent(Activity_30.BROADCAST_FILTER).putExtra("status",Activity_30.STATUS_FINISH).putExtra("result", 100));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                stopSelf();
            }
        }).start();
        return  START_REDELIVER_INTENT;
        //  START_NOT_STICKY – сервис не будет перезапущен после того, как был убит системой
        //  START_STICKY – сервис будет перезапущен после того, как был убит системой
        //  START_REDELIVER_INTENT – сервис будет перезапущен после того, как был убит системой.
        //  Кроме этого, сервис снова получит все вызовы startService, которые не были завершены методом stopSelf(startId).
    }

    public IBinder onBind(Intent intent) {
        return new Binder();
    }

    public void onDestroy() {
        Toast.makeText(this, "Service Stopped", Toast.LENGTH_LONG).show();
        super.onDestroy();
    }
}