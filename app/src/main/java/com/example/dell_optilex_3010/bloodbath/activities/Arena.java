package com.example.dell_optilex_3010.bloodbath.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.dell_optilex_3010.bloodbath.R;
import com.example.dell_optilex_3010.bloodbath.charactercreation.Character;
import com.example.dell_optilex_3010.bloodbath.combatmechanics.OpponentActions;
import com.example.dell_optilex_3010.bloodbath.combatmechanics.TestSortControl;

import java.util.Random;

public class Arena extends AppCompatActivity {
    private Random dice = new Random(1 - 20);
    private OpponentActions opponentActions = new OpponentActions();
    private int[] combatVariables = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};  // round counter/player wounds/defender wounds/temporary attacker bonus (dext)/temporary attacker bonus (stmn)/temporary attacker bonus (intl)/temporary attacker bonus (knwl)/temporary defender bonus (dext)/temporary defender bonus (stmn)/temporary defender bonus (intl)/temporary defender bonus (knwl)/round process counter
    private String[] preparedActionsAndAdvantage = {"", "", "", "", "", "", ""};   // first slot owner/first slot action stocked / second slot owner / second slot action stored / third slot owner / third slot action stored/Advantaged player
    private Character player;
    private Character opponent;
    private TestSortControl test;
    private TextView tvArena;
    private Button startButton;
    private Button actionButton;
    private Button opponentTurn;

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
        startButton = findViewById(R.id.btStartRound);
        actionButton = findViewById(R.id.btAction);
        opponentTurn = findViewById(R.id.btBringIt);
        tvArena.setMovementMethod(new ScrollingMovementMethod());
        actionButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) { // the menu sorts and fills up with the player's character on click
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
                    public boolean onMenuItemClick(MenuItem item) { // player's turn
                        test.actionManager(player, opponent, item.getTitle().toString(), dice, combatVariables, preparedActionsAndAdvantage, tvArena); // apply player's choice
                        actionButton.setVisibility(View.GONE); // player button disappears
                        if (combatVariables[11] == 0) { // if the opponent hasn't played yet


                            // here will be victory tester


                            opponentTurn.setVisibility(View.VISIBLE); // opponent's turn
                        } else { // if the opponent has played already
                            startButton.setVisibility(View.VISIBLE); // new round
                        }
                        combatVariables[11]++;
                        test.reactionsApplier(player, opponent, test.actionInventoryChecker(player, preparedActionsAndAdvantage), combatVariables, dice, tvArena, preparedActionsAndAdvantage); // testing for opponent reactions

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
        combatVariables[0]++;// new round
        combatVariables[11] = 0; // beginning of a new round
        tvArena.append("\nRound " + (String.valueOf(combatVariables[0])) + "\n\n");
        startButton.setVisibility(View.GONE);
        test.determiningAdvantage(player, opponent, combatVariables, dice, preparedActionsAndAdvantage, tvArena); // determines who has the advantage
        if (preparedActionsAndAdvantage[6].equals(player.getName())) { // if the player has the advantage he plays first
            actionButton.setVisibility(View.VISIBLE);
        } else {
            opponentTurn.setVisibility(View.VISIBLE);
        }

    }

    public void opponentTurn(View view) {

        opponentActions.jeanMiFrappe(player, opponent, dice, combatVariables, tvArena); // opponent plays his turn
        test.reactionsApplier(opponent, player, test.actionInventoryChecker(opponent, preparedActionsAndAdvantage), combatVariables, dice, tvArena, preparedActionsAndAdvantage);// testing for player reactions
        opponentTurn.setVisibility(View.GONE);
        if (combatVariables[11] == 0) { //if player hasn't played yet it's his turn
            actionButton.setVisibility(View.VISIBLE);
        } else { // else new round
            startButton.setVisibility(View.VISIBLE);
        }
        combatVariables[11]++;
    }
}


