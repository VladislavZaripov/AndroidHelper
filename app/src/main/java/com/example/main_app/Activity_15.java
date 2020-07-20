package com.example.main_app;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.HeaderViewListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Activity_15 extends AppCompatActivity {

    String [] data = {"one","two","three","four","five"};
    ArrayAdapter<String> adapter;

    View header1;
    View header2;
    View footer1;
    View footer2;

    ListView lvMain;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_15);

        lvMain = findViewById(R.id.Act15_LvMain);
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,data);

        textView = findViewById(R.id.Act15_textView);

        header1 = createHeader("header 1");
        header2 = createHeader("header 2");
        footer1 = createFooter("footer 1");
        footer2 = createFooter("footer 2");

        fillList();
    }

    void fillList(){
        lvMain.addHeaderView(header1);
        lvMain.addHeaderView(header2,"some text for header2",false);
        lvMain.addFooterView(footer1);
        lvMain.addFooterView(footer2,"some text for footer2",false);
        lvMain.setAdapter(adapter);
    }

    public void  onclick (View v) {
        Object obj;
        String string = "";
        HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter) lvMain.getAdapter();
        obj = headerViewListAdapter.getItem(1);
        string = string.concat("headerViewListAdapter.getItem(1) = " + obj.toString() + "\n");
        obj = headerViewListAdapter.getItem(4);
        string = string.concat("headerViewListAdapter.getItem(4) = " + obj.toString() + "\n");

        ArrayAdapter <String> arrayAdapter = (ArrayAdapter) headerViewListAdapter.getWrappedAdapter();
        obj = arrayAdapter.getItem(1);
        string = string.concat("arrayAdapter.getItem(1) = " + obj.toString() + "\n");
        obj = arrayAdapter.getItem(4);
        string = string.concat("arrayAdapter.getItem(4) = " + obj.toString() + "\n");
        textView.setText(string);
    }

    View createHeader (String text){
        View v = getLayoutInflater().inflate(R.layout.activity_15_header,null);
        ((TextView)v.findViewById(R.id.Act15_header_tvText)).setText(text);
        return v;
    }

    View createFooter (String text){
        View v = getLayoutInflater().inflate(R.layout.activity_15_footer,null);
        ((TextView)v.findViewById(R.id.Act15_footer_tvText)).setText(text);
        return v;
    }
}