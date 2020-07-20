package com.example.main_app;

import android.content.Context;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Activity_11 extends AppCompatActivity {

    ExpandableListView elvMain;
    AdapterHelper ah;
    SimpleExpandableListAdapter adapter;
    TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_11);
        elvMain = findViewById(R.id.Act11_elvMain);
        ah = new AdapterHelper(this);
        adapter = ah.getAdapter();
        elvMain.setAdapter(adapter);
        tvInfo = findViewById(R.id.Act11_tvInfo);

        elvMain.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                tvInfo.setText("Вызвали: " + ah.getGroupChildText(groupPosition, childPosition) + "; idGroup = " + groupPosition + "; idChild = " + childPosition);
                return false;
            }
        });

        elvMain.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                tvInfo.setText(ah.getGroupText(groupPosition) + "; id = " + id);
                return false;
            }
        });

        elvMain.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                tvInfo.setText("Свернули: " + ah.getGroupText(groupPosition) + "; idGroup = " + groupPosition);
            }
        });

        elvMain.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                tvInfo.setText("Развернули: " + ah.getGroupText(groupPosition) + "; idGroup = " + groupPosition);
            }
        });
    }

    class AdapterHelper {

        final String ATTR_GROUP_NAME = "groupName";
        final String ATTR_PHONE_NAME = "phoneName";

        String[] groups = {"HTC", "Samsung", "LG"};
        String[] phonesHTC = {"Sensation", "Desire", "WildFire", "Hero"};
        String[] phonesSamsung = {"Galaxy S II", "Galaxy Nexus", "Wave"};
        String[] phonesLG = {"Optimus", "Optimus Link", "Optimus Black", "Optimus One"};

        ArrayList<Map<String, String>> groupData;
        ArrayList<ArrayList<Map<String, String>>> childData;
        ArrayList<Map<String, String>> childDataItem;
        Map<String, String> m;

        Context ctx;
        SimpleExpandableListAdapter adapter;

        AdapterHelper(Context ctx) {
            this.ctx = ctx;
        }

        SimpleExpandableListAdapter getAdapter() {

            groupData = new ArrayList<>();
            String[] groupFrom = {ATTR_GROUP_NAME};
            int[] groupTo = {android.R.id.text1};

            for (String group : groups) {
                m = new HashMap<>();
                m.put("groupName", group);
                groupData.add(m);
            }

            childData = new ArrayList<>();
            String[] childFrom = {ATTR_PHONE_NAME};
            int[] childTo = {android.R.id.text1};

            childDataItem = new ArrayList<>();
            for (String phone : phonesHTC) {
                m = new HashMap<>();
                m.put("phoneName", phone);
                childDataItem.add(m);
            }
            childData.add(childDataItem);

            childDataItem = new ArrayList<>();
            for (String phone : phonesSamsung) {
                m = new HashMap<>();
                m.put("phoneName", phone);
                childDataItem.add(m);
            }
            childData.add(childDataItem);

            childDataItem = new ArrayList<>();
            for (String phone : phonesLG) {
                m = new HashMap<>();
                m.put("phoneName", phone);
                childDataItem.add(m);
            }
            childData.add(childDataItem);

            adapter = new SimpleExpandableListAdapter(ctx,
                    groupData, android.R.layout.simple_expandable_list_item_1, groupFrom, groupTo, childData,
                    android.R.layout.simple_list_item_1, childFrom, childTo);

            return adapter;
        }

        String getGroupText(int groupPos) {
            return ((Map<String, String>) (adapter.getGroup(groupPos))).get(ATTR_GROUP_NAME);
        }

        String getChildText(int groupPos, int childPos) {
            return ((Map<String, String>) (adapter.getChild(groupPos, childPos))).get(ATTR_PHONE_NAME);
        }

        String getGroupChildText(int groupPos, int childPos) {
            return getGroupText(groupPos) + " " + getChildText(groupPos, childPos);
        }
    }
}