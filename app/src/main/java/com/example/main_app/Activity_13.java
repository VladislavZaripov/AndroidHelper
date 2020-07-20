package com.example.main_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Activity_13 extends AppCompatActivity {

    private static final int CM_DELETED_ID = 1;
    ListView lvMain;
    DB db;
    SimpleCursorAdapter adapter;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_13);
        db = new DB(this);
        db.open();

        cursor = db.getAllData();
        startManagingCursor(cursor);

        String [] from = {DB.COLUMN_IMG, DB.COLUMN_TXT};
        int [] to = {R.id.ivImg, R.id.tvText};

        adapter = new SimpleCursorAdapter(this, R.layout.activity_13_item,cursor,from,to);
        lvMain = findViewById(R.id.Act13_LvData);
        lvMain.setAdapter(adapter);

        registerForContextMenu(lvMain);
    }

    public void onButtonClick (View view){
        db.addRec("sometext" + (cursor.getCount()+1), R.mipmap.ic_launcher_round) ;
        cursor.requery();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        switch (v.getId()){
            case R.id.Act13_LvData: menu.add(0, CM_DELETED_ID,0, "Delete_record");
                break;
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case CM_DELETED_ID:
                AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                db.delRec(acmi.id);
                cursor.requery();
                return true;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }

    public class DB {
        private static final String DB_NAME = "DataBase13";
        private static final int DB_VERSION = 1;
        private static final String DB_TABLE = "mytab";

        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_IMG = "img";
        public static final String COLUMN_TXT = "txt";

        private static final String DB_CREATE = "create table " + DB_TABLE + "(" +
                COLUMN_ID + " integer primary key autoincrement, " +
                COLUMN_IMG + " integer, " +
                COLUMN_TXT + " text" +
                ");";

        private final Context mCtx;
        private DBHelper mDBHelper;
        private SQLiteDatabase mDB;

        public DB(Context mCtx) {
            this.mCtx = mCtx;
        }

        public void open(){
            mDBHelper = new DBHelper(mCtx, DB_NAME,null,DB_VERSION);
            mDB = mDBHelper.getWritableDatabase();
        }

        public void close(){
            if (mDBHelper != null) mDBHelper.close();
        }

        public Cursor getAllData () {
            return mDB.query(DB_TABLE,null,null,null,null,null,null);
        }

        public void addRec (String txt, int img) {
            ContentValues cv = new ContentValues();
            cv.put(COLUMN_TXT,txt);
            cv.put(COLUMN_IMG,img);
            mDB.insert(DB_TABLE,null,cv);
        }

        public void delRec (long id) {
            mDB.delete(DB_TABLE, COLUMN_ID + " = " + id, null);
        }

        class DBHelper extends SQLiteOpenHelper {
            public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
                super(context, name, factory, version);
            }

            @Override
            public void onCreate(SQLiteDatabase db) {
                db.execSQL(DB_CREATE);
                ContentValues cv = new ContentValues();
                for (int i = 0; i < 5; i++){
                    cv.put(COLUMN_TXT,"sometext"+(i+1));
                    cv.put(COLUMN_IMG,R.mipmap.ic_launcher_round);
                    db.insert(DB_TABLE,null,cv);
                    cv.clear();
                }
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            }
        }
    }
}
