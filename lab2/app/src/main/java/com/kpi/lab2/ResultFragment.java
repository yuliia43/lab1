package com.kpi.lab2;

import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResultFragment extends Fragment {
    private String result = "undefined";

    public ResultFragment() {
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result, container, false);
        TextView textView = view.findViewById(R.id.result);
        textView.append(" " + result);
        return view;
    }
}
