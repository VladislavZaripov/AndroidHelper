package com.example.main_app;

import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Activity_21a extends AppCompatActivity {

    TextView textView0, textView1, textView2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_21a);

        MyObject myObj = getIntent().getParcelableExtra(MyObject.class.getCanonicalName());

        textView0 = findViewById(R.id.Act21a_textView0);
        textView1 = findViewById(R.id.Act21a_textView1);
        textView2 = findViewById(R.id.Act21a_textView2);
        textView0.setText(myObj.toString());
        textView1.setText(myObj.s);
        textView2.setText(String.valueOf(myObj.i));
    }
}