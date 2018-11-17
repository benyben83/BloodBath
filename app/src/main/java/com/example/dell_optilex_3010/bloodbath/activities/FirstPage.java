package com.example.dell_optilex_3010.bloodbath.activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.dell_optilex_3010.bloodbath.services.MusicPlayer;
import com.example.dell_optilex_3010.bloodbath.R;

public class FirstPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
        startService(new Intent(this, MusicPlayer.class));

    }

    public void StartNewGame(View view) {  // Proceed to new character creation
        SoundingOK();
        Intent i = new Intent(this, CreationPageOne.class);
        startActivity(i);
    }

    public void LeaveApp(View view) {  // closes the App
        SoundingNotOk();
        finish();
    }

    public void SoundingOK() {
        MediaPlayer buttonSound;
        buttonSound = MediaPlayer.create(this, R.raw.button_sound_ok);
        buttonSound.start();
    }

    public void SoundingNotOk() {
        MediaPlayer buttonSound;
        buttonSound = MediaPlayer.create(this, R.raw.button_sound_not_ok);
        buttonSound.start();
    }


}