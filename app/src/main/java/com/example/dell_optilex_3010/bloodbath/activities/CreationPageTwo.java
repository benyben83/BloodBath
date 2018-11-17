package com.example.dell_optilex_3010.bloodbath.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell_optilex_3010.bloodbath.charactercreation.Character;
import com.example.dell_optilex_3010.bloodbath.R;
import com.example.dell_optilex_3010.bloodbath.charactercreation.Race;

public class CreationPageTwo extends AppCompatActivity {
    private Character player;
    private  TextView tvDisplayRace;
    private  TextView tvDisplayName;
    private  TextView tvDisplayDexterity;
    private  TextView tvDisplayStamina;
    private  TextView tvDisplayIntelligence;
    private  TextView tvDisplayKnowledge;
    private  TextView tvDisplayStyle;
    private  Race activeRace ;  // used when switching races in order to reinitialize stats between changes
    private  Race human = new Race("Human", 0, 0, 0, 0);  //list of available races
    private  Race elf = new Race("Elf", 1, -1, 0, -1);
    private  Race gnome = new Race("Gnome", 0, -1, 1, -1);
    private Race halfling = new Race("Halfling", -1, 0, -1, 1);
    private  Race troll = new Race("Troll", -2, 2, -2, 0);
    private  Race fairy = new Race("Fairy", 0, 4, 2, 0);
    private  Race tentacular = new Race("Tentacular", -2, 0, -2, 2);
    private  Race dwarf = new Race("Dwarf", -1, 1, -1, 0);
    private  Race orc = new Race("Orc", 2, 0, 0, 4);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_page_two);
        player = new Character(this);   //needed to store character data from database

        activeRace = human;
        tvDisplayName = findViewById(R.id.tvDisplayName);
        tvDisplayRace = findViewById(R.id.tvDisplayRace);
        tvDisplayDexterity = findViewById(R.id.tvDisplayDexterity);
        tvDisplayStamina = findViewById(R.id.tvDisplayStamina);
        tvDisplayIntelligence = findViewById(R.id.tvDisplayIntelligence);
        tvDisplayKnowledge = findViewById(R.id.tvDisplayKnowledge);
        tvDisplayStyle = findViewById(R.id.tvDisplayStyle);

        try {
            player.loadCharacter();      //retrieving character data from database
            player.setRace("Human");       // default setting, to prevent crash
            tvDisplayName.setText("Name : " + player.getName());  // synchronizing stats display with character data
            tvDisplayRace.setText("Race : " + player.getRace());
            tvDisplayDexterity.setText("Dexterity : " + String.valueOf(player.getDexterity()));
            tvDisplayStamina.setText("Stamina : " + String.valueOf(player.getStamina()));
            tvDisplayIntelligence.setText("Intelligence : " + String.valueOf(player.getIntelligence()));
            tvDisplayKnowledge.setText("Knowledge : " + String.valueOf(player.getKnowledge()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void applyRace(Race race) {
        activeRace = race;// storing racial choice
        player.setRace(race.getName());  // updating character stats
        player.setDexterity(player.getDexterity() + race.getDexterity());
        player.setStamina(player.getStamina() + race.getStamina());
        player.setIntelligence(player.getIntelligence() + race.getIntelligence());
        player.setKnowledge(player.getKnowledge() + race.getKnowledge());
        tvDisplayRace.setText("Race : " + race.getName());  // synchronizing stats display with character data
        tvDisplayDexterity.setText("Dexterity : " + String.valueOf(player.getDexterity()));
        tvDisplayStamina.setText("Stamina : " + String.valueOf(player.getStamina()));
        tvDisplayIntelligence.setText("Intelligence : " + String.valueOf(player.getIntelligence()));
        tvDisplayKnowledge.setText("Knowledge : " + String.valueOf(player.getKnowledge()));

    }

    public void undoRace(Race race) { // needed so that racial choice doesn't stack indefinetely with itself
        if (!race.getName().equals("Human")) {

            player.setDexterity(player.getDexterity() - race.getDexterity()); // updating character stats
            player.setStamina(player.getStamina() - race.getStamina());
            player.setIntelligence(player.getIntelligence() - race.getIntelligence());
            player.setKnowledge(player.getKnowledge() - race.getKnowledge());
        }
    }

    public void chooseRace(View view) {
        undoRace(activeRace);
        soundingOk();
        switch (view.getId()) {
            case R.id.btHuman:
                applyRace(human);
                break;
            case R.id.btElf:
                applyRace(elf);
                break;
            case R.id.btDwarf:
                applyRace(dwarf);
                break;
            case R.id.btGnome:
                applyRace(gnome);
                break;
            case R.id.btHalfling:
                applyRace(halfling);
                break;
            case R.id.btOrc:
                applyRace(orc);
                break;
            case R.id.btFairy:
                applyRace(fairy);
                break;
            case R.id.btTroll:
                applyRace(troll);
                break;
            case R.id.btTentacular:
                applyRace(tentacular);
                break;
        }
    }

    public void chooseStyle(View view) {
        soundingOk();
        switch (view.getId()) {
            case R.id.btPower:
                player.setStyle1("Power");
                tvDisplayStyle.setText("Style : " + player.getStyle1());
                break;
            case R.id.btTechnics:
                player.setStyle1("Technics");
                tvDisplayStyle.setText("Style : " + player.getStyle1());
                break;
            case R.id.btLooks:
                player.setStyle1("Looks");
                tvDisplayStyle.setText("Style : " + player.getStyle1());
                break;
            case R.id.btMagic:
                player.setStyle1("Magic");
                tvDisplayStyle.setText("Style : " + player.getStyle1());
                break;
            case R.id.btScience:
                player.setStyle1("Science");
                tvDisplayStyle.setText("Style : " + player.getStyle1());
                break;
        }
    }

      public void proceed(View view) {
          soundingOk();
          player.saveCharacter();
          Intent i = new Intent(this, CreationRecap.class);
          startActivity(i);
      }

    public void goBack(View view) {
        soundingNotOk();
        Intent i = new Intent(this, CreationPageOne.class);
        startActivity(i);
    }

    public void soundingOk() {
        MediaPlayer buttonsound;
        buttonsound = MediaPlayer.create(this, R.raw.button_sound_ok);
        buttonsound.start();
    }

    public void soundingNotOk() {
        MediaPlayer buttonSound;
        buttonSound = MediaPlayer.create(this, R.raw.button_sound_not_ok);
        buttonSound.start();
    }

    public void tutorial(View view) {  // provides hints on game mechanics via toast
        Context context = getApplicationContext();
        CharSequence text = "Styles define the panel of available actions to learn while a character levels up. Each style is more effective towards two other styles and vulnerable to the two styles remaining";
        int duration = Toast.LENGTH_LONG;
        Toast tutoTwo = Toast.makeText(context, text, duration);
        tutoTwo.setGravity(Gravity.TOP | Gravity.LEFT, 0, 0);
        tutoTwo.getView().setBackgroundColor(Color.RED);
        tutoTwo.show();
    }
}