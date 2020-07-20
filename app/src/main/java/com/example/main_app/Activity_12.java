package com.example.main_app;

import android.content.Context;
import android.graphics.Color;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Activity_12 extends AppCompatActivity {

    final String ATTRIBUTE_NAME_VALUE = "value";
    int[] values = {80, 40, -30, 20, -50, 0, 30, -60, 10, -10};
    final String ATTRIBUTE_NAME_IMAGE = "image";
    final int POSITIVE = R.drawable.apps_wheresmydroid;
    final int NEGATIVE = R.drawable.apps_voodoo;
    final String ATTRIBUTE_NAME_CHECKED = "checked";
    boolean [] checked = {true,true,false,true,false,true,true,false,true,false};
    final String ATTRIBUTE_NAME_PROGRESS = "progress";
    ListView lvMain;
    ArrayList<Map<String, Object>> data;
    SimpleAdapter adapter;
    Map<String, Object> m;

    private final int CM_DELETE_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_12);

        data = new ArrayList<>();

        for (int i = 0; i < values.length; i++) {
            m = new HashMap<>();
            m.put(ATTRIBUTE_NAME_VALUE, values[i]);
            if (values[i] > 0) m.put(ATTRIBUTE_NAME_IMAGE, POSITIVE);
            else if (values[i] < 0) m.put(ATTRIBUTE_NAME_IMAGE, NEGATIVE);
            else m.put(ATTRIBUTE_NAME_IMAGE, 0);
            m.put(ATTRIBUTE_NAME_CHECKED,checked[i]);
            m.put(ATTRIBUTE_NAME_PROGRESS,values[i]);
            data.add(m);
        }

        String[] from = {ATTRIBUTE_NAME_VALUE, ATTRIBUTE_NAME_IMAGE, ATTRIBUTE_NAME_CHECKED, ATTRIBUTE_NAME_PROGRESS, ATTRIBUTE_NAME_PROGRESS};
        int[] to = {R.id.Act12_item_textView, R.id.Act12_item_imageView, R.id.Act12_item_CheckBox, R.id.Act12_item_ProgressBar};

        adapter = new MySimpleAdapter(this, data, R.layout.activity_12_item, from, to);
        adapter.setViewBinder(new MyViewBinder());
        lvMain = findViewById(R.id.Act12_lvMain);
        lvMain.setAdapter(adapter);
        registerForContextMenu(lvMain);
    }

    public void OnButtonClick (View view) {
        m = new HashMap<>();
        int randomNum = -100 + (int)(Math.random() * 200);
        m.put(ATTRIBUTE_NAME_VALUE,randomNum);
        if (randomNum > 0) m.put(ATTRIBUTE_NAME_IMAGE, POSITIVE);
        else if (randomNum < 0) m.put(ATTRIBUTE_NAME_IMAGE, NEGATIVE);
        else m.put(ATTRIBUTE_NAME_IMAGE, 0);
        m.put(ATTRIBUTE_NAME_CHECKED,Math.random() < 0.5);
        m.put(ATTRIBUTE_NAME_PROGRESS,randomNum);
        data.add(m);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        switch (v.getId()){
            case R.id.Act12_lvMain: menu.add(0,CM_DELETE_ID,0,"Удалить запись");
            break;
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case CM_DELETE_ID:
                AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                data.remove(acmi.position);
                adapter.notifyDataSetChanged();
                return true;
        }
        return super.onContextItemSelected(item);
    }

    class MySimpleAdapter extends SimpleAdapter {
        public MySimpleAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
            super(context, data, resource, from, to);
        }

        @Override
        public void setViewText(TextView v, String text) {
            super.setViewText(v, text);
            if (v.getId() == R.id.Act12_item_textView)
                if (Integer.parseInt(text) < 0) v.setTextColor(Color.RED);
                else if (Integer.parseInt(text) > 0) v.setTextColor(Color.GREEN);
        }

        @Override
        public void setViewImage(ImageView v, int value) {
            super.setViewImage(v, value);
            if (v.getId() == R.id.Act12_item_imageView)
                if (value == NEGATIVE) v.setBackgroundColor(Color.RED);
                else if (value == POSITIVE) v.setBackgroundColor(Color.GREEN);
        }
    }

    class MyViewBinder implements SimpleAdapter.ViewBinder{
        @Override
        public boolean setViewValue(View view, Object data, String textRepresentation) {
            switch (view.getId()){
                case R.id.Act12_item_ProgressBar:
                    int i = (Integer) data;
                   ((ProgressBar)view).setProgress(i);
                    return true;
            }
            return false;
        }
    }
}