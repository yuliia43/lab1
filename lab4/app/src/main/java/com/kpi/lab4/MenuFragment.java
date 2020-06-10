package com.kpi.lab4;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.app.Fragment;

import android.os.Environment;
import android.os.storage.StorageManager;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class MenuFragment extends Fragment {
    private VideoPlayer videoPlayer;

    private static final String AUDIO = "audio";
    private static final String VIDEO = "video";
    private String songOnThePlayer = "";
    private MediaPlayer mPlayer;
    private String currentMediaType = "";
    private List<String> formats = Arrays.asList("wav", "mid", "midi", "mp3", "mp4", "avi", "wma",
            "wmv", "3gp", "mov", "flv", "asf", "gif", "mpeg", "webm");

    public MenuFragment() {
        // Required empty public constructor
    }

    public void setVideoPlayer(VideoPlayer videoPlayer) {
        this.videoPlayer = videoPlayer;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.menu_fragment, container, false);
        handlingMediaButton(view, R.id.audiosBtn, AUDIO);
        handlingMediaButton(view, R.id.videosBtn, VIDEO);
        setInternetSearchAction(view);
        Button storageReceiverBtn = view.findViewById(R.id.loadFiles);
        storageReceiverBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StorageManager storageManager = StorageManager.
            }
        });
        /*File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File[] list = externalStoragePublicDirectory.listFiles();
        for (File files: list){
            System.out.println(files);
        }*/
        return view;
    }

    private void setInternetSearchAction(final View view) {
        ImageButton submit = view.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TableLayout tbl = view.findViewById(R.id.multimediaList);
                Editable text = ((EditText) view.findViewById(R.id.insertUrl)).getText();
                String mediaUrl = text.toString();
                if (!mediaUrl.isEmpty()) {
                    String[] parts = mediaUrl.split("\\.");
                    if (parts.length != 0) {
                        String format = parts[parts.length - 1];
                        if (formats.contains(format)) {
                            String[] urlParts = mediaUrl.split("/");
                            switch (currentMediaType) {
                                case AUDIO: {
                                    text.clear();
                                    addSong(tbl, urlParts[urlParts.length - 1], mediaUrl);
                                    break;
                                }
                                case VIDEO: {
                                    text.clear();
                                    videoPlayer.playVideo(mediaUrl, urlParts[urlParts.length - 1]);
                                    currentMediaType = "";
                                    break;
                                }
                            }
                        } else {
                            Toast.makeText(getActivity(), "Incorrect URL", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getActivity(), "Incorrect URL", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "Your URL is empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void handlingMediaButton(final View view, int p, final String mediaType) {
        Button audioBtn = view.findViewById(p);
        audioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHiddenElements(view);
                getMultimedia(view, mediaType);
            }
        });
    }

    private void showHiddenElements(View view) {
        view.findViewById(R.id.infoLabel).setVisibility(View.VISIBLE);
        view.findViewById(R.id.insertUrl).setVisibility(View.VISIBLE);
        view.findViewById(R.id.submit).setVisibility(View.VISIBLE);
        view.findViewById(R.id.anotherOption).setVisibility(View.VISIBLE);
        view.findViewById(R.id.loadFiles).setVisibility(View.VISIBLE);
    }

    private void getMultimedia(View view, String type) {
        currentMediaType = type;
        TableLayout tbl = view.findViewById(R.id.multimediaList);
        tbl.removeViewsInLayout(0, tbl.getChildCount());
        switch (type) {
            case AUDIO: {
                addSong(tbl, "The neighbourhood - Reflections",
                        Integer.toString(R.raw.the_neighbourhood__reflections));
                addSong(tbl, "Twenty one pilots - Message Man",
                        Integer.toString(R.raw.twenty_one_pilots__message_man));
                break;
            }
            case VIDEO: {
                addVideo(tbl, "Winnie The Pooh (offcut)", Integer.toString(R.raw.winnie));
                break;
            }
        }
    }

    private void addVideo(TableLayout tbl, final String videoName, final String videoId) {
        Context context = getActivity();
        TableRow tableRow = new TableRow(context);
        tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        TextView videoTitle = new TextView(context);
        videoTitle.setPadding(3, 3, 3, 3);
        videoTitle.append(videoName);
        Button openVideoBtn = new Button(context);
        setButtonPadding(openVideoBtn);
        openVideoBtn.setText("Watch");
        openVideoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoPlayer.playVideo(videoId, videoName);
            }
        });
        tableRow.addView(videoTitle);
        tableRow.addView(openVideoBtn);
        tbl.addView(tableRow);

    }

    private void addSong(TableLayout tbl, String songName, final String songId) {
        Context context = getActivity();
        TableRow tableRow = new TableRow(context);
        tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        TextView songTitle = new TextView(context);
        songTitle.setPadding(3, 3, 3, 3);
        songTitle.append(songName);
        Button playBtn = createPlayButton(songId, context);
        Button stopBtn = createStopButton(songId, context);
        tableRow.addView(songTitle);
        tableRow.addView(playBtn);
        tableRow.addView(stopBtn);
        tbl.addView(tableRow);
    }

    private Button createPlayButton(final String songId, Context context) {
        Button playBtn = new Button(context);
        setButtonPadding(playBtn);
        playBtn.setText("play");
        playBtn.setId(songId.hashCode());
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((Button) v).getText().equals("play")) {
                    if (mPlayer == null || songOnThePlayer != songId) {
                        if (songOnThePlayer != "") {
                            mPlayer.release();
                            resetPlayButtonText();
                        }
                        if (TextUtils.isDigitsOnly(songId)) {
                            int id = Integer.parseInt(songId);
                            mPlayer = MediaPlayer.create(getActivity(), id);
                        } else {
                            mPlayer = MediaPlayer.create(getActivity(), Uri.parse(songId));
                        }
                        songOnThePlayer = songId;
                    }
                    mPlayer.start();
                    ((Button) v).setText("pause");
                } else {
                    mPlayer.pause();
                    ((Button) v).setText("play");
                }
            }
        });
        return playBtn;
    }

    private Button createStopButton(final String songId, Context context) {
        Button stopBtn = new Button(context);
        setButtonPadding(stopBtn);
        stopBtn.setText("stop");
        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (songOnThePlayer == songId) {
                    if (!songOnThePlayer.isEmpty() && !TextUtils.isDigitsOnly(songOnThePlayer)) {
                        mPlayer.stop();
                        songOnThePlayer = "";
                        TableLayout tbl = getView().findViewById(R.id.multimediaList);
                        tbl.removeView(((View) v.getParent()));
                    } else {
                        resetPlayButtonText();
                        mPlayer.stop();
                        songOnThePlayer = "";
                    }
                }
            }
        });
        return stopBtn;
    }

    private void setButtonPadding(Button stopBtn) {
        stopBtn.setPadding(3, 3, 3, 3);
    }

    private void resetPlayButtonText() {
        Button anotherPlayBtn = getView().findViewById(songOnThePlayer.hashCode());
        anotherPlayBtn.setText("play");
    }

}
