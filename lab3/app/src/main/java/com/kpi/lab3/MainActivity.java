package com.kpi.lab3;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements OnHeadlineSelectedListener {
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getFragmentManager();
        addQuestionFragment();
        Button statsBtn = findViewById(R.id.stats_btn);
        statsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getStats();
            }
        });
    }


    public void getStats(){
        Intent intent = new Intent(getApplicationContext(), StatsActivity.class);
        startActivity(intent);
    }

    private void addQuestionFragment(){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        QuestionFragment questionFragment = new QuestionFragment();
        questionFragment.setParent(this);
        fragmentTransaction.add(R.id.fragment_container, questionFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void handleResult(String result) {
        if (result != "undefined"){
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentManager.popBackStack();
            ResultFragment resultFragment = new ResultFragment();
            resultFragment.setResult(result);
            resultFragment.setParent(this);
            fragmentTransaction.replace(R.id.fragment_container, resultFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        else{
            Toast.makeText(this, "Nothing is chosen", Toast.LENGTH_SHORT).show();
        }
    }
}
