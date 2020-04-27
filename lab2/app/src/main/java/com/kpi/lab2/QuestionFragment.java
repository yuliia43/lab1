package com.kpi.lab2;

import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuestionFragment extends Fragment {
    private OnHeadlineSelectedListener parent;

    private String choice = "undefined";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question, container, false);
        Button okButton = view.findViewById(R.id.okButton);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parent.handleResult(choice);
            }
        });
        RadioGroup group = view.findViewById(R.id.radioGroup);
        int count = group.getChildCount();
        for (int i = 0; i < count; i++){
            RadioButton btn = (RadioButton) group.getChildAt(i);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onRadioButtonClicked(v);
                }
            });
        }
        return view;
    }

    public void setParent(OnHeadlineSelectedListener parent) {
        this.parent = parent;
    }


    public void onRadioButtonClicked(View view) {
        switch (view.getId()) {
            case R.id.another: {
                choice = "another language";
                break;
            }
            case R.id.c: {
                choice = "C";
                break;
            }
            case R.id.c_plus_plus: {
                choice = "C++";
                break;
            }
            case R.id.c_sharp: {
                choice = "C#";
                break;
            }
            case R.id.java: {
                choice = "Java";
                break;
            }
            case R.id.prolog: {
                choice = "Prolog";
                break;
            }
            case R.id.python: {
                choice = "Python";
                break;
            }
            default:
                choice = "undefined";
        }
    }
}
