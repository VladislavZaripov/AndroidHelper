package com.example.main_app;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Activity_38 extends Activity implements SensorEventListener {

    SensorManager mSensorManager;
    Sensor mAccelerometerSensor;
    TextView mXValueText;
    TextView mYValueText;
    TextView mZValueText;

    ImageView imageView;
    int width,height;
    TextView mXImgText;
    TextView mYImgText;

    TextView mXText;
    TextView mYText;

    LinearLayout layout;
    TextView mXLText;
    TextView mYLText;
    TextView mXImg;
    TextView mYImg;
    int mXL, mYL;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_38);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);

        if (sensors.size() > 0) {
            for (Sensor sensor : sensors) {
                switch (sensor.getType()) {
                    case Sensor.TYPE_ACCELEROMETER:
                        if (mAccelerometerSensor == null)
                            mAccelerometerSensor = sensor;
                        break;
                    default:
                        break;
                }
            }
        }

        mXValueText = findViewById(R.id.Act38_value_x);
        mYValueText = findViewById(R.id.Act38_value_y);
        mZValueText = findViewById(R.id.Act38_value_z);

        imageView = findViewById(R.id.Act38_imageView);
        mXImg = findViewById(R.id.Act38_xIMG);
        mYImg = findViewById(R.id.Act38_yIMG);

        ViewTreeObserver vto1 = imageView.getViewTreeObserver();
        vto1.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                imageView.getViewTreeObserver().removeOnPreDrawListener(this);
                width = imageView.getWidth();
                height = imageView.getHeight();
                mXImg.setText(Integer.toString(width));
                mYImg.setText(Integer.toString(height));
                return false;
            }
        });

        mXImgText = findViewById(R.id.Act38_img_x);
        mYImgText = findViewById(R.id.Act38_img_y);

        mXText = findViewById(R.id.Act38_x);
        mYText = findViewById(R.id.Act38_y);
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics metricsB = new DisplayMetrics();
        display.getMetrics(metricsB);
        mXText.setText(""+metricsB.widthPixels);
        mYText.setText(""+metricsB.heightPixels);

        layout = findViewById(R.id.Act38_LL);
        mXLText = findViewById(R.id.Act38_xL);
        mYLText = findViewById(R.id.Act38_yL);

        ViewTreeObserver vto2 = layout.getViewTreeObserver();
        vto2.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                layout.getViewTreeObserver().removeOnPreDrawListener(this);
                mXL = layout.getWidth();
                mYL = layout.getHeight();
                mXLText.setText(Integer.toString(mXL));
                mYLText.setText(Integer.toString(mYL));
            return false;
            }
        });
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        switch (event.sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER: {
                mXValueText.setText(String.format("%1.3f", event.values[0]));
                mYValueText.setText(String.format("%1.3f", event.values[1]));
                mZValueText.setText(String.format("%1.3f", event.values[2]));

                float X = imageView.getX()-event.values[0]*3;
                if (X>mXL-width){
                    imageView.setX(mXL-width);
                    mXImgText.setText(Integer.toString(mXL-width));
                }
                else if (X<0){
                    imageView.setX(0);
                    mXImgText.setText(Integer.toString(0));
                }
                else {
                    imageView.setX(X);
                    mXImgText.setText(Integer.toString((int)X));
                }

                float Y = imageView.getY()+event.values[1]*3;
                if (Y>mYL-height){
                    imageView.setY(mYL-height);
                    mYImgText.setText(Integer.toString(mYL-height));
                }
                else if (Y<0){
                    imageView.setY(0);
                    mYImgText.setText(Integer.toString(0));
                }
                else {
                    imageView.setY(Y);
                    mYImgText.setText(Integer.toString((int)Y));
                }
                 break;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometerSensor, SensorManager.SENSOR_DELAY_GAME);
    }
    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(this);
        super.onPause();
    }
}