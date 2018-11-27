package com.example.dell_optilex_3010.bloodbath.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell_optilex_3010.bloodbath.charactercreation.Character;
import com.example.dell_optilex_3010.bloodbath.R;
import com.example.dell_optilex_3010.bloodbath.charactercreation.Race;

import static java.lang.String.valueOf;

public class NewCharacter extends AppCompatActivity {
    private TextView tvDexterityScore;
    private TextView tvStaminaScore;
    private TextView tvIntelligenceScore;
    private TextView tvKnowledgeScore;
    private TextView tvRemainingPoints;
    private Character player;
    private TextView tvRace;
    private EditText etName;
    private LinearLayout llCreationOne;
    private ScrollView svCreationTwo;
    private LinearLayout llCreationThree;
    private Button btFirstNext;
    private Button btSecondNext;
    private Button btThirdNext;
    private TextView tvStyle;
    private Race activeRace;  // used when switching races in order to reinitialize stats between changes
    private Race human = new Race("Human", 0, 0, 0, 0);  //list of available races
    private Race elf = new Race("Elf", 1, -1, 0, -1);
    private Race gnome = new Race("Gnome", 0, -1, 1, -1);
    private Race halfling = new Race("Halfling", -1, 0, -1, 1);
    private Race troll = new Race("Troll", -2, 2, -2, 0);
    private Race fairy = new Race("Fairy", 0, -4, 2, 0);
    private Race tentacular = new Race("Tentacular", -2, 0, -2, 2);
    private Race dwarf = new Race("Dwarf", -1, 1, -1, 0);
    private Race orc = new Race("Orc", 2, 0, 0, -4);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_character);
        player = new Character(this);               // create a character used to store character creation data
        tvDexterityScore = findViewById(R.id.tvDexterity);
        tvStaminaScore = findViewById(R.id.tvStamina);
        tvIntelligenceScore = findViewById(R.id.tvIntelligence);
        tvKnowledgeScore = findViewById(R.id.tvKnowledge);
        tvRemainingPoints = findViewById(R.id.tvRemainingPoints);
        etName = findViewById(R.id.etName);
        tvRace = findViewById(R.id.tvRace);
        tvStyle = findViewById(R.id.tvStyle);
        activeRace = human;
        Bundle extras = getIntent().getExtras();  // retrieving extra in order to manage several different saves
        int savedInstance = extras.getInt("CharacterID");
        player.setId(savedInstance);
        btFirstNext = findViewById(R.id.btFirstNext);
        btSecondNext = findViewById(R.id.btSecondNext);
        btThirdNext = findViewById(R.id.btThirdNext);
        llCreationOne = findViewById(R.id.llCreationOne);
        svCreationTwo = findViewById(R.id.svCreationTwo);
        llCreationThree=findViewById(R.id.llCreationThree);
        LinearLayout llBackground = (LinearLayout) findViewById(R.id.llCreation);
        AnimationDrawable animationDrawable = (AnimationDrawable) llBackground.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();
    }

    public void increaseScore(int score, TextView display, int remainingPoints, TextView displayRemainingPoints) {  // synchronizing character stats with TextView display
        score++;
        display.setText(valueOf(score));
        remainingPoints--;
        displayRemainingPoints.setText(valueOf(remainingPoints));
    }

    public void decreaseScore(int score, TextView display, int remainingPoints, TextView displayRemainingPoints) {   // synchronizing character stats with TextView display
        score--;
        display.setText(valueOf(score));
        remainingPoints++;
        displayRemainingPoints.setText(valueOf(remainingPoints));
    }

    public void increaseStat(View view) {  //  increasing player stats on "+" button pressed
        int intDexterity = Integer.parseInt(tvDexterityScore.getText().toString());
        int intStamina = Integer.parseInt(tvStaminaScore.getText().toString());
        int intIntelligence = Integer.parseInt(tvIntelligenceScore.getText().toString());
        int intKnowledge = Integer.parseInt(tvKnowledgeScore.getText().toString());
        int remainingPoints = Integer.parseInt(tvRemainingPoints.getText().toString());
        if (remainingPoints > 0) {
            switch (view.getId()) {
                case R.id.btIncreaseDexterity:
                    increaseScore(intDexterity, tvDexterityScore, remainingPoints, tvRemainingPoints);
                    soundingOk();
                    break;
                case R.id.btIncreaseStamina:
                    increaseScore(intStamina, tvStaminaScore, remainingPoints, tvRemainingPoints);
                    soundingOk();
                    break;
                case R.id.btIncreaseIntelligence:
                    increaseScore(intIntelligence, tvIntelligenceScore, remainingPoints, tvRemainingPoints);
                    soundingOk();
                    break;
                case R.id.btIncreaseKnowledge:
                    increaseScore(intKnowledge, tvKnowledgeScore, remainingPoints, tvRemainingPoints);
                    soundingOk();
                    break;
            }
        }
    }

    public void decreaseStat(View view) { //  decreasing player stats on "-" button pressed
        int intDexterity = Integer.parseInt(tvDexterityScore.getText().toString());
        int intStamina = Integer.parseInt(tvStaminaScore.getText().toString());
        int intIntelligence = Integer.parseInt(tvIntelligenceScore.getText().toString());
        int intKnowledge = Integer.parseInt(tvKnowledgeScore.getText().toString());
        int intRemainingPoints = Integer.parseInt(tvRemainingPoints.getText().toString());
        if (intRemainingPoints <= 5) {
            switch (view.getId()) {
                case R.id.btDecreaseDexterity:
                    if (intDexterity >= 1) {
                        decreaseScore(intDexterity, tvDexterityScore, intRemainingPoints, tvRemainingPoints);
                        soundingOk();
                    } else {
                        soundingNotOk();
                    }
                    break;
                case R.id.btDecreaseStamina:
                    if (intStamina >= 1) {
                        decreaseScore(intStamina, tvStaminaScore, intRemainingPoints, tvRemainingPoints);
                        soundingOk();
                    } else {
                        soundingNotOk();
                    }
                    break;
                case R.id.btDecreaseIntelligence:
                    if (intIntelligence >= 1) {
                        decreaseScore(intIntelligence, tvIntelligenceScore, intRemainingPoints, tvRemainingPoints);
                        soundingOk();
                    } else {
                        soundingNotOk();
                    }
                    break;
                case R.id.btDecreaseKnowledge:
                    if (intKnowledge >= 1) {
                        decreaseScore(intKnowledge, tvKnowledgeScore, intRemainingPoints, tvRemainingPoints);
                        soundingOk();
                    } else {
                        soundingNotOk();
                    }
                    break;
            }
        }
    }

    public void applyRace(Race race) {
        activeRace = race;// storing racial choice
        player.setRace(race.getName());  // updating character stats
        player.setDexterity(player.getDexterity() + race.getDexterity());
        player.setStamina(player.getStamina() + race.getStamina());
        player.setIntelligence(player.getIntelligence() + race.getIntelligence());
        player.setKnowledge(player.getKnowledge() + race.getKnowledge());
        tvRace.setText("Race : " + race.getName());  // synchronizing stats display with character data
        tvDexterityScore.setText(String.valueOf(player.getDexterity()));
        tvStaminaScore.setText(String.valueOf(player.getStamina()));
        tvIntelligenceScore.setText(String.valueOf(player.getIntelligence()));
        tvKnowledgeScore.setText(String.valueOf(player.getKnowledge()));

    }

    public void undoRace(Race race) { // needed so that racial choice doesn't stack indefinetely with itself
        player.setDexterity(player.getDexterity() - race.getDexterity()); // updating character stats
        player.setStamina(player.getStamina() - race.getStamina());
        player.setIntelligence(player.getIntelligence() - race.getIntelligence());
        player.setKnowledge(player.getKnowledge() - race.getKnowledge());
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

    public void goBack(View view) {
        soundingNotOk();
        Intent i = new Intent(this, FirstPage.class);
        startActivity(i);
    }

    public void clearName(View view) {
        etName.setText("");
    }

    public void proceed(View view) {          // updating player data and storing it into the database
        soundingOk();
        player.setName(valueOf(etName.getText()));
        player.setDexterity(Integer.parseInt(valueOf(tvDexterityScore.getText())));
        player.setStamina(Integer.parseInt(valueOf(tvStaminaScore.getText())));
        player.setIntelligence(Integer.parseInt(valueOf(tvIntelligenceScore.getText())));
        player.setKnowledge(Integer.parseInt(valueOf(tvKnowledgeScore.getText())));
        player.saveCharacter(player.getId());
        Intent i = new Intent(this, Arena.class);
        i.putExtra("CharacterID", player.getId());
        startActivity(i);
    }

    public void soundingOk() {
        MediaPlayer buttonSound;
        buttonSound = MediaPlayer.create(this, R.raw.button_sound_ok);
        buttonSound.start();
    }

    public void soundingNotOk() {
        MediaPlayer buttonSound;
        buttonSound = MediaPlayer.create(this, R.raw.button_sound_not_ok);
        buttonSound.start();
    }

    public void firstNext(View view) {
        soundingOk();
        player.setDexterity(Integer.parseInt(tvDexterityScore.getText().toString()));
        player.setStamina(Integer.parseInt(tvStaminaScore.getText().toString()));
        player.setIntelligence(Integer.parseInt(tvIntelligenceScore.getText().toString()));
        player.setKnowledge(Integer.parseInt(tvKnowledgeScore.getText().toString()));
        llCreationOne.setVisibility(View.GONE);
        svCreationTwo.setVisibility(View.VISIBLE);
        btFirstNext.setVisibility(View.GONE);
        btSecondNext.setVisibility(View.VISIBLE);


    }

    public void secondNext(View view) {
        soundingOk();
        player.setDexterity(Integer.parseInt(tvDexterityScore.getText().toString()));
        player.setStamina(Integer.parseInt(tvStaminaScore.getText().toString()));
        player.setIntelligence(Integer.parseInt(tvIntelligenceScore.getText().toString()));
        player.setKnowledge(Integer.parseInt(tvKnowledgeScore.getText().toString()));
        player.setRace(tvRace.getText().toString());
        svCreationTwo.setVisibility(View.GONE);
        llCreationThree.setVisibility(View.VISIBLE);
        btSecondNext.setVisibility(View.GONE);
        btThirdNext.setVisibility(View.VISIBLE);
    }

    public void thirdNext(View view) {
        soundingOk();
        player.setStyle1(tvStyle.getText().toString());
        player.saveCharacter(player.getId());
        Intent i = new Intent(this, Arena.class);
        i.putExtra("CharacterID", player.getId());
        startActivity(i);

    }

    public void chooseStyle(View view) {
        soundingOk();
        switch (view.getId()) {
            case R.id.btPower:
                player.setStyle1("Power");
                tvStyle.setText("Style : " + player.getStyle1());
                break;
            case R.id.btTechnics:
                player.setStyle1("Technics");
                tvStyle.setText("Style : " + player.getStyle1());
                break;
            case R.id.btLooks:
                player.setStyle1("Looks");
                tvStyle.setText("Style : " + player.getStyle1());
                break;
            case R.id.btMagic:
                player.setStyle1("Magic");
                tvStyle.setText("Style : " + player.getStyle1());
                break;
            case R.id.btScience:
                player.setStyle1("Science");
                tvStyle.setText("Style : " + player.getStyle1());
                break;
        }
    }

}
