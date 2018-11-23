package com.example.dell_optilex_3010.bloodbath.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell_optilex_3010.bloodbath.charactercreation.Character;
import com.example.dell_optilex_3010.bloodbath.R;

import static java.lang.String.valueOf;

public class CreationPageOne extends AppCompatActivity {
    private TextView tvDexterityScore;
    private TextView tvStaminaScore;
    private TextView tvIntelligenceScore;
    private TextView tvKnowledgeScore;
    private  TextView tvRemainingPoints;
    private Character player;
    private EditText etName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_page_one);
        player = new Character(this);               // create a character used to store character creation data
        tvDexterityScore = findViewById(R.id.tvDexterityScore);
        tvStaminaScore = findViewById(R.id.tvStaminaScore);
        tvIntelligenceScore = findViewById(R.id.tvIntelligenceScore);
        tvKnowledgeScore = findViewById(R.id.tvKnowledgescore);
        tvRemainingPoints = findViewById(R.id.tvRemainingPoints);
        etName = findViewById(R.id.etName);
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
        Intent i = new Intent(this, CreationPageTwo.class);
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

    public void tutorial(View view) {     // provides hints on game mechanics via toast
        Context context = getApplicationContext();
        CharSequence text = "Stats are grouped by opposition : Stamina is used to protect against physical attacks(Dexterity attacks), and the same goes for mental attacks with Knowledge protecting against Intelligence";
        int duration = Toast.LENGTH_LONG;
        Toast tutoOne = Toast.makeText(context, text, duration);
        tutoOne.setGravity(Gravity.TOP | Gravity.LEFT, 0, 0);
        tutoOne.getView().setBackgroundColor(Color.RED);
        tutoOne.show();
    }

}
