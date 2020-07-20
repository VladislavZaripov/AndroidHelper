package com.example.main_app;

import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Activity_7 extends AppCompatActivity implements View.OnClickListener {

    Button bSave, bLoad;
    EditText text;
    SharedPreferences sPref;
    final String SAVED_TEXT = "saved_text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_7);
        bSave = findViewById(R.id.Act7_buttonSave);
        bLoad = findViewById(R.id.Act7_buttonLoad);
        text = findViewById(R.id.Act7_editText);
        bSave.setOnClickListener(this);
        bLoad.setOnClickListener(this);
        text.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Act7_buttonSave:
                saveText();
                break;
            case R.id.Act7_buttonLoad:
                loadText();
                break;
            case R.id.Act7_editText:
                text.setText("");
                break;
        }
    }

    private void saveText() {
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(SAVED_TEXT, text.getText().toString());
        ed.commit();
        text.setText("");
    }

    private void loadText() {
        sPref = getPreferences(MODE_PRIVATE);
        text.setText(sPref.getString(SAVED_TEXT,""));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveText();
    }
}