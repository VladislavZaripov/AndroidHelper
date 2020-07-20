package com.example.main_app;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Objects;

public class Activity_5a extends AppCompatActivity implements View.OnClickListener {

    TextView textView;
    String message;

    EditText editText;
    Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5a);

        textView = findViewById(R.id.Act5a_textView);
        Intent intent = getIntent();
        message = intent.getStringExtra("name1");
        textView.setText(message);

        editText = findViewById(R.id.Act5a_editText);
        button3 = findViewById(R.id.Act5a_button2);
        button3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Act5a_button2:
                Intent intent = new Intent(this,Activity_5.class);
                intent.putExtra("name2",editText.getText().toString());
                setResult(RESULT_OK,intent);
                finish();
        }
    }
}
