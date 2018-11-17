package com.example.dell_optilex_3010.bloodbath.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import com.example.dell_optilex_3010.bloodbath.R;

public class MusicPlayer extends Service {

    private MediaPlayer introMusicPlayer;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        introMusicPlayer = MediaPlayer.create(this, R.raw.intro);
        introMusicPlayer.setLooping(true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        introMusicPlayer.stop();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        introMusicPlayer.start();
    }
}

