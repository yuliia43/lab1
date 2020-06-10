package com.kpi.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements VideoPlayer {
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getFragmentManager();
        MenuFragment menuFragment = new MenuFragment();
        menuFragment.setVideoPlayer(this);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container, menuFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void playVideo(String id, String name) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentManager.popBackStack();
        VideoFragment videoFragment = new VideoFragment();
        videoFragment.setVideoId(id);
        videoFragment.setVideoName(name);
        fragmentTransaction.replace(R.id.container, videoFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
