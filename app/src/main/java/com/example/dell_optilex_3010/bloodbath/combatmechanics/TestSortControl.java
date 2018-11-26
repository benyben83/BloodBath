package com.example.dell_optilex_3010.bloodbath.combatmechanics;


import android.widget.TextView;

import com.example.dell_optilex_3010.bloodbath.charactercreation.Character;

import java.util.LinkedList;
import java.util.Random;


public class TestSortControl {
    private DirectActions directActions = new DirectActions();
    private PreparedActions preparedActions = new PreparedActions();
    private PassiveActions passiveActions = new PassiveActions();
    private CombinedActions combinedActions = new CombinedActions();


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
        if ((!character.getName().equals(playerModel.getName())) && variables[2] > 0) {
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
        if (variables[0] == 1) {
            actionsAndAdvantage[6] = opponent.getName();
            if ((player.getDexterity() + player.getIntelligence() + dice.nextInt() + variables[3] + variables[5]) >= (opponent.getIntelligence() + opponent.getDexterity() + dice.nextInt() + variables[7] + variables[9])) {
                actionsAndAdvantage[6] = player.getName();
            }
            tvArena.append(actionsAndAdvantage[6] + " starts this fight with the advantage !\n\n");
        } else if (variables[0] > 0) {
            if (actionsAndAdvantage[6].equals(player.getName()) && variables[1] > 0 && variables[2] == 0) {

                actionsAndAdvantage[6] = opponent.getName();
                tvArena.append(player.getName() + " looses the advantage !\n\n");

            } else if (actionsAndAdvantage[6].equals(opponent.getName()) && variables[2] > 0 && variables[1] == 0) {
                actionsAndAdvantage[6] = player.getName();
                tvArena.append(opponent.getName() + " looses the advantage !\n\n");

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
        if ("Rush".equals(actionToSort) || "Frenetic bashing".equals(actionToSort)) {
            sortedIndex = 0;
        }
        if ("Loudness".equals(actionToSort) || "Fetish".equals(actionToSort) || "Vandalism".equals(actionToSort) || "Warming up".equals(actionToSort)) {
            sortedIndex = 1;
        }
        if ("Barbaric fire".equals(actionToSort) || "Battle rage".equals(actionToSort)) {
            sortedIndex = 2;
        }
        if ("Inquisition".equals(actionToSort) || "Ancient rites".equals(actionToSort)) {
            sortedIndex = 3;
        }
        return sortedIndex;
    }

    public void actionManager(Character player, Character opponent, String actionChosen, Random dice, int[] variables, String[] actionsStored, TextView tvArena) { // used to apply player's choice to combat

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

    private void actionStorer(String action, String[] actionsStored, Character player, TextView // used for preparing actions
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

    public void reactionsApplier(Character player, Character opponent, LinkedList<String> reactionsToApply, int[] combatVariables, Random dice, TextView tvArena, String[] prepartedActions) { // used to trigger appropriate combat reactions


        for (String action : reactionsToApply) {


            if ("Loudness".equals(action) && testingPassiveReaction(player, "Loudness") && testingStyle(opponent, "Magic")) {
                passiveActions.passivePowerOne(player, opponent, combatVariables, dice, tvArena);

            }
            if ("Fetish".equals(action) && testingPassiveReaction(player, "Fetish") && testingStyle(opponent, "Magic")) {
                passiveActions.passivePowerTwo(player, opponent, combatVariables, dice, tvArena);

            }
            if ("Vandalism".equals(action) && testingPassiveReaction(player, "Vandalism") && testingStyle(opponent, "Science")) {
                passiveActions.passivePowerThree(player, opponent, combatVariables, dice, tvArena);

            }
            if ("Warming up".equals(action) && testingPassiveReaction(player, "Warming up") && testingStyle(opponent, "Science")) {
                passiveActions.passivePowerFour(player, opponent, combatVariables, dice, tvArena);

            }
            if ("Barbaric fire".equals(action) && testingActionAlreadyPrepared(player, "Barbaric fire", prepartedActions)) {
                preparedActions.preparedPowerOne(player, opponent, combatVariables, dice, tvArena);

            }
            if ("Battle rage".equals(action) && testingActionAlreadyPrepared(player, "Barbaric fire", prepartedActions) && testingWounds(player, player, combatVariables)) {
                preparedActions.preparedPowerTwo(player, opponent, combatVariables, dice, tvArena);

            }


        }
    }

    public LinkedList<String> actionInventoryChecker(Character player, String[] actionsStored) { // used to retrieve every reaction to test for triggering
        LinkedList<String> reactions = new LinkedList<>();

        reactions.add(player.getActionOne());
        reactions.add(player.getActionTwo());
        reactions.add(player.getActionThree());
        reactions.add(player.getActionFour()); b
        reactions.add(player.getActionFive());
        reactions.add(player.getActionSix());
        reactions.add(player.getActionSeven());
        reactions.add(player.getActionEight());
        reactions.add(player.getActionNine());
        reactions.add(player.getActionTen());
        if (actionsStored[0].equals(player.getName())) {
            reactions.add(actionsStored[1]);
            actionsStored[1] = "";
        }
        if (actionsStored[2].equals(player.getName())) {
            reactions.add(actionsStored[3]);
            actionsStored[3] = "";
        }
        if (actionsStored[4].equals(player.getName())) {
            reactions.add(actionsStored[5]);
            actionsStored[5] = "";
        }
        return reactions;
    }

    public boolean testingPassiveReaction(Character player, String actionWanted) { // testing if a given character has the given action slotted
        boolean positiveTesting = false;
        if (player.getActionOne().equals(actionWanted) || player.getActionTwo().equals(actionWanted) || player.getActionThree().equals(actionWanted) || player.getActionFour().equals(actionWanted) || player.getActionFive().equals(actionWanted) || player.getActionSix().equals(actionWanted) || player.getActionSeven().equals(actionWanted) || player.getActionEight().equals(actionWanted) || player.getActionNine().equals(actionWanted) || player.getActionTen().equals(actionWanted)) {
            positiveTesting = true;
        }
        return positiveTesting;
    }

    public boolean testingActionAlreadyPrepared(Character player, String action, String[] actionsStored) { // testing if the action has been prepared and slotted    already
        boolean positiveTesting = false;
        for (int i = 0; i <= 5; i += 5) {
            if (actionsStored[i].equals(player.getName()) && actionsStored[i + 1].equals(action)) {
                positiveTesting = true;
            }
        }
        return positiveTesting;
    }
}


