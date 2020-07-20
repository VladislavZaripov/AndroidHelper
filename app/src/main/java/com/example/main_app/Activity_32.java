package com.example.main_app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

public class Activity_32 extends Activity implements OnTouchListener {

    ConstraintLayout llMain;
    TextView tv;

    StringBuilder sb = new StringBuilder();
    int upPI = 0;
    int downPI = 0;
    boolean inTouch = false;
    String result = "";

    ArrayList <ImageView> list;
    ImageView imageView;
    float width;
    float height;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_32);
        llMain = findViewById(R.id.Act32_llMain);
        llMain.setOnTouchListener(this);

        tv = new TextView(this);
        tv.setTextSize(16);
        llMain.addView(tv);

        list = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.apps_voodoo);
            imageView.setX(800);
            imageView.setY(100);
            llMain.addView(imageView);
            list.add(imageView);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View view, MotionEvent event) {

        width = imageView.getWidth();
        height = imageView.getHeight();

        int pointerIndex = event.getActionIndex();  // индекс касания
        int pointerCount = event.getPointerCount(); // число касаний

        switch (event.getActionMasked()) {

            case MotionEvent.ACTION_DOWN: // первое касание
                inTouch = true;

            case MotionEvent.ACTION_POINTER_DOWN: // последующие касания
                downPI = pointerIndex;
                try {
                    list.get(event.getPointerId(pointerIndex)).setX(event.getX(event.getPointerId(pointerIndex)) - width/2);
                    list.get(event.getPointerId(pointerIndex)).setY(event.getY(event.getPointerId(pointerIndex)) - height/2);
                }
                catch (Exception e) {e.printStackTrace();}
                break;

            case MotionEvent.ACTION_UP: // прерывание последнего касания
                inTouch = false;
                sb.setLength(0);

            case MotionEvent.ACTION_POINTER_UP: // прерывания касаний
                upPI = pointerIndex;
                try {
                    list.get(event.getPointerId(pointerIndex)).setX(event.getX(event.getPointerId(pointerIndex)) - width/2);
                    list.get(event.getPointerId(pointerIndex)).setY(event.getY(event.getPointerId(pointerIndex)) - height/2);
                }
                catch (Exception e) {e.printStackTrace();}
                break;

            case MotionEvent.ACTION_MOVE: // движение
                sb.setLength(0);

                for (int i = 0; i < 10; i++) {
                    sb.append("Index = ").append(i);
                    if (i < pointerCount) {
                        sb.append(", ID = ").append(event.getPointerId(i));
                        sb.append(", X = ").append((int) event.getX(i));
                        sb.append(", Y = ").append((int) event.getY(i));
                        try {
                            list.get(event.getPointerId(i)).setX(event.getX(event.getPointerId(i)) - width/2);
                            list.get(event.getPointerId(i)).setY(event.getY(event.getPointerId(i)) - height/2);
                        }
                        catch (Exception e) {e.printStackTrace();}

                    } else {
                        sb.append(", ID = ");
                        sb.append(", X = ");
                        sb.append(", Y = ");
                    }
                    sb.append("\r\n");
                }
                break;
        }
        result = "down: " + downPI + "\n" + "up: " + upPI + "\n";

        if (inTouch) {
            result += "pointerCount = " + pointerCount + "\n" + sb.toString();
        }
        tv.setText(result);
        return true;
    }
}