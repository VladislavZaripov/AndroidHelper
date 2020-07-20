package com.example.main_app;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.util.concurrent.TimeUnit;

public class Activity_27 extends AppCompatActivity {

    TextView textView;
    Handler handler;
    Message msg;
    final int sendEmptyMessage = 0;
    final int sendMessage = 1;
    final int sendEmptyMessageDelayed = 3;
    final int sendMessageDelayed = 4;
    final int sendEmptyMessageAtTime = 5;
    final int sendMessageAtTime = 6;
    final int removeMessages = 7;
    final int arg1 = 10;
    final int arg2 = 20;
    Obj obj;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_27);

        textView = findViewById(R.id.Act27_textView);
        obj = new Obj("text");

        handler = new Handler() {
            @SuppressLint("HandlerLeak")
            public void handleMessage(android.os.Message msg) {
                switch (msg.what) {
                    case sendEmptyMessage:
                        textView.setText(textView.getText() + "handler.sendEmptyMessage\n" + "what = " + msg.what + "\n\n");
                        break;
                    case sendMessage:
                        textView.setText(textView.getText() + "handler.sendMessage\n" + "what = " + msg.what + "; arg1 = " + msg.arg1 + "; arg2 = " + msg.arg2 + "; obj = " + msg.obj.toString() + "\n\n");
                        break;
                    case sendEmptyMessageDelayed:
                        textView.setText(textView.getText() + "handler.sendEmptyMessageDelayed\n" + "what = " + msg.what + "\n\n");
                        break;
                    case sendMessageDelayed:
                        textView.setText(textView.getText() + "handler.sendMessageDelayed\n" + "what = " + msg.what + "; arg1 = " + msg.arg1 + "; arg2 = " + msg.arg2 + "; obj = " + msg.obj.toString() + "\n\n");
                        break;
                    case sendEmptyMessageAtTime:
                        textView.setText(textView.getText() + "handler.sendEmptyMessageAtTime\n" + "what = " + msg.what + "\n\n");
                        break;
                    case sendMessageAtTime:
                        textView.setText(textView.getText() + "handler.sendMessageAtTime\n" + "what = " + msg.what + "; arg1 = " + msg.arg1 + "; arg2 = " + msg.arg2 + "; obj = " + msg.obj.toString() + "\n\n");
                        break;
                    case removeMessages:
                        textView.setText(textView.getText() + "handler.removeMessages\n" + "what = " + msg.what + "\n\n");
                        break;
                }
            }
        };
    }

    public void onclick(View v) {

        Thread t = new Thread(new Runnable() {

            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    handler.sendEmptyMessage(sendEmptyMessage);

                    TimeUnit.SECONDS.sleep(2);
                    msg = handler.obtainMessage(sendMessage, arg1, arg2, obj);
                    handler.sendMessage(msg);

                    TimeUnit.SECONDS.sleep(1);
                    handler.sendEmptyMessageDelayed(sendEmptyMessageDelayed, 1000);

                    TimeUnit.SECONDS.sleep(2);
                    msg = handler.obtainMessage(sendMessageDelayed, arg1, arg2, obj);
                    handler.sendMessageDelayed(msg, 1000);

                    TimeUnit.SECONDS.sleep(2);
                    msg = handler.obtainMessage(sendMessageAtTime, arg1, arg2, obj);
                    handler.sendMessageAtTime(msg, 1000);

                    TimeUnit.SECONDS.sleep(2);
                    handler.sendEmptyMessageAtTime(sendEmptyMessageAtTime, 1000);
                    handler.removeMessages(sendEmptyMessageAtTime);

                    TimeUnit.SECONDS.sleep(2);
                    handler.sendEmptyMessageDelayed(removeMessages, 1000);
                    handler.removeMessages(removeMessages);

                    TimeUnit.SECONDS.sleep(2);
                    Runnable runnable1 = new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(textView.getText() + "handler.post" + "\n\n");
                        }
                    };
                    handler.post(runnable1);

                    TimeUnit.SECONDS.sleep(2);
                    Runnable runnable2 = new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(textView.getText() + "handler.postDelayed" + "\n\n");
                        }
                    };
                    handler.postDelayed(runnable2,1);

                    TimeUnit.SECONDS.sleep(2);
                    Runnable runnable3 = new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(textView.getText() + "Activity.runOnUiThread" + "\n\n");
                        }
                    };
                    runOnUiThread(runnable3);

                    TimeUnit.SECONDS.sleep(2);
                    Runnable runnable4 = new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(textView.getText() + "View.post" + "\n\n");
                        }
                    };
                    textView.post(runnable4);

                    TimeUnit.SECONDS.sleep(2);
                    Runnable runnable5 = new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(textView.getText() + "View.postDelayed");
                        }
                    };
                    textView.postDelayed(runnable5,1);



                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }

    class Obj{
        String string;

        public Obj(String string) {
            this.string = string;
        }

        @Override
        public String toString() {
            return "obj{" +
                    "string='" + string + '\'' +
                    '}';
        }
    }

}
