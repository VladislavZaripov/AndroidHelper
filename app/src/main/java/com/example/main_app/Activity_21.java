package com.example.main_app;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Activity_21 extends AppCompatActivity {

    EditText editText1, editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_21);
        editText1 = findViewById(R.id.Act21_editText1);
        editText2 = findViewById(R.id.Act21_editText2);
    }

    public void onclick(View v) {
        MyObject myObj = new MyObject(editText1.getText().toString(), Integer.parseInt(editText2.getText().toString()));
        Intent intent = new Intent(this, Activity_21a.class);
        intent.putExtra(MyObject.class.getCanonicalName(), myObj);
        startActivity(intent);
    }
}

class MyObject implements Parcelable {

    public String s;
    public int i;

    public MyObject(String s, int i) {
        this.s = s;
        this.i = i;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(s);
        dest.writeInt(i);
    }

    public static final Parcelable.Creator<MyObject> CREATOR = new Parcelable.Creator<MyObject>(){
        @Override
        public MyObject createFromParcel(Parcel source) {
            return new MyObject(source.readString(), source.readInt());
        }
        @Override
        public MyObject[] newArray(int size) {
            return new MyObject[size];
        }
    };
}