package com.example.dell_optilex_3010.bloodbath.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell_optilex_3010.bloodbath.R;
import com.example.dell_optilex_3010.bloodbath.charactercreation.Character;
import com.example.dell_optilex_3010.bloodbath.combatmechanics.OpponentActions;
import com.example.dell_optilex_3010.bloodbath.combatmechanics.TestSortControl;

import java.util.Random;

public class Arena extends AppCompatActivity {
    Random dice = new Random(1 - 20);
    OpponentActions opponentActions = new OpponentActions();
    private int[] combatVariables = {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};  // round counter/player wounds/defender wounds/temporary attacker bonus (dext)/temporary attacker bonus (stmn)/temporary attacker bonus (intl)/temporary attacker bonus (knwl)/temporary defender bonus (dext)/temporary defender bonus (stmn)/temporary defender bonus (intl)/temporary defender bonus (knwl)/round started/player played/opponent played
    private String[] preparedActionsAndAdvantage = {"", "", "", "", "", "", "", ""};   // first slot owner/first slot action stocked / second slot owner / second slot action stored / third slot owner / third slot action stored/Advantaged player/Winner of the fight
    private Character player;
    private Character opponent;
    boolean playerTurnDone;
    boolean opponentTurnDone;
    TestSortControl test;
    TextView tvArena;
    Button startButton;
    Button actionButton;
    Button opponentTurn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arena);
        player = new Character(this);
        opponent = new Character(this);
        player.loadCharacter(1);
        opponent.loadCharacter(2);
        test = new TestSortControl();
        tvArena = findViewById(R.id.tvArena);
        startButton = findViewById(R.id.btFirstRound);
        actionButton = findViewById(R.id.btAction);
        opponentTurn = findViewById(R.id.btBringIt);
        actionButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(Arena.this, actionButton);
                popup.getMenuInflater().inflate(R.menu.menu_display, popup.getMenu());
                popup.getMenu().getItem(test.actionsMenuSorter(player.getActionOne())).getSubMenu().add(player.getActionOne());
                popup.getMenu().getItem(test.actionsMenuSorter(player.getActionTwo())).getSubMenu().add(player.getActionTwo());
                popup.getMenu().getItem(test.actionsMenuSorter(player.getActionThree())).getSubMenu().add(player.getActionThree());
                popup.getMenu().getItem(test.actionsMenuSorter(player.getActionFour())).getSubMenu().add(player.getActionFour());
                popup.getMenu().getItem(test.actionsMenuSorter(player.getActionFive())).getSubMenu().add(player.getActionFive());
                popup.getMenu().getItem(test.actionsMenuSorter(player.getActionSix())).getSubMenu().add(player.getActionSix());
                popup.getMenu().getItem(test.actionsMenuSorter(player.getActionSeven())).getSubMenu().add(player.getActionSeven());
                popup.getMenu().getItem(test.actionsMenuSorter(player.getActionEight())).getSubMenu().add(player.getActionEight());
                popup.getMenu().getItem(test.actionsMenuSorter(player.getActionNine())).getSubMenu().add(player.getActionNine());
                popup.getMenu().getItem(test.actionsMenuSorter(player.getActionTen())).getSubMenu().add(player.getActionTen());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {


                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        // test.actionManager(player, opponent, item.getTitle().toString(), dice, combatVariables, preparedActionsAndAdvantage, tvArena);
                        Toast.makeText(Arena.this, "You Clicked : " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;

                    }
                });
                popup.show();

            }
        });

    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        return super.onPrepareOptionsMenu(menu);
    }

    public void startRound(View view) {
        tvArena.append("Round " + (String.valueOf(combatVariables[0])) + "\n");
        startButton.setVisibility(View.INVISIBLE);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((combatVariables[9]!=combatVariables[0])){

                }
            }
        });


        while (preparedActionsAndAdvantage[7].equals("")) { // as long as nobody won

            test.determiningAdvantage(player, opponent, combatVariables, dice, preparedActionsAndAdvantage, tvArena); // determines who has the advantage
            if (preparedActionsAndAdvantage[6].equals(player.getName())) { // if the player has the advantage he plays first
                actionButton.setVisibility(View.VISIBLE); // player turn
                while (!playerTurnDone) {
                }
                test.reactionsApplier(player, opponent, test.actionInventoryChecker(player, preparedActionsAndAdvantage), combatVariables, dice, tvArena, preparedActionsAndAdvantage); // testing for opponent reactions
                opponentTurn.setVisibility(View.VISIBLE);// opponent turn
                while (!opponentTurnDone) {

                }
                test.reactionsApplier(opponent, player, test.actionInventoryChecker(player, preparedActionsAndAdvantage), combatVariables, dice, tvArena, preparedActionsAndAdvantage);// testing for player reactions
            } else { // if the opponent has the advantage he plays first
                opponentTurn.setVisibility(View.VISIBLE);// opponent turn
                while (!opponentTurnDone) {

                }
                test.reactionsApplier(opponent, player, test.actionInventoryChecker(player, preparedActionsAndAdvantage), combatVariables, dice, tvArena, preparedActionsAndAdvantage);// testing for player reactions
                actionButton.setVisibility(View.VISIBLE);// player turn
                while (!playerTurnDone) {
                }
                test.reactionsApplier(player, opponent, test.actionInventoryChecker(player, preparedActionsAndAdvantage), combatVariables, dice, tvArena, preparedActionsAndAdvantage);// testing for opponent reactions

            }
            test.testingVictory(player, opponent, combatVariables, preparedActionsAndAdvantage); // checking for victory
            combatVariables[0]++; // next round
            startButton.setVisibility(View.VISIBLE);
        }
    }

    public void opponentTurn(View view) {
        opponentActions.jeanMiFrappe(player, opponent, dice, combatVariables, tvArena);
        opponentTurn.setVisibility(View.INVISIBLE);


    }

}
