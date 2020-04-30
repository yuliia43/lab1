package com.kpi.lab3;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResultFragment extends Fragment {
    private String result = "undefined";
    private OnHeadlineSelectedListener parent;

    public ResultFragment() {
    }

    public void setParent(OnHeadlineSelectedListener parent) {
        this.parent = parent;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_result, container, false);
        TextView textView = view.findViewById(R.id.result);
        textView.append(" " + result);
        Button submitBtn =  view.findViewById(R.id.submitButton);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getActivity()
                        .getApplicationContext()
                        .getSharedPreferences(String.valueOf(R.string.statsFile), Context.MODE_PRIVATE);
                String userName = ((EditText) view.findViewById(R.id.username)).getText().toString();
                boolean nameExists = sharedPreferences.contains(userName);
                    sharedPreferences.edit()
                            .putString(userName, result)
                            .commit();
                if (!nameExists)
                    Toast.makeText(getActivity(), "Added successfully!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getActivity(), "Updated successfully!", Toast.LENGTH_SHORT).show();
                //getFragmentManager().popBackStack();
            }
        });
        return view;
    }
}
