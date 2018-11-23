package com.example.dell_optilex_3010.bloodbath.combatmechanics;

import android.widget.TextView;

import com.example.dell_optilex_3010.bloodbath.charactercreation.Character;

import java.util.Random;


public class TestSortControl {
    DirectActions directActions = new DirectActions();
    PreparedActions preparedActions = new PreparedActions();
    PassiveActions passiveActions = new PassiveActions();
    CombinedActions combinedActions = new CombinedActions();


    public TestSortControl() {
    }

    public boolean testingStyle(Character character, String styleWanted) { // used to verify if a given character uses a given style
        boolean positiveTesting = false;
        if (character.getStyle1().equals(styleWanted) || character.getStyle2().equals(styleWanted) || character.getStyle3().equals(styleWanted) || character.getStyle4().equals(styleWanted) || character.getStyle5().equals(styleWanted)) {
            positiveTesting = true;
        }
        return positiveTesting;
    }

    public boolean testingWounds(Character character, Character playerModel, int[] variables) { // used to verify if a given player is wounded. use player character twice as argument
        boolean positiveTesting = false;
        if (character.getName().equals(playerModel.getName()) && variables[1] > 0) {
            positiveTesting = true;
        }
        if (!character.getName().equals(playerModel.getName()) && variables[2] > 0) {
            positiveTesting = true;
        }
        return positiveTesting;
    }

    public boolean testingPreparedAction(Character player, String[] actionsStored, String actionWanted) { // used to verify if a given prepared action has already been prepared by a given character
        boolean positiveTesting = false;
        if (player.getName().equals(actionsStored[0]) && actionWanted.equals(actionsStored[1])) {
            positiveTesting = true;
        }
        if (player.getName().equals(actionsStored[2]) && actionWanted.equals(actionsStored[3])) {
            positiveTesting = true;
        }
        if (player.getName().equals(actionsStored[4]) && actionWanted.equals(actionsStored[5])) {
            positiveTesting = true;
        }
        return positiveTesting;
    }

    public void determiningAdvantage(Character player, Character opponent, int[] variables, Random dice, String[] actionsAndAdvantage, TextView tvArena) { // used to determine what character has the advantage during combat
        if (variables[0] == 0) {
            actionsAndAdvantage[6] = opponent.getName();
            if ((player.getDexterity() + player.getIntelligence() + dice.nextInt()) >= (opponent.getIntelligence() + opponent.getDexterity() + dice.nextInt())) {
                actionsAndAdvantage[6] = player.getName();
            }
            tvArena.setText(player.getName() + " Starts this fight with the advantage !\n");
        } else if (variables[0] > 0) {
            if (actionsAndAdvantage[6].equals(player.getName())) {
                if (variables[1] > 0 && variables[2] == 0) {
                    actionsAndAdvantage[6] = opponent.getName();
                    tvArena.append(player.getName() + " looses the advantage !");
                }
            } else if (actionsAndAdvantage[6].equals(opponent.getName())) {
                if (variables[2] > 0 && variables[1] == 0) {
                    actionsAndAdvantage[6] = player.getName();
                    tvArena.append(opponent.getName() + " looses the advantage !");
                }
            }
        }

    }

    public void testingVictory(Character player, Character opponent, int[] variables, String[] actionsAndAdvantage) { // used to verify if a given character has reached victory yet
        if (variables[1] >= player.getHealth() && actionsAndAdvantage[6].equals(opponent.getName())) {
            actionsAndAdvantage[7] = opponent.getName();
        }
        if (variables[2] >= opponent.getHealth() && actionsAndAdvantage[6].equals((player.getName()))) {
            actionsAndAdvantage[7] = player.getName();
        }

    }

    public int actionsMenuSorter(String actionToSort) { // used to sort dynamic menu items at the proper index
        int sortedIndex = 4;
        if (actionToSort.equals("Rush") || actionToSort.equals("Frenetic bashing")) {
            sortedIndex = 0;
        }
        if (actionToSort.equals("Loudness") || actionToSort.equals("Fetish") || actionToSort.equals("Vandalism") || actionToSort.equals("Warming up")) {
            sortedIndex = 1;
        }
        if (actionToSort.equals("Barbaric fire") || actionToSort.equals("Battle rage")) {
            sortedIndex = 2;
        }
        if (actionToSort.equals("Inquisition") || actionToSort.equals("Ancient rites")) {
            sortedIndex = 3;
        }
        return sortedIndex;
    }

