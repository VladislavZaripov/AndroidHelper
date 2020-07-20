package com.example.main_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Activity_8 extends AppCompatActivity implements View.OnClickListener {

    DB db;
    Cursor cursor;

    TextView textViewTable1Column1,textViewTable1Column2,textViewTable1Column3;
    TextView textViewTable2Column1,textViewTable2Column2,textViewTable2Column3;
    TextView textViewTable3Column1,textViewTable3Column2,textViewTable3Column3,textViewTable3Column4, textViewTable3Column5;

    EditText editTextIdTable1Column1, editTextIdTable1Column2, editTextIdTable1Column3;
    Button addButton,updateButton,deleteButton,deleteTableButton,createTableButton;

    RadioGroup rgSort;
    RadioButton rSortId, rSortName, rSortPosition, rSortSalary;
    RadioGroup rgGroup;
    RadioButton rGroupId, rGroupName, rGroupPosition, rGroupSalary;
    EditText editTextSelectionTable3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_8);

        db = new DB(this);
        db.open();

        textViewTable1Column1 = findViewById(R.id.Act8_textView1);
        textViewTable1Column2 = findViewById(R.id.Act8_textView2);
        textViewTable1Column3 = findViewById(R.id.Act8_textView3);
        db.refreshTextViewTable1();

        editTextIdTable1Column1 = findViewById(R.id.Act8_EditText1);
        editTextIdTable1Column2 = findViewById(R.id.Act8_EditText2);
        editTextIdTable1Column3 = findViewById(R.id.Act8_EditText3);

        addButton = findViewById(R.id.Act8_button1);
        deleteButton = findViewById(R.id.Act8_button2);
        updateButton = findViewById(R.id.Act8_button3);
        deleteTableButton = findViewById(R.id.Act8_button4);
        createTableButton = findViewById(R.id.Act8_button5);
        addButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);
        updateButton.setOnClickListener(this);
        deleteTableButton.setOnClickListener(this);
        createTableButton.setOnClickListener(this);

        textViewTable2Column1 = findViewById(R.id.Act8_textView4);
        textViewTable2Column2 = findViewById(R.id.Act8_textView5);
        textViewTable2Column3 = findViewById(R.id.Act8_textView6);
        db.refreshTextViewTable2();

        textViewTable3Column1 = findViewById(R.id.Act8_textView7);
        textViewTable3Column2 = findViewById(R.id.Act8_textView8);
        textViewTable3Column3 = findViewById(R.id.Act8_textView9);
        textViewTable3Column4 = findViewById(R.id.Act8_textView10);
        textViewTable3Column5 =findViewById(R.id.Act8_textView11);

        rgSort = findViewById(R.id.Act8_rgSort);

        rSortId = findViewById(R.id.Act8_rSortId);
        rSortName = findViewById(R.id.Act8_rSortName);
        rSortPosition = findViewById(R.id.Act8_rSortPosition);
        rSortSalary = findViewById(R.id.Act8_rSortSalary);
        rSortId.setOnClickListener(this);
        rSortName.setOnClickListener(this);
        rSortPosition.setOnClickListener(this);
        rSortSalary.setOnClickListener(this);

        rgGroup = findViewById(R.id.Act8_rgGroup);

        rGroupId = findViewById(R.id.Act8_rGroupId);
        rGroupName = findViewById(R.id.Act8_rGroupName);
        rGroupPosition = findViewById(R.id.Act8_rGroupPosition);
        rGroupSalary = findViewById(R.id.Act8_rGroupSalary);
        rGroupId.setOnClickListener(this);
        rGroupName.setOnClickListener(this);
        rGroupPosition.setOnClickListener(this);
        rGroupSalary.setOnClickListener(this);


        editTextSelectionTable3 = findViewById(R.id.Act8_EditText4);
        editTextSelectionTable3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                db.fillTable3();
            }
        });

        db.fillTable3();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Act8_button1:
                db.addPositionInTable1();
                db.refreshTextViewTable1();
                db.fillTable3();
                break;

            case R.id.Act8_button2:
                db.deletePositionInTable1();
                db.refreshTextViewTable1();
                db.fillTable3();
                break;

            case R.id.Act8_button3:
                db.updatePositionInTable1();
                db.refreshTextViewTable1();
                db.fillTable3();
                break;
            case R.id.Act8_button4:
                db.clearTable1();
                db.refreshTextViewTable1();
                db.fillTable3();
                break;
            case R.id.Act8_button5:
                db.clearTable1();
                db.fillTable1();
                db.refreshTextViewTable1();
                db.fillTable3();
                break;
            case R.id.Act8_rSortId:
            case R.id.Act8_rSortName:
            case R.id.Act8_rSortPosition:
            case R.id.Act8_rSortSalary:
            case R.id.Act8_rGroupId:
            case R.id.Act8_rGroupName:
            case R.id.Act8_rGroupPosition:
            case R.id.Act8_rGroupSalary:
                db.fillTable3();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }

    class DB {
        private static final String DB_NAME = "DataBase8";
        private static final int DB_VERSION = 1;

        private static final String TABLE1_NAME = "People";
        public static final String TABLE1_COLUMN_ID = "People_id";
        public static final String TABLE1_COLUMN_NAME = "People_name";
        public static final String TABLE1_COLUMN_POSITION_ID = "People_position_id";
        String[] people_name = {"Максим", "Сергей", "Руслан", "Наталья", "Иван", "Мария", "Светлана", "Григорий"};
        int[] people_position_id = {2, 3, 2, 2, 3, 1, 2, 4};

        public static final String TABLE1_CREATE = "create table " + TABLE1_NAME +
                "(" +
                TABLE1_COLUMN_ID + " integer primary key, " +
                TABLE1_COLUMN_NAME + " text, " +
                TABLE1_COLUMN_POSITION_ID + " integer" +
                ");";

        private static final String TABLE2_NAME = "Position";
        public static final String TABLE2_COLUMN_ID = "Position_id";
        public static final String TABLE2_COLUMN_NAME = "Position_name";
        public static final String TABLE2_COLUMN_SALARY = "Position_salary";
        public int[] position_id = {1, 2, 3, 4};
        public String[] position_name = {"Директор", "Программист", "Бухгалтер", "Охранник"};
        public int[] position_salary = {90000, 60000, 40000, 20000};

        public static final String TABLE2_CREATE = "create table " + TABLE2_NAME +
                "(" +
                TABLE2_COLUMN_ID + " integer primary key, " +
                TABLE2_COLUMN_NAME + " text, " +
                TABLE2_COLUMN_SALARY + " integer" +
                ");";

        private final Context context;
        private DBHelper dbHelper;
        private SQLiteDatabase db;
        ContentValues cv;

        public DB(Context context) {
            this.context = context;
        }

        public void open() {
            dbHelper = new DBHelper(context, DB_NAME, null, DB_VERSION);
            db = dbHelper.getWritableDatabase();
        }

        public void close() {
            if (dbHelper != null) dbHelper.close();
        }

        public void refreshTextViewTable1() {
            cursor = db.query(TABLE1_NAME, null, null, null, null, null, null);
            String string1 = "";
            String string2 = "";
            String string3 = "";

            if (cursor.moveToFirst()) {
                int id_index = cursor.getColumnIndex(DB.TABLE1_COLUMN_ID);
                int name_index = cursor.getColumnIndex(DB.TABLE1_COLUMN_NAME);
                int position_id_index = cursor.getColumnIndex(DB.TABLE1_COLUMN_POSITION_ID);
                do {
                    string1 = string1.concat(cursor.getInt(id_index) + "\n");
                    string2 = string2.concat(cursor.getString(name_index)+ "\n");
                    string3 = string3.concat(cursor.getString(position_id_index) + "\n");
                } while (cursor.moveToNext());
            } else string1 = string2 = string3 =  "Empty";

            textViewTable1Column1.setText(string1.split("\\n+$", 2)[0] + "");
            textViewTable1Column2.setText(string2.split("\\n+$", 2)[0] + "");
            textViewTable1Column3.setText(string3.split("\\n+$", 2)[0] + "");
        }

        public void refreshTextViewTable2() {
            cursor = db.query(TABLE2_NAME, null, null, null, null, null, null);
            String string1 = "";
            String string2 = "";
            String string3 = "";

            if (cursor.moveToFirst()) {
                int id_index = cursor.getColumnIndex(DB.TABLE2_COLUMN_ID);
                int name_index = cursor.getColumnIndex(DB.TABLE2_COLUMN_NAME);
                int salary_index = cursor.getColumnIndex(DB.TABLE2_COLUMN_SALARY);
                do {
                    string1 = string1.concat(cursor.getInt(id_index) + "\n");
                    string2 = string2.concat(cursor.getString(name_index)+ "\n");
                    string3 = string3.concat(cursor.getString(salary_index) + "\n");
                } while (cursor.moveToNext());
            } else string1 = string2 = string3 =  "Empty";

            textViewTable2Column1.setText(string1.split("\\n+$", 2)[0] + "");
            textViewTable2Column2.setText(string2.split("\\n+$", 2)[0] + "");
            textViewTable2Column3.setText(string3.split("\\n+$", 2)[0] + "");
        }

        public void addPositionInTable1() {
            cv = new ContentValues();
            cv.put(TABLE1_COLUMN_ID, editTextIdTable1Column1.getText().toString());
            cv.put(TABLE1_COLUMN_NAME,editTextIdTable1Column2.getText().toString());
            cv.put(TABLE1_COLUMN_POSITION_ID,editTextIdTable1Column3.getText().toString());
            db.insert(TABLE1_NAME, null,cv);
        }

        public void deletePositionInTable1() {
            try {
                Integer.parseInt(editTextIdTable1Column1.getText().toString());
                db.delete(TABLE1_NAME,TABLE1_COLUMN_ID + "= " + editTextIdTable1Column1.getText().toString(), null);
            }
            catch (Exception e){
                Toast.makeText(Activity_8.this,"Id is not integer",Toast.LENGTH_SHORT).show();
            }
        }

        public void updatePositionInTable1() {
            cv = new ContentValues();
            cv.put(TABLE1_COLUMN_NAME,editTextIdTable1Column2.getText().toString());
            cv.put(TABLE1_COLUMN_POSITION_ID,editTextIdTable1Column3.getText().toString());
            db.update(TABLE1_NAME,cv,TABLE1_COLUMN_ID + "= ?", new String[] {editTextIdTable1Column1.getText().toString()});
        }

        public void clearTable1() {
            db.delete(TABLE1_NAME,null,null);}

        public void fillTable1() {
            cv = new ContentValues();
            db.beginTransaction();
            try {
                for (int i = 0; i < people_name.length; i++) {
                    cv.clear();
                    cv.put(TABLE1_COLUMN_ID, i+1);
                    cv.put(TABLE1_COLUMN_NAME, people_name[i]);
                    cv.put(TABLE1_COLUMN_POSITION_ID, people_position_id[i]);
                    db.insert(TABLE1_NAME, null, cv);
                }
                db.setTransactionSuccessful();
            } finally {
                db.endTransaction();
            }
        }

        public void fillTable3() {
            String table = TABLE1_NAME+ " as PL left outer join " + TABLE2_NAME + " as PS on PL."+ TABLE1_COLUMN_POSITION_ID+ " = PS."+ TABLE2_COLUMN_ID;
            String [] columns = {"PL." + TABLE1_COLUMN_ID + " as Id", "PL." + TABLE1_COLUMN_NAME + " as Name", "PS." + TABLE2_COLUMN_NAME + " as Position", "PS." + TABLE2_COLUMN_SALARY + " as Salary","count(" + TABLE1_COLUMN_ID + ") as Count"};

            cursor = db.query(table, columns, "Salary > ?", new String[] {editTextSelectionTable3.getText().toString()}, groupByTable3(),null, orderByTable3());

            String string1 = "";
            String string2 = "";
            String string3 = "";
            String string4 = "";
            String string5 = "";

            if (cursor.moveToFirst()) {
                int id_index = cursor.getColumnIndex("Id");
                int name_index = cursor.getColumnIndex("Name");
                int position_index = cursor.getColumnIndex("Position");
                int salary_index = cursor.getColumnIndex("Salary");
                int count_index = cursor.getColumnIndex("Count");

                do {
                    string1 = string1.concat(cursor.getInt(id_index) + "\n");
                    string2 = string2.concat(cursor.getString(name_index)+ "\n");
                    string3 = string3.concat(cursor.getString(position_index) + "\n");
                    string4 = string4.concat(cursor.getInt(salary_index) + "\n");
                    string5 = string5.concat(cursor.getInt(count_index) + "\n");
                } while (cursor.moveToNext());
            } else string1 = string2 = string3 = string4 = string5= "Empty";

            textViewTable3Column1.setText(string1.split("\\n+$", 2)[0] + "");
            textViewTable3Column2.setText(string2.split("\\n+$", 2)[0] + "");
            textViewTable3Column3.setText(string3.split("\\n+$", 2)[0] + "");
            textViewTable3Column4.setText(string4.split("\\n+$", 2)[0] + "");
            textViewTable3Column5.setText(string5.split("\\n+$", 2)[0] + "");
        }

        public String orderByTable3() {
            String orderBy = "Name";

            switch (rgSort.getCheckedRadioButtonId()){
                case R.id.Act8_rSortId:
                    orderBy = "Id";
                    return orderBy;
                case R.id.Act8_rSortName:
                    orderBy = "Name";
                    return orderBy;
                case R.id.Act8_rSortPosition:
                    orderBy = "Position";
                    return orderBy;
                case R.id.Act8_rSortSalary:
                    orderBy = "Salary";
                    return orderBy;
            }
            return orderBy;
        }

        public String groupByTable3() {
            String groupBy;
            switch (rgGroup.getCheckedRadioButtonId()){
                default:
                case R.id.Act8_rGroupId:
                    groupBy = "Id";
                    return groupBy;
                case R.id.Act8_rGroupName:
                    groupBy = "Name";
                    return groupBy;
                case R.id.Act8_rGroupPosition:
                    groupBy = "Position";
                    return groupBy;
                case R.id.Act8_rGroupSalary:
                    groupBy = "Salary";
                    return groupBy;
            }
        }

        class DBHelper extends SQLiteOpenHelper {

            public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
                super(context, name, factory, version);
            }

            @Override
            public void onCreate(SQLiteDatabase db) {
                cv = new ContentValues();

                db.execSQL(TABLE1_CREATE);
                    for (int i = 0; i < people_name.length; i++) {
                        cv.clear();
                        cv.put(TABLE1_COLUMN_ID, i);
                        cv.put(TABLE1_COLUMN_NAME, people_name[i]);
                        cv.put(TABLE1_COLUMN_POSITION_ID, people_position_id[i]);
                        db.insert(TABLE1_NAME, null, cv);
                    }

                db.execSQL(TABLE2_CREATE);
                    for (int i = 0; i < position_id.length; i++) {
                        cv.clear();
                        cv.put(TABLE2_COLUMN_ID, position_id[i]);
                        cv.put(TABLE2_COLUMN_NAME, position_name[i]);
                        cv.put(TABLE2_COLUMN_SALARY, position_salary[i]);
                        db.insert(TABLE2_NAME, null, cv);
                    }
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                db.execSQL("drop table if exists " + TABLE1_NAME);
                db.execSQL("drop table if exists " + TABLE2_NAME);
                onCreate(db);
            }
        }
    }
}