package com.example.main_app;

import android.app.TabActivity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.Toast;
import android.os.Bundle;

public class Activity_25 extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_25);

        TabHost tabHost = getTabHost();
        TabHost.TabSpec tabSpec;

        tabSpec = tabHost.newTabSpec("tag1");
        tabSpec.setIndicator("TAB 1");
        tabSpec.setContent(R.id.Act25_tab1);
        tabHost.addTab(tabSpec);


        tabSpec = tabHost.newTabSpec("tag2");
        View view = getLayoutInflater().inflate(R.layout.activity_25_tab_header, null);
        ((ImageView)view.findViewById(R.id.Act25_tab_imageView)).setImageResource(R.drawable.apps_voodoo);
        tabSpec.setIndicator(view);
        tabSpec.setContent(R.id.Act25_tab2);
        tabHost.addTab(tabSpec);


        tabSpec = tabHost.newTabSpec("tag3");
        tabSpec.setIndicator("TAB 3");
        tabSpec.setContent(new Intent().setClass(this, Activity_4.class));
        tabHost.addTab(tabSpec);


        tabHost.setCurrentTabByTag("tag");


        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            public void onTabChanged(String tabId) {
                Toast.makeText(getBaseContext(), "tabId = " + tabId, Toast.LENGTH_SHORT).show();
            }
        });
    }
}