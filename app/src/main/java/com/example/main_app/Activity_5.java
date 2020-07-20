package com.example.main_app;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Objects;

public class Activity_5 extends AppCompatActivity implements View.OnClickListener {

    EditText editText;
    Button button1;

    TextView textView;
    Button button2;
    final int requestCodeInt = 111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5);

        editText = findViewById(R.id.Act5_editText);
        button1 = findViewById(R.id.Act5_button1);
        button1.setOnClickListener(this);

        textView = findViewById(R.id.Act5_textView);
        button2 = findViewById(R.id.Act5_button2);
        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(this, Activity_5a.class);

        switch (v.getId()) {
            case R.id.Act5_button1:
                intent.putExtra("name1", editText.getText().toString());
                startActivity(intent);
                break;
            case R.id.Act5_button2:
                startActivityForResult(intent, requestCodeInt);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK)
        switch (requestCode){
            case requestCodeInt:
                String string;
                if (data == null) return;
                else string = data.getStringExtra("name2");
                textView.setText(string);
                break;
        }
    }
}