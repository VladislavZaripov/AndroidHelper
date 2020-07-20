package com.example.main_app;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Activity_18 extends AppCompatActivity implements View.OnClickListener, DialogInterface.OnClickListener {

    final int DIALOG_TIME = 1;
    int my_Hour;
    int my_minute;
    TextView tvTime;
    TimePickerDialog.OnTimeSetListener myCallBack1 = new TimePickerDialog.OnTimeSetListener() {
        @SuppressLint("SetTextI18n")
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            my_Hour = hourOfDay; my_minute = minute;
            tvTime.setText("Time is: " + my_Hour + " hours " + my_minute + " minutes;");
        }
    };

    final int DIALOG_DATE = 2;
    int myYear;
    int myMonth;
    int myDay;
    TextView tvDate;
    DatePickerDialog.OnDateSetListener myCallBack2 = new DatePickerDialog.OnDateSetListener() {
        @SuppressLint("SetTextI18n")
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            myYear = year; myMonth = monthOfYear; myDay = dayOfMonth;
            tvDate.setText("Today is " + myDay + "/" + myMonth+1 + "/" + myYear);
        }
    };

    final int DIALOG_EXIT = 3;
    Button btnExit;

    final int DIALOG_SHOW_TIME = 4;
    Button btnTime;
    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_18);
        tvTime = findViewById(R.id.Act18_tvTime);
        tvTime.setOnClickListener(this);
        tvDate = findViewById(R.id.Act18_tvDate);
        tvDate.setOnClickListener(this);
        btnExit = findViewById(R.id.Act18_btnExit);
        btnExit.setOnClickListener(this);
        btnTime = findViewById(R.id.Act18_btnTime);
        btnTime.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Act18_tvTime: showDialog(DIALOG_TIME);
            break;
            case R.id.Act18_tvDate: showDialog(DIALOG_DATE);
            break;
            case R.id.Act18_btnExit: showDialog(DIALOG_EXIT);
            break;
            case R.id.Act18_btnTime: showDialog(DIALOG_SHOW_TIME);
            break;
        }
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id){
            case DIALOG_TIME:
                return new TimePickerDialog(this, myCallBack1, my_Hour,my_minute,true);
            case DIALOG_DATE:
                return new DatePickerDialog(this, myCallBack2, myYear, myMonth, myDay);
            case DIALOG_EXIT:
                AlertDialog.Builder adb1 = new AlertDialog.Builder(this);
                adb1.setTitle("Exit");
                adb1.setMessage("Save data");
                adb1.setIcon(android.R.drawable.ic_dialog_info);
                adb1.setPositiveButton("Yes", this);
                adb1.setNegativeButton("No", this);
                adb1.setNeutralButton("Cancel", this);
                return adb1.create();
            case DIALOG_SHOW_TIME:
                AlertDialog.Builder adb2 = new AlertDialog.Builder(this);
                adb2.setTitle("Текущее время");
                adb2.setMessage(simpleDateFormat.format(new Date(System.currentTimeMillis())));
                return adb2.create();
        }
        return super.onCreateDialog(id);
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        super.onPrepareDialog(id, dialog);
        if (DIALOG_SHOW_TIME == id) {
            ((AlertDialog)dialog).setMessage(simpleDateFormat.format(new Date(System.currentTimeMillis())));
        }
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which) {
            case Dialog.BUTTON_POSITIVE:
                Toast.makeText(this, "Saved", Toast.LENGTH_LONG).show();
                finish();
                break;
            case Dialog.BUTTON_NEGATIVE:
                finish();
                break;
            case Dialog.BUTTON_NEUTRAL:
                break;
        }
    }
}
