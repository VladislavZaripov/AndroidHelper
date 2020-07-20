package com.example.main_app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;
import androidx.annotation.NonNull;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Activity_28 extends Activity implements View.OnClickListener {

    MyTask mt;
    static TextView tvInfo;
    Button button0, button1, button2, button3, button4, button5;
    String string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_28);
        tvInfo = findViewById(R.id.Act28_textView);
        button0 = findViewById(R.id.Act28_button0);
        button1 = findViewById(R.id.Act28_button1);
        button2 = findViewById(R.id.Act28_button2);
        button3 = findViewById(R.id.Act28_button3);
        button4 = findViewById(R.id.Act28_button4);
        button5 = findViewById(R.id.Act28_button5);
        button0.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        mt = (MyTask) getLastNonConfigurationInstance();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Act28_button0:
                mt = new MyTask();
                mt.link(this);                                                              // for turn the activity
                Toast.makeText(this,"New task create", Toast.LENGTH_LONG).show();
                break;
            case R.id.Act28_button1:
                try {
                    mt.execute();
                    Toast.makeText(this,"Task run", Toast.LENGTH_LONG).show();
                }
                catch (Exception e)
                {
                    Toast.makeText(this,"No Task", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.Act28_button2:
                try {
                    mt.cancel(false);
                }
                catch (Exception e)
                {
                    Toast.makeText(this,"No Task", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.Act28_button3:
                try {
                    tvInfo.setText(tvInfo.getText() + "\n\n" + "get result: " + mt.get() + "\n");
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (CancellationException e){
                    tvInfo.setText(tvInfo.getText() + "\n\n" + "get: " + e);
                } catch (Exception e) {
                    Toast.makeText(this, "No Task", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.Act28_button4:
                try {
                    tvInfo.setText(tvInfo.getText() + "\n\n" + "get(1,TimeUnit.SECONDS) result: " + mt.get(1,TimeUnit.SECONDS));
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    tvInfo.setText(tvInfo.getText() + "\n\n" + "get(1,TimeUnit.SECONDS): " + e);
                } catch (CancellationException e){
                    tvInfo.setText(tvInfo.getText() + "\n\n" + "get(1,TimeUnit.SECONDS): " + e);
                } catch (Exception e) {
                    Toast.makeText(this, "No Task", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.Act28_button5:
                try {
                    Toast.makeText(this,mt.getStatus().toString(), Toast.LENGTH_LONG).show();
                }
                catch (Exception e)
                {
                    Toast.makeText(this,"No Task", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    @SuppressLint("StaticFieldLeak")
    static class MyTask extends AsyncTask <String, Integer, Boolean> {                  // static for turn the activity

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tvInfo.setText("onPreExecute\n");
        }

        @Override
        protected Boolean doInBackground(String... params) {
            try {
                for (int i = 0; i < 5; i++) {
                    TimeUnit.SECONDS.sleep(1); // Some hard task in separate thread, that do not have access to activity
                    publishProgress(i);
                    if (isCancelled()) return null; // For cancel task
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            tvInfo.setText(tvInfo.getText() +"\n" + "onProgressUpdate count: " + values[0]);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            tvInfo.setText(tvInfo.getText()+ "\n\n" +"onPostExecute result: " + result);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            tvInfo.setText(tvInfo.getText() + "\n\n" + "onCancelled");
        }

        Activity_28 activity;                                                                   // for turn the activity

        void link(Activity_28 act) {                                                            // for turn the activity
            activity = act;
        }

        void unLink() {                                                                         // for turn the activity
            activity = null;
        }
    }

    public Object onRetainNonConfigurationInstance() {                                          // for turn the activity
        mt.unLink();
        return mt;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        string = tvInfo.getText().toString();
        outState.putString("string",string);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        string = savedInstanceState.getString("string");
        tvInfo.setText(string);
    }
}