package com.example.main_app;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Activity_19 extends AppCompatActivity{

    String [] data = { "one", "two", "three", "four" };
    DB db;
    Cursor cursor;
    int cnt = 0;

    final  int DIALOG_ITEM1 = 1;
    final  int DIALOG_ADAPTER1 = 2;
    final  int DIALOG_CURSOR1 = 3;

    final int DIALOG_ITEMS2 = 4;
    final int DIALOG_ADAPTER2 = 5;
    final int DIALOG_CURSOR2 = 6;

    final int DIALOG_ITEMS3 = 7;
    final int DIALOG_CURSOR3 = 8;
    boolean [] chkd = { false, true, true, false };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_19);

        db = new DB(this);
        db.open();
        cursor = db.getAllData();
        startManagingCursor(cursor);
    }

    public void onclick (View view){
        changeCount();
        switch (view.getId()){
            case R.id.Act19_btnItems1:
                showDialog(DIALOG_ITEM1);
                break;
            case R.id.Act19_btnAdapter1:
                showDialog(DIALOG_ADAPTER1);
                break;
            case R.id.Act19_btnCursor1:
                showDialog(DIALOG_CURSOR1);
                break;
            case R.id.Act19_btnItems2:
                showDialog(DIALOG_ITEMS2);
                break;
            case R.id.Act19_btnAdapter2:
                showDialog(DIALOG_ADAPTER2);
                break;
            case R.id.Act19_btnCursor2:
                showDialog(DIALOG_CURSOR2);
                break;
            case R.id.Act19_btnItems3:
                showDialog(DIALOG_ITEMS3);
                break;
            case R.id.Act19_btnCursor3:
                showDialog(DIALOG_CURSOR3);
                break;
            default:
                break;
        }
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        switch (id){
            case DIALOG_ITEM1:
                adb.setTitle("items");
                adb.setItems(data,myClickListener1);
                break;
            case DIALOG_ADAPTER1:
                adb.setTitle("adapter");
                ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.select_dialog_item, data);
                adb.setAdapter(adapter1,myClickListener1);
                break;
            case DIALOG_CURSOR1:
                adb.setTitle("cursor");
                adb.setCursor(cursor,myClickListener1,DB.COLUMN_TXT);
                break;
            case DIALOG_ITEMS2:
                adb.setTitle("items");
                adb.setSingleChoiceItems(data, -1, myClickListener2);
                adb.setPositiveButton("ok", myClickListener2);
                break;
            case DIALOG_ADAPTER2:
                adb.setTitle("adapter");
                ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.select_dialog_singlechoice, data);
                adb.setSingleChoiceItems(adapter2, -1, myClickListener2);
                adb.setPositiveButton("ok", myClickListener2);
                break;
            case DIALOG_CURSOR2:
                adb.setTitle("cursor");
                adb.setSingleChoiceItems(cursor, -1, DB.COLUMN_TXT, myClickListener2);
                adb.setPositiveButton("ok", myClickListener2);
                break;
            case DIALOG_ITEMS3:
                adb.setTitle("items");
                adb.setMultiChoiceItems(data, chkd, myClickListener3);
                adb.setPositiveButton("ok", myBtnClickListener);
                break;
            case DIALOG_CURSOR3:
                adb.setTitle("cursor");
                adb.setMultiChoiceItems(cursor, DB.COLUMN_CHK, DB.COLUMN_TXT, myClickListener4);
                adb.setPositiveButton("ok", myBtnClickListener);
        }
        return adb.create();
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        AlertDialog adialog = (AlertDialog) dialog;
        ListAdapter listAdapter = adialog.getListView().getAdapter();

        switch (id) {
            case DIALOG_ITEM1:
            case DIALOG_ADAPTER1:
                if (listAdapter instanceof BaseAdapter) {
                    BaseAdapter bAdapter = (BaseAdapter) listAdapter;
                    bAdapter.notifyDataSetChanged();
                }
                break;
            case DIALOG_CURSOR1:
                break;
            default: break;
        }
    }

    DialogInterface.OnClickListener myClickListener1 = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
        }
    };

    DialogInterface.OnClickListener myClickListener2 = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            ListView lv = ((AlertDialog) dialog).getListView();
            if (which == Dialog.BUTTON_POSITIVE)
                Toast.makeText(Activity_19.this,"pos = " + lv.getCheckedItemPosition(),Toast.LENGTH_LONG).show();
            else
                Toast.makeText(Activity_19.this,"which = " + which,Toast.LENGTH_LONG).show();
        }
    };

    DialogInterface.OnMultiChoiceClickListener myClickListener3 = new DialogInterface.OnMultiChoiceClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which, boolean isChecked) {
            Toast.makeText(Activity_19.this,"which = " + which + ", isChecked = " + isChecked,Toast.LENGTH_LONG).show();
        }
    };

    DialogInterface.OnMultiChoiceClickListener myClickListener4 = new DialogInterface.OnMultiChoiceClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which, boolean isChecked) {
            Toast.makeText(Activity_19.this,"which = " + which + ", isChecked = " + isChecked,Toast.LENGTH_LONG).show();
            db.changeRec2(which, isChecked);
            cursor.requery();
        }
    };

    DialogInterface.OnClickListener myBtnClickListener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            SparseBooleanArray sbArray = ((AlertDialog)dialog).getListView().getCheckedItemPositions();
            String string = "";
            for (int i = 0; i < sbArray.size(); i++) {
                int key = sbArray.keyAt(i);
                if (sbArray.get(key))
                    string = string.concat(" " + key);
            }
            Toast.makeText(Activity_19.this,"checked: " + string,Toast.LENGTH_LONG).show();
        }
    };

    void changeCount() {
        cnt++;
        data[3] = String.valueOf(cnt);
        db.changeRec(4, String.valueOf(cnt));
        cursor.requery();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }

    public class DB {

        private static final int DB_VERSION = 1;
        private SQLiteDatabase mDB;
        private DBHelper mDBHelper;
        private final Context mCtx;

        private static final String DB_NAME = "DataBase19";

        private static final String DB_TABLE = "mytab";

        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_CHK = "checked";
        public static final String COLUMN_TXT = "txt";

        private static final String DB_CREATE =
                "create table " + DB_TABLE + "(" +
                        COLUMN_ID + " integer primary key autoincrement, " +
                        COLUMN_CHK + " integer, " +
                        COLUMN_TXT + " text" +
                        ");";

        public DB(Context context) {
            mCtx = context;
        }

        public void open() {
            mDBHelper = new DBHelper(mCtx, DB_NAME, null, DB_VERSION);
            mDB = mDBHelper.getWritableDatabase();
        }

        public void close() {
            if (mDBHelper!=null) mDBHelper.close();
        }

        public Cursor getAllData() {
            return mDB.query(DB_TABLE, null, null, null, null, null, null);
        }

        public void changeRec(int id, String txt) {
            ContentValues cv = new ContentValues();
            cv.put(COLUMN_TXT, txt);
            mDB.update(DB_TABLE, cv, COLUMN_ID + " = " + id, null);
        }

        public void changeRec2(int pos, boolean isChecked) {
            ContentValues cv = new ContentValues();
            cv.put(COLUMN_CHK, (isChecked) ? 1 : 0);
            mDB.update(DB_TABLE, cv, COLUMN_ID + " = " + (pos + 1), null);
        }

        class DBHelper extends SQLiteOpenHelper {

            public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
                super(context, name, factory, version);
            }

            @Override
            public void onCreate(SQLiteDatabase db) {
                db.execSQL(DB_CREATE);
                ContentValues cv = new ContentValues();
                for (int i = 0; i < 4; i++) {
                    cv.put(COLUMN_TXT, "sometext " + i);
                    cv.put(COLUMN_CHK, 0);
                    db.insert(DB_TABLE, null, cv);
                }
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            }
        }
    }
}
