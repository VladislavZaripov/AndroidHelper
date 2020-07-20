package com.example.main_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Activity_23 extends AppCompatActivity {

    TextView tvInfo;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_23);

        tvInfo = findViewById(R.id.Act23_textView);
        sp = PreferenceManager.getDefaultSharedPreferences(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem mi = menu.add(0,1,0,"Preferences");
        mi.setIntent(new Intent(this, Activity_23_pref.class));
        return super.onCreateOptionsMenu(menu);
    }

    protected void onResume() {
        String address = sp.getString("address", "");
        Boolean notif = sp.getBoolean("chb1", false);
        String listValue = sp.getString("list","не выбрано");

        String text = "";
        text = text.concat("\n address = " + address);
        text = text.concat("\n chb1 = " + ((notif) ? "enabled" : "disabled"));
        text = text.concat("\n listValue = " + listValue);
        tvInfo.setText(text);
        super.onResume();
    }
}
