package com.example.main_app;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Activity_20 extends AppCompatActivity {

    final int DIALOG1 = 1;
    final int DIALOG2 = 2;

    int btn;
    LinearLayout view;
    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    TextView tvCount;
    ArrayList<TextView> textViews;

    ProgressDialog pd;
    Handler h;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_20);
        textViews = new ArrayList<>(10);
    }

    public void onclick(View v) {
        switch (v.getId()){
            case R.id.Act20_add:
            case R.id.Act20_delete:
                btn = v.getId();
                showDialog(DIALOG1);
                break;
            case R.id.Act20_dialog:
                showDialog(DIALOG2);
                h = new Handler();
                h.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        removeDialog(DIALOG2);
                    }
                },2000);
                break;
            case R.id.Act20_default:
                pd = new ProgressDialog(this);
                pd.setTitle("Title");
                pd.setMessage("Message");
                pd.setButton(Dialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                pd.show();
                break;
            case R.id.Act20_horizontal:
                pd = new ProgressDialog(this);
                pd.setTitle("Title");
                pd.setMessage("Message");
                pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pd.setMax(2148);
                pd.setIndeterminate(true);
                pd.show();
                h = new Handler() {
                    @SuppressLint("HandlerLeak")
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        pd.setIndeterminate(false);
                        if (pd.getProgress() < pd.getMax()) {
                            pd.incrementProgressBy(50);
                            pd.incrementSecondaryProgressBy(75);
                            h.sendEmptyMessageDelayed(0, 100);
                        } else {
                            pd.dismiss();
                        }
                    }
                };
                h.sendEmptyMessageDelayed(0, 2000);
                break;
        }
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);

        switch (id){
            case DIALOG1:
                adb.setTitle("Custom dialog");
                view = (LinearLayout) getLayoutInflater().inflate(R.layout.activity_20_dialog, null);
                adb.setView(view);
                tvCount = view.findViewById(R.id.Act20_dialog_tvCount);
                break;
            case DIALOG2:
                Toast.makeText(this,"Create dialog",Toast.LENGTH_LONG).show();
                adb.setTitle("Title");
                adb.setMessage("Message");
                adb.setPositiveButton("OK",null);
                Dialog dialog = adb.create();

                dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialog) {
                        Toast.makeText(Activity_20.this,"Show",Toast.LENGTH_LONG).show();
                    }
                });

                dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        Toast.makeText(Activity_20.this,"Cancel",Toast.LENGTH_LONG).show();
                    }
                });

                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        Toast.makeText(Activity_20.this,"Dismiss",Toast.LENGTH_LONG).show();
                    }
                });
                return dialog;
        }
        return adb.create();
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        super.onPrepareDialog(id, dialog);
        switch (id){
            case DIALOG1:
                TextView tvTime = dialog.getWindow().findViewById(R.id.Act20_dialog_tvTime);
                tvTime.setText(sdf.format(new Date(System.currentTimeMillis())));
                TextView tv;
                switch (btn){
                    case R.id.Act20_add:
                        tv = new TextView(this);
                        view.addView(tv, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        tv.setText("TextView " + (textViews.size() + 1));
                        textViews.add(tv);
                        break;
                    case R.id.Act20_delete:
                        if (textViews.size() > 0) {
                            tv = textViews.get(textViews.size() - 1);
                            view.removeView(tv);
                            textViews.remove(tv);
                        }
                        break;
                }
                tvCount.setText("Кол-во TextView = " + textViews.size());
                break;
        }
    }
}