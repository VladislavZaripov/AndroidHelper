package com.example.main_app;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Objects;

public class Activity_2 extends AppCompatActivity implements View.OnClickListener{

    LinearLayout llMain;
    RadioGroup rgGravity;
    RadioButton rbLeft, rbCenter, rbRight;
    EditText etName;
    Button btnCreate, btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        llMain = findViewById(R.id.Act2_llMain);
        rgGravity = findViewById(R.id.Act2_rg);
        rbLeft = findViewById(R.id.Act2_rbLeft);
        rbCenter = findViewById(R.id.Act2_rbCenter);
        rbRight = findViewById(R.id.Act2_rbRight);
        etName = findViewById(R.id.Act2_etName);
        btnCreate = findViewById(R.id.Act2_btnCreate);
        btnClear = findViewById(R.id.Act2_btnClear);

        btnCreate.setOnClickListener(this);
        btnClear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.Act2_btnCreate:
                LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                int btnGravity = Gravity.LEFT;
                switch (rgGravity.getCheckedRadioButtonId()){
                    case R.id.Act2_rbLeft: btnGravity = Gravity.LEFT; break;
                    case R.id.Act2_rbCenter: btnGravity = Gravity.CENTER; break;
                    case R.id.Act2_rbRight: btnGravity = Gravity.RIGHT; break;
                }
                lParams.gravity = btnGravity;
                Button btnNew = new Button(this);
                btnNew.setText(etName.getText().toString());
                llMain.addView(btnNew,lParams);
                break;

            case R.id.Act2_btnClear:
                llMain.removeAllViews();
                Toast.makeText(Activity_2.this,"All CLEAR",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}