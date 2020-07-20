package com.example.main_app;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class Activity_31_my_service extends Service {

    int some_value;
    MyBinder binder = new MyBinder();

    public void onCreate() {
        Toast.makeText(this, "Service Created", Toast.LENGTH_LONG).show();
        super.onCreate();
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        some_value = intent.getIntExtra("Some value",1);
        new Thread(new Runnable() {
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(some_value);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                stopSelf();
            }
        }).start();
        return  START_NOT_STICKY;
        //  START_NOT_STICKY – сервис не будет перезапущен после того, как был убит системой
        //  START_STICKY – сервис будет перезапущен после того, как был убит системой
        //  START_REDELIVER_INTENT – сервис будет перезапущен после того, как был убит системой.
        //  Кроме этого, сервис снова получит все вызовы startService, которые не были завершены методом stopSelf(startId).
    }

    public IBinder onBind(Intent intent) {
        Toast.makeText(this, "onBind", Toast.LENGTH_LONG).show();
        return binder;
    }

    public void onRebind(Intent intent) {
        Toast.makeText(this, "onRebind", Toast.LENGTH_LONG).show();
        super.onRebind(intent);
    }

    public boolean onUnbind(Intent intent) {
        Toast.makeText(this, "onUnbind", Toast.LENGTH_LONG).show();
        return super.onUnbind(intent); // true for reconnection, default false - do not connect again after unbind
    }

    public void onDestroy() {
        Toast.makeText(this, "Service Stopped", Toast.LENGTH_LONG).show();
        super.onDestroy();
    }

    class MyBinder extends Binder {
        Activity_31_my_service getService() {
            return Activity_31_my_service.this;
        }
    }

    public void Task(){
        Toast.makeText(this, "new Task running", Toast.LENGTH_LONG).show();
    }
}