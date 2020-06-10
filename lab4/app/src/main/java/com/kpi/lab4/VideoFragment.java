package com.kpi.lab4;

import android.net.Uri;
import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends Fragment {
    private String videoId;
    private String videoName = "";
    private VideoView videoView;

    public VideoFragment() {
        // Required empty public constructor
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_video, container, false);
        TextView titleLabel = view.findViewById(R.id.videoTitle);
        titleLabel.append(videoName);
        tuneVideoView(view);
        tuneStartPauseButton(view);
        tuneStopButton(view);
        return view;
    }

    private void tuneStopButton(final View view) {
        Button stopBtn = view.findViewById(R.id.stopBtn);
        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.stopPlayback();
                videoView.resume();
                ((Button)view.findViewById(R.id.startBtn)).setText("start");
            }
        });
    }

    private void tuneStartPauseButton(View view) {
        Button startBtn = view.findViewById(R.id.startBtn);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = ((Button)v).getText().toString();
                if(text.equals("start") || text.equals("continue")){
                    ((Button) v).setText("pause");
                    videoView.start();
                }
                else {
                    ((Button) v).setText("continue");
                    videoView.pause();
                }
            }
        });
    }

    private void tuneVideoView(View view) {
        videoView = view.findViewById(R.id.videoPlayer);
        Uri videoUri;
        if(android.text.TextUtils.isDigitsOnly(videoId))
            videoUri = Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + videoId);
        else
            videoUri = Uri.parse(videoId);
        videoView.setVideoURI(videoUri);
        MediaController mediaController = new MediaController(getActivity());
        videoView.setMediaController(mediaController);
        mediaController.setMediaPlayer(videoView);
    }
}
