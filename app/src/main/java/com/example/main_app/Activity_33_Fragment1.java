package com.example.main_app;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.Objects;

public class Activity_33_Fragment1 extends Fragment implements View.OnClickListener {

    Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_activity_33_1, null);
        button = v.findViewById(R.id.Act33_fr1_button);
        button.setOnClickListener(this);
        return v;
    }

    public void onClick(View v) {
        ((TextView) Objects.requireNonNull(getActivity()).findViewById(R.id.Act33_textView)).setText("Access from Fragment 1");
    }
}