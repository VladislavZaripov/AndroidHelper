package com.example.main_app;

import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Activity_10 extends AppCompatActivity {

    ListView lvMain;
    TextView text1;
    TextView text2;
    TextView text3;
    String [] names;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_10);

        names = getResources().getStringArray(R.array.Act10_names);

        lvMain = findViewById(R.id.Act10_lvMain);
        lvMain.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.
                createFromResource(this, R.array.Act10_names, android.R.layout.simple_list_item_multiple_choice);
        lvMain.setAdapter(adapter);

        text1 = findViewById(R.id.Act10_textView1);
        text2 = findViewById(R.id.Act10_textView2);
        text3 = findViewById(R.id.Act10_textView3);



        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                text1.setText("Item click position = " + position + ", id = " + id);
                String text = "";
                SparseBooleanArray sbArray = lvMain.getCheckedItemPositions();
                for (int i = 0; i < sbArray.size(); i++) {
                    int key = sbArray.keyAt(i);
                    if (sbArray.get(key))
                        text = text.concat(names[key] + "\n");
                }
                text2.setText(text.split("\\n+$", 2)[0] + "");
            }
        });

        lvMain.setOnScrollListener(new AbsListView.OnScrollListener(){
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                text3.setText("scroll state = " + scrollState);
            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                text3.setText("scroll: firstVisibleItem = " + firstVisibleItem + ", visibleItemCount = " + visibleItemCount + ", totalItemCount = " + totalItemCount);
            }
        });
    }
}
