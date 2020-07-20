package com.example.main_app;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class Activity_29_my_service extends Service {

    int some_value;
    PendingIntent pi;

    public void onCreate() {
        Toast.makeText(this, "Service Created", Toast.LENGTH_LONG).show();
        super.onCreate();
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        some_value = intent.getIntExtra("Some value",1);
        pi = intent.getParcelableExtra("Pending");
        new Thread(new Runnable() {
            public void run() {
                try {
                    pi.send(Activity_29.STATUS_START);
                    TimeUnit.SECONDS.sleep(some_value);
                    pi.send(Activity_29_my_service.this, Activity_29.STATUS_FINISH, new Intent().putExtra("result", 100));
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