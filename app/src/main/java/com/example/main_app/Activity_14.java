package com.example.main_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ExpandableListView;
import android.widget.SimpleCursorTreeAdapter;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Activity_14 extends AppCompatActivity {

    ExpandableListView elvMain;
    DB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_14);
        db = new DB(this);
        db.open();

        Cursor cursor = db.getCompanyData();
        startManagingCursor(cursor);

        String[] groupFrom = {DB.COMPANY_COLUMN_NAME};
        int[] groupTo = {android.R.id.text1};

        String[] childFrom = {DB.PHONE_COLUMN_NAME};
        int[] childTo = {android.R.id.text1};

        SimpleCursorTreeAdapter adapter = new MyAdapter(this, cursor, android.R.layout.simple_expandable_list_item_1,
                groupFrom, groupTo, android.R.layout.simple_list_item_1, childFrom, childTo);

        elvMain = findViewById(R.id.Act14_elvMain);
        elvMain.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }

    class MyAdapter extends SimpleCursorTreeAdapter {
        public MyAdapter(Context context, Cursor cursor, int groupLayout, String[] groupFrom,
                         int[] groupTo, int childLayout, String[] childFrom, int[] childTo) {
            super(context, cursor, groupLayout, groupFrom, groupTo, childLayout, childFrom, childTo);
        }


        @Override
        protected Cursor getChildrenCursor(Cursor groupCursor) {
            int idColumn = groupCursor.getColumnIndex(DB.COMPANY_COLUMN_ID);
            return db.getPhoneData(groupCursor.getInt(idColumn));
        }
    }

    public class DB {

        private static final String DB_NAME = "DataBase14";
        private static final int DB_VERSION = 1;

        // имя таблицы компаний, поля и запрос создания
        private static final String COMPANY_TABLE = "company";
        public static final String COMPANY_COLUMN_ID = "_id";
        public static final String COMPANY_COLUMN_NAME = "name";
        private static final String COMPANY_TABLE_CREATE = "create table "
                + COMPANY_TABLE + "(" + COMPANY_COLUMN_ID
                + " integer primary key, " + COMPANY_COLUMN_NAME + " text" + ");";

        // имя таблицы телефонов, поля и запрос создания
        private static final String PHONE_TABLE = "phone";
        public static final String PHONE_COLUMN_ID = "_id";
        public static final String PHONE_COLUMN_NAME = "name";
        public static final String PHONE_COLUMN_COMPANY = "company";
        private static final String PHONE_TABLE_CREATE = "create table "
                + PHONE_TABLE + "(" + PHONE_COLUMN_ID
                + " integer primary key autoincrement, " + PHONE_COLUMN_NAME
                + " text, " + PHONE_COLUMN_COMPANY + " integer" + ");";

        private final Context mCtx;

        private DBHelper mDBHelper;
        private SQLiteDatabase mDB;

        public DB(Context ctx) {
            mCtx = ctx;
        }

        // открываем подключение
        public void open() {
            mDBHelper = new DBHelper(mCtx, DB_NAME, null, DB_VERSION);
            mDB = mDBHelper.getWritableDatabase();
        }

        // закрываем подключение
        public void close() {
            if (mDBHelper != null)
                mDBHelper.close();
        }

        // данные по компаниям
        public Cursor getCompanyData() {
            return mDB.query(COMPANY_TABLE, null, null, null, null, null, null);
        }

        // данные по телефонам конкретной группы
        public Cursor getPhoneData(long companyID) {
            return mDB.query(PHONE_TABLE, null, PHONE_COLUMN_COMPANY + " = "
                    + companyID, null, null, null, null);
        }

        private class DBHelper extends SQLiteOpenHelper {

            public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                            int version) {
                super(context, name, factory, version);
            }

            @Override
            public void onCreate(SQLiteDatabase db) {
                ContentValues cv = new ContentValues();

                // названия компаний (групп)
                String[] companies = new String[]{"HTC", "Samsung", "LG"};

                // создаем и заполняем таблицу компаний
                db.execSQL(COMPANY_TABLE_CREATE);
                for (int i = 0; i < companies.length; i++) {
                    cv.put(COMPANY_COLUMN_ID, i + 1);
                    cv.put(COMPANY_COLUMN_NAME, companies[i]);
                    db.insert(COMPANY_TABLE, null, cv);
                }

                // названия телефонов (элементов)
                String[] phonesHTC = new String[]{"Sensation", "Desire",
                        "Wildfire", "Hero"};
                String[] phonesSams = new String[]{"Galaxy S II", "Galaxy Nexus",
                        "Wave"};
                String[] phonesLG = new String[]{"Optimus", "Optimus Link",
                        "Optimus Black", "Optimus One"};

                // создаем и заполняем таблицу телефонов
                db.execSQL(PHONE_TABLE_CREATE);
                cv.clear();
                for (String s : phonesHTC) {
                    cv.put(PHONE_COLUMN_COMPANY, 1);
                    cv.put(PHONE_COLUMN_NAME, s);
                    db.insert(PHONE_TABLE, null, cv);
                }
                for (String phonesSam : phonesSams) {
                    cv.put(PHONE_COLUMN_COMPANY, 2);
                    cv.put(PHONE_COLUMN_NAME, phonesSam);
                    db.insert(PHONE_TABLE, null, cv);
                }
                for (String s : phonesLG) {
                    cv.put(PHONE_COLUMN_COMPANY, 3);
                    cv.put(PHONE_COLUMN_NAME, s);
                    db.insert(PHONE_TABLE, null, cv);
                }
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            }
        }
    }
}

