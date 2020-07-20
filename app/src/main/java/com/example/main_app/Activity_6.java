package com.example.main_app;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Activity_6 extends AppCompatActivity implements View.OnClickListener {

    Button bWEB, bMAP, bCALL;
    EditText page, latitude , longitude , number;
    String pageS, latitudeS , longitudeS , numberS;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_6);

        bWEB = findViewById(R.id.Act6_buttonWEB);
        bMAP = findViewById(R.id.Act6_buttonMAP);
        bCALL = findViewById(R.id.Act6_buttonCALL);
        bWEB.setOnClickListener(this);
        bMAP.setOnClickListener(this);
        bCALL.setOnClickListener(this);

        page = findViewById(R.id.Act6_editText1);
        latitude = findViewById(R.id.Act6_editText2);
        longitude = findViewById(R.id.Act6_editText3);
        number = findViewById(R.id.Act6_editText4);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.Act6_buttonWEB:
                if (page.getText().toString().equals("")) pageS = "https://yandex.ru/";
                else pageS = page.getText().toString();

                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(pageS));
                startActivity(intent);
                break;

            case R.id.Act6_buttonMAP:
                if (latitude.getText().toString().equals("")||longitude.getText().toString().equals("")) {
                    latitudeS = "55.787847";
                    longitudeS = "37.939277";
                }
                else{
                    latitudeS = latitude.getText().toString();
                    longitudeS = longitude.getText().toString();}

                intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("geo: " + latitudeS + "," + longitudeS));
                startActivity(intent);
                break;

            case R.id.Act6_buttonCALL:
                if (number.getText().toString().equals("")) numberS = "+79258259600";
                else numberS = number.getText().toString();

                intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+ numberS));
                startActivity(intent);
                break;
        }

    }
}
