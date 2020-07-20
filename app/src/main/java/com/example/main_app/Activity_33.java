package com.example.main_app;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import java.util.Objects;

public class Activity_33 extends FragmentActivity {

    Activity_33_Fragment1 frag1;
    Activity_33_Fragment2 frag2;
    FragmentTransaction fTrans;
    CheckBox checkBox;

    int fragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_33);

        frag1 = new Activity_33_Fragment1();
        frag2 = new Activity_33_Fragment2();
        checkBox = findViewById(R.id.Act33_checkBox);
    }

    public void onClick(View v) {
        fTrans = getSupportFragmentManager().beginTransaction();
        switch (v.getId()) {
            case R.id.Act33_btnAdd:
                if (fragment==0){
                    fTrans.add(R.id.Act33_frgmCont, frag1);
                    fragment = 1;}
                if (fragment==2){
                    fTrans.remove(frag2);
                    fTrans.add(R.id.Act33_frgmCont, frag1);
                    fragment = 1;}
                break;
            case R.id.Act33_btnRemove:
                if (fragment == 1)
                    fTrans.remove(frag1);
                if (fragment == 2)
                    fTrans.remove(frag2);
                fragment = 0;
                break;
            case R.id.Act33_btnReplace:
                fTrans.replace(R.id.Act33_frgmCont, frag2);
                fragment = 2;
                break;
            case R.id.Act33_btnSendMess1:
                try {
                    ((TextView) Objects.requireNonNull(frag1.getView()).findViewById(R.id.Act33_fr1_text)).setText("Access to Fragment 1 from Activity");
                }
                catch (Exception e)
                {
                    Toast.makeText(this,"You must set Fragment 1",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.Act33_btnSendMess2:
                try {
                ((TextView) Objects.requireNonNull(frag2.getView()).findViewById(R.id.Act33_fr2_text)).setText("Access to Fragment 1 from Activity");
                }
                catch (Exception e)
                {
                    Toast.makeText(this,"You must set Fragment 2",Toast.LENGTH_LONG).show();
                }
                break;
        }
        if (checkBox.isChecked()) fTrans.addToBackStack(null);
        fTrans.commit();
    }
}
