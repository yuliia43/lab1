package com.kpi.lab1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MainActivity extends Activity {

    String choice = "undefined";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button okButton = findViewById(R.id.okButton);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = "Your choice was " + choice;
                Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
            }
        });
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
