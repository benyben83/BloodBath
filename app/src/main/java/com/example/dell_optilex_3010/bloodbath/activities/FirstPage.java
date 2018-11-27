package com.example.dell_optilex_3010.bloodbath.activities;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.dell_optilex_3010.bloodbath.charactercreation.Character;

import com.example.dell_optilex_3010.bloodbath.services.MusicPlayer;
import com.example.dell_optilex_3010.bloodbath.R;

public class FirstPage extends AppCompatActivity {
    private Character characterOne;
    private Character characterTwo;
    private Character characterThree;
    private Button btPlay;
    private Button btQuit;
    private Button btReturn;
    private Button btFirstSave;
    private Button btSecondSave;
    private Button btThirdSave;
    private Button btNewFirstSave;
    private Button btNewSecondSave;
    private Button btNewThirdSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
        startService(new Intent(this, MusicPlayer.class));
        characterOne = new Character(this);
        characterTwo = new Character(this);
        characterThree = new Character(this);
        characterOne.loadCharacter(1);
        characterTwo.loadCharacter(2);
        characterThree.loadCharacter(3);
        btPlay = findViewById(R.id.btPlay);
        btQuit = findViewById(R.id.btQuit);
        btReturn = findViewById(R.id.btReturn);
        btFirstSave = findViewById(R.id.btFirstSave);
        btSecondSave = findViewById(R.id.btSecondSave);
        btThirdSave = findViewById(R.id.btThirdSave);
        btNewFirstSave = findViewById(R.id.btNewFirstSave);
        btNewSecondSave = findViewById(R.id.btNewSecondSave);
        btNewThirdSave = findViewById(R.id.btNewThirdSave);
        LinearLayout llBackground = (LinearLayout) findViewById(R.id.llfirstpage);
        AnimationDrawable animationDrawable = (AnimationDrawable) llBackground.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();

    }

    public void play(View view) {  // Proceed to new character creation
        soundingOK();
        btPlay.setVisibility(View.GONE);
        btQuit.setVisibility(View.GONE);
        btFirstSave.setVisibility(View.VISIBLE);
        btNewFirstSave.setVisibility(View.VISIBLE);
        btFirstSave.setText(characterOne.getName() + " " + characterOne.getRace());
        btSecondSave.setVisibility(View.VISIBLE);
        btNewSecondSave.setVisibility(View.VISIBLE);
        btSecondSave.setText(characterTwo.getName() + " " + characterTwo.getRace());
        btThirdSave.setVisibility(View.VISIBLE);
        btNewThirdSave.setVisibility(View.VISIBLE);
        btThirdSave.setText(characterThree.getName() + " " + characterThree.getRace());
        btReturn.setVisibility(View.VISIBLE);

    }

    public void leaveApp(View view) {  // closes the App
        soundingNotOk();
        finish();
    }

    public void returnToPrevious(View view) {
        btPlay.setVisibility(View.VISIBLE);
        btQuit.setVisibility(View.VISIBLE);
        btFirstSave.setVisibility(View.GONE);
        btNewFirstSave.setVisibility(View.GONE);
        btSecondSave.setVisibility(View.GONE);
        btNewSecondSave.setVisibility(View.GONE);
        btThirdSave.setVisibility(View.GONE);
        btNewThirdSave.setVisibility(View.GONE);
        btReturn.setVisibility(View.GONE);
    }

    public void soundingOK() {
        MediaPlayer buttonSound;
        buttonSound = MediaPlayer.create(this, R.raw.button_sound_ok);
        buttonSound.start();
    }

    public void soundingNotOk() {
        MediaPlayer buttonSound;
        buttonSound = MediaPlayer.create(this, R.raw.button_sound_not_ok);
        buttonSound.start();
    }

    public void loadGameOne(View view){
        Intent i = new Intent(this, Arena.class);
        i.putExtra("CharacterID", 1);
        startActivity(i);
    }
    public void loadGameTwo(View view){
        Intent i = new Intent(this, Arena.class);
        i.putExtra("CharacterID", 2);
        startActivity(i);
    }
    public void loadGameThree(View view){
        Intent i = new Intent(this, Arena.class);
        i.putExtra("CharacterID", 3);
        startActivity(i);
    }
    public void newGameOne (View view){
        Intent i = new Intent(this, NewCharacter.class);
        i.putExtra("CharacterID", 1);
        startActivity(i);
    }
    public void newGameTwo (View view){
        Intent i = new Intent(this, NewCharacter.class);
        i.putExtra("CharacterID", 2);
        startActivity(i);
    }
    public void newGameThree (View view){
        Intent i = new Intent(this, NewCharacter.class);
        i.putExtra("CharacterID", 3);
        startActivity(i);
    }


}