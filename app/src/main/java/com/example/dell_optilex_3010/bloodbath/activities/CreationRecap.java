package com.example.dell_optilex_3010.bloodbath.activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.dell_optilex_3010.bloodbath.charactercreation.Character;
import com.example.dell_optilex_3010.bloodbath.R;

public class CreationRecap extends AppCompatActivity {
    private Character player;
    private TextView tvName;
    private TextView tvRace;
    private TextView tvStyle;
    private TextView tvDexterity;
    private TextView tvStamina;
    private TextView tvIntelligence;
    private TextView tvKnowledge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_recap);
        player = new Character(this);
        player.loadCharacter();
        tvDexterity = findViewById(R.id.tvDexterity);
        tvIntelligence = findViewById(R.id.tvIntelligence);
        tvName = findViewById(R.id.tvName);
        tvRace = findViewById(R.id.tvRace);
        tvStyle = findViewById(R.id.tvStyle);
        tvStamina = findViewById(R.id.tvStamina);
        tvKnowledge = findViewById(R.id.tvKnowledge);
        tvKnowledge.setText("Knowledge : "+String.valueOf(player.getKnowledge()));
        tvIntelligence.setText("Intelligence : "+String.valueOf(player.getIntelligence()));
        tvStamina.setText("Stamina : "+String.valueOf(player.getStamina()));
        tvDexterity.setText("Dexterity : "+String.valueOf(player.getDexterity()));
        tvStyle.setText("Style : "+ player.getStyle1());
        tvRace.setText("Race : "+ player.getRace());
        tvName.setText("Name : "+ player.getName());

    }
    public void proceed (View view){
        soudingOk();
        player.saveCharacter();
        Intent i = new Intent(this, CreationRecap.class);
        startActivity(i);
    }
    public void  goBack(View view){
        soundingNotOk();
        Intent i = new Intent(this, CreationPageTwo.class);
        startActivity(i);
    }
    public void soudingOk(){
        MediaPlayer buttonSound;
        buttonSound = MediaPlayer.create(this, R.raw.button_sound_ok);
        buttonSound.start();
    }
    public void soundingNotOk(){
        MediaPlayer buttonSound;
        buttonSound = MediaPlayer.create(this, R.raw.button_sound_not_ok);
        buttonSound.start();
    }
}
