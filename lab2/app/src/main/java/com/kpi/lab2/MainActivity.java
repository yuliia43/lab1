package com.kpi.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements OnHeadlineSelectedListener {
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getFragmentManager();
        addQuestionFragments();
    }

    private void addQuestionFragments(){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        addQuestionFragment(fragmentTransaction);
        fragmentTransaction.commit();
    }

    private void addQuestionFragment(FragmentTransaction fragmentTransaction){
        QuestionFragment questionFragment = new QuestionFragment();
        questionFragment.setParent(this);
        fragmentTransaction.add(R.id.fragment_container, questionFragment);

    }

    @Override
    public void handleResult(String result) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentManager.popBackStack();
        ResultFragment resultFragment = new ResultFragment();
        resultFragment.setResult(result);
        fragmentTransaction.replace(R.id.fragment_container, resultFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