    public void actionManager(Character player, Character opponent, String actionChosen, Random dice, int[] variables, String[] actionsStored, TextView tvArena) {
        switch (actionChosen) {
            case "Rush":
                directActions.directPowerOne(player, opponent, variables, dice, tvArena);
                break;
            case "Frenetic bashing":
                directActions.directPowerTwo(player, opponent, variables, dice, tvArena);
                break;
            case "Loudness":
                tvArena.append("You can't trigger a passive action.");
                break;
            case "Fetish":
                tvArena.append("You can't trigger a passive action.");
                break;
            case "Vandalism":
                tvArena.append("You can't trigger a passive action.");
                break;
            case "Warming up":
                tvArena.append("You can't trigger a passive action.");
                break;
            case "Barbaric fire":
                actionStorer("Barbaric fire", actionsStored, player, tvArena);
                break;
            case "Battle rage":
                actionStorer("Battle rage", actionsStored, player, tvArena);
                break;
            case "Inquisition":
                combinedActions.combinedPowerOne(player, opponent, variables, actionsStored, tvArena);
                break;
            case "Ancient rites":
                combinedActions.combinedPowerTwo(player, opponent, variables, actionsStored, tvArena);
                break;
        }
    }

    private void actionStorer(String action, String[] actionsStored, Character player, TextView
            tvArena) {
        String statement = "You prepare something nasty\n";
        if (actionsStored[0].equals("")) {
            actionsStored[0] = player.getName();
            actionsStored[1] = action;
        } else if (actionsStored[2].equals("")) {
            actionsStored[2] = player.getName();
            actionsStored[3] = action;
        } else if (actionsStored[4].equals("")) {
            actionsStored[4] = player.getName();
            actionsStored[5] = action;
        } else {
            statement = "Too many actions prepared ! Yours has gone to waste...\n";
        }
        tvArena.append(statement);
    }

    public void reactionsApplier(Character player, Character opponent, String[] reactionsToApply, int[] combatVariables, Random dice, TextView tvArena, String[] actionsStored) {
        for (int i = 0; (i <= 13); i++) {
            switch (reactionsToApply[i]) {
                case "Loudness":
                    passiveActions.passivePowerOne(player, opponent, combatVariables, dice, tvArena);
                    break;
                case "Fetish":
                    passiveActions.passivePowerTwo(player, opponent, combatVariables, dice, tvArena);
                    break;
                case "Vandalism":
                    passiveActions.passivePowerThree(player, opponent, combatVariables, dice, tvArena);
                    break;
                case "Warming up":
                    passiveActions.passivePowerFour(player, opponent, combatVariables, dice, tvArena);
                    break;
                case "Barbaric fire":
                    preparedActions.preparedPowerOne(player, opponent, combatVariables, dice, tvArena);
                    break;
                case "Battle rage":
                    preparedActions.preparedPowerTwo(player, opponent, combatVariables, dice, tvArena, actionsStored);
                    break;
                case "":
                    break;
            }
        }
    }

    public String[] actionInventoryChecker(Character player,String[] actionsStored) {
        String[]reactions = new String[13];
        reactions[0] = player.getActionOne();
        reactions[1] = player.getActionTwo();
        reactions[2] = player.getActionThree();
        reactions[3] = player.getActionFour();
        reactions[4] = player.getActionFive();
        reactions[5] = player.getActionSix();
        reactions[6] = player.getActionSeven();
        reactions[7] = player.getActionEight();
        reactions[8] = player.getActionNine();
        reactions[9] = player.getActionTen();
        if (actionsStored[0].equals(player.getName())) {
            reactions[10] = actionsStored[1];
            actionsStored[1] = "";
        }
        if (actionsStored[2].equals(player.getName())) {
            reactions[11] = actionsStored[3];
            actionsStored[3] = "";
        }
        if (actionsStored[4].equals(player.getName())) {
            reactions[12] = actionsStored[5];
            actionsStored[5] = "";
        }
        return reactions;
    }
}





